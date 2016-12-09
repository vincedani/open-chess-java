/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package main.java.game;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.JChessApp;
import main.java.LogToFile;
import main.java.board.IChessboard;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.pieces.King;
import main.java.squareBoard.SquareBoard;

/**
 * Class responsible for the starts of new games, loading games, saving it, and
 * for ending it. This class is also responsible for appoing player with have a
 * move at the moment
 */
public class Game extends JPanel implements MouseListener, ComponentListener {

	private Settings settings;
	public boolean blockedChessboard;
	private IChessboard chessboard;
	private Player activePlayer;
	private GameClock gameClock;
	private Client client;
	private MovesTable moves;
	private Chat chat;

	public Game() {

		moves = new MovesTable(this);
		settings = new Settings();
		chessboard = new CircleBoard(this.getSettings(), this.getMoves());

		initializeChessboardPanel();
		initializeClock();
		initializeMovesHistory();
		initializeChat();

		this.blockedChessboard = false;
		this.setLayout(null);
		this.addComponentListener(this);
		this.setDoubleBuffered(true);
	}

	private void initializeChat() {
		this.setChat(new Chat());
		this.getChat().setSize(new Dimension(380, 100));
		this.getChat().setLocation(new Point(0, 500));
		this.getChat().setMinimumSize(new Dimension(400, 100));
	}

	private void initializeMovesHistory() {
		JScrollPane movesHistory = this.getMoves().getScrollPane();
		movesHistory.setSize(new Dimension(180, 350));
		movesHistory.setLocation(new Point(500, 121));
		this.add(movesHistory);
	}

	private void initializeClock() {
		setGameClock(new GameClock(this));
		getGameClock().setSize(new Dimension(200, 100));
		getGameClock().setLocation(new Point(500, 0));
		this.add(getGameClock());
	}

	private void initializeChessboardPanel() {
		chessboard.getDisplay().setVisible(true);
		chessboard.getDisplay().addMouseListener(this);
		chessboard.getDisplay().setLocation(new Point(0, 0));
		this.add(chessboard.getDisplay());
	}

	/**
	 * Method to save actual state of game
	 * 
	 * @param path
	 *            address of place where game will be saved
	 */
	public void saveGame(File path) {
		File file = path;
		FileWriter fileW = null;
		try {
			fileW = new FileWriter(file);
		} catch (java.io.IOException exc) {
			// System.err.println("error creating fileWriter: " + exc);
			LogToFile.log(exc, "Error", "error creating fileWriter: " + exc.getMessage());
			JOptionPane.showMessageDialog(this, Settings.lang("error_writing_to_file") + ": " + exc);
			return;
		}
		String str = new String("");
		String info = new String("[Event \"Game\"]\n[Date \"" + Calendar.YEAR + "." + Calendar.MONTH + 1 + "."
				+ Calendar.DAY_OF_MONTH + "\"]\n" + "[White \"" + this.getSettings().playerWhite.name + "\"]\n[Black \""
				+ this.getSettings().playerBlack.name + "\"]\n\n");
		str += info;
		str += this.getMoves().getMovesInString();
		try {
			fileW.write(str);
			fileW.flush();
			fileW.close();
		} catch (java.io.IOException exc) {
			// System.out.println("error writing to file: " + exc);
			LogToFile.log(exc, "Error", "error writing to file: " + exc.getMessage());
			JOptionPane.showMessageDialog(this, Settings.lang("error_writing_to_file") + ": " + exc);
			return;
		}
		JOptionPane.showMessageDialog(this, Settings.lang("game_saved_properly"));
	}

	/**
	 * Loading game method(loading game state from the earlier saved file)
	 * 
	 * @param file
	 *            File where is saved game
	 */

	static public void loadGame(File file) {
		FileReader fileR = null;
		try {
			fileR = new FileReader(file);
		} catch (java.io.IOException exc) {
			// System.out.println("Something wrong reading file: " + exc);
			LogToFile.log(exc, "Error", "Something wrong reading file: " + exc.getMessage());
			return;
		}
		BufferedReader br = new BufferedReader(fileR);
		String tempStr = new String();
		String blackName, whiteName, blueName;
		try {
			tempStr = getLineWithVar(br, new String("[White"));
			whiteName = getValue(tempStr);
			tempStr = getLineWithVar(br, new String("[Black"));
			blackName = getValue(tempStr);
			tempStr = getLineWithVar(br, new String("[Blue"));
			blueName = getValue(tempStr);
			tempStr = getLineWithVar(br, new String("1."));
		} catch (ReadGameError err) {
			// System.out.println("Error reading file: " + err);
			LogToFile.log(err, "Error", "Error reading file: " + err.getMessage());
			return;
		}
		Game newGUI = JChessApp.getJcv().addNewTab(whiteName + " vs. " + blackName + " vs. " + blueName);
		Settings locSetts = newGUI.getSettings();
		locSetts.playerBlack.name = blackName;
		locSetts.playerWhite.name = whiteName;
		locSetts.playerBlue.name = blueName;

		locSetts.playerBlack.setType(Player.playerTypes.localUser);
		locSetts.playerWhite.setType(Player.playerTypes.localUser);
		locSetts.playerBlue.setType(Player.playerTypes.localUser);

		locSetts.gameMode = Settings.gameModes.loadGame;
		locSetts.gameType = Settings.gameTypes.local;

		newGUI.newGame();
		// newGUI.blockedChessboard = true;
		newGUI.getMoves().setMoves(tempStr);
		// newGUI.blockedChessboard = false;
		newGUI.chessboard.getDisplay().repaint();
	}

	/**
	 * Method checking in with of line there is an error
	 * 
	 * @param br
	 *            BufferedReader class object to operate on
	 * @param srcStr
	 *            String class object with text which variable you want to get
	 *            in file
	 * @return String with searched variable in file (whole line)
	 * @throws ReadGameError
	 *             class object when something goes wrong when reading file
	 */
	static public String getLineWithVar(BufferedReader br, String srcStr) throws ReadGameError {
		String str = new String();
		while (true) {
			try {
				str = br.readLine();
			} catch (java.io.IOException exc) {
				// System.out.println("Something wrong reading file: " + exc);
				LogToFile.log(exc, "Error", "Something wrong reading file: " + exc.getMessage());
			}
			if (str == null) {
				throw new ReadGameError();
			}
			if (str.startsWith(srcStr)) {
				return str;
			}
		}
	}

	/**
	 * Method to get value from loaded txt line
	 * 
	 * @param line
	 *            Line which is read
	 * @return result String with loaded value
	 * @throws ReadGameError
	 *             object class when something goes wrong
	 */
	static public String getValue(String line) throws ReadGameError {
		// System.out.println("getValue called with: "+line);
		int from = line.indexOf("\"");
		int to = line.lastIndexOf("\"");
		int size = line.length() - 1;
		String result = new String();
		if (to < from || from > size || to > size || to < 0 || from < 0) {
			throw new ReadGameError();
		}
		try {
			result = line.substring(from + 1, to);
		} catch (java.lang.StringIndexOutOfBoundsException exc) {
			// System.out.println("error getting value: " + exc);
			LogToFile.log(exc, "Error", "error getting value: " + exc.getMessage());
			return "none";
		}
		return result;
	}

	/**
	 * Method to Start new game
	 *
	 */
	public void newGame() {
		chessboard.setPieces("",
				new Player[] { getSettings().playerWhite, getSettings().playerBlack, getSettings().playerBlue });

		// System.out.println("new game, game type: "+settings.gameType.name());

		activePlayer = getSettings().playerWhite;
		if (activePlayer.playerType != Player.playerTypes.localUser) {
			this.blockedChessboard = true;
		}
		Game activeGame = JChessApp.getJcv().getActiveTabGame();
		if (activeGame != null && JChessApp.getJcv().getNumberOfOpenedTabs() == 1) {
			activeGame.chessboard.getDisplay().resizeChessboard(activeGame.chessboard.get_height(false));
			activeGame.chessboard.getDisplay().repaint();
			activeGame.repaint();
		}

		chessboard.getDisplay().repaint();
		this.repaint();
	}

	/**
	 * Method to end game
	 * 
	 * @param message
	 *            what to show player(s) at end of the game (for example "draw",
	 *            "black wins" etc.)
	 */
	public void endGame(String message) {
		this.blockedChessboard = true;
		System.out.println(message);
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * Method to switch active players after move
	 */
	public void switchActive() {
		if (activePlayer == getSettings().playerWhite) {
			activePlayer = getSettings().playerBlack;
		} else if (activePlayer == getSettings().playerBlack) {
			activePlayer = getSettings().playerBlue;
		} else if (activePlayer == getSettings().playerBlue) {
			activePlayer = getSettings().playerWhite;
		}

		this.getGameClock().switch_clocks();
	}

	/**
	 * Method of getting the active player
	 * 
	 * @return player The player which have a move
	 */
	public Player getActivePlayer() {
		return this.activePlayer;
	}

	/**
	 * Method to go to next move (checks if game is local/network etc.)
	 */
	public void nextMove() {
		switchActive();

		System.out.println("next move, active player: " + activePlayer.name + ", color: "
				+ activePlayer.getColor().name() + ", type: " + activePlayer.playerType.name());
		if (activePlayer.playerType == Player.playerTypes.localUser) {
			this.blockedChessboard = false;
		} else if (activePlayer.playerType == Player.playerTypes.networkUser) {
			this.blockedChessboard = true;
		} else if (activePlayer.playerType == Player.playerTypes.computer) {
			// ? if nothing here then delete
		}
	}

	/**
	 * Method to simulate Move to check if it's correct etc. (usable for network
	 * game).
	 * 
	 * @param beginX
	 *            from which X (on chessboard) move starts
	 * @param beginY
	 *            from which Y (on chessboard) move starts
	 * @param endX
	 *            to which X (on chessboard) move go
	 * @param endY
	 *            to which Y (on chessboard) move go
	 */
	public boolean simulateMove(int beginX, int beginY, int endX, int endY) {
		try {
			chessboard.select(chessboard.getSquares()[beginX][beginY]);
			if (chessboard.getActiveSquare().piece.allMoves(false).indexOf(chessboard.getSquares()[endX][endY]) != -1) // move
			{
				chessboard.move(chessboard.getSquares()[beginX][beginY], chessboard.getSquares()[endX][endY]);
			} else {
				System.out.println("Bad move");
				return false;
			}
			chessboard.unselect();
			nextMove();

			return true;

		} catch (StringIndexOutOfBoundsException exc) {
			return false;
		} catch (ArrayIndexOutOfBoundsException exc) {
			return false;
		} catch (NullPointerException exc) {
			return false;
		} finally {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, "ERROR");
		}
	}

	public boolean undo() {
		boolean status = false;

		if (this.getSettings().gameType == Settings.gameTypes.local) {
			status = chessboard.undo();
			if (status) {
				this.switchActive();
			} else {
				chessboard.getDisplay().repaint();// repaint for sure
			}
		} else if (this.getSettings().gameType == Settings.gameTypes.network) {
			this.getClient().sendUndoAsk();
			status = true;
		}
		return status;
	}

	public boolean rewindToBegin() {
		boolean result = false;

		if (this.getSettings().gameType == Settings.gameTypes.local) {
			while (chessboard.undo()) {
				result = true;
			}
		} else {
			throw new UnsupportedOperationException(Settings.lang("operation_supported_only_in_local_game"));
		}

		return result;
	}

	public boolean rewindToEnd() throws UnsupportedOperationException {
		boolean result = false;

		if (this.getSettings().gameType == Settings.gameTypes.local) {
			while (chessboard.redo()) {
				result = true;
			}
		} else {
			throw new UnsupportedOperationException(Settings.lang("operation_supported_only_in_local_game"));
		}

		return result;
	}

	public boolean redo() {
		boolean status = chessboard.redo();
		if (this.getSettings().gameType == Settings.gameTypes.local) {
			if (status) {
				this.nextMove();
			} else {
				chessboard.getDisplay().repaint();// repaint for sure
			}
		} else {
			throw new UnsupportedOperationException(Settings.lang("operation_supported_only_in_local_game"));
		}
		return status;
	}

	public void mousePressed(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON3) // right button
		{
			this.undo();
		} else if (event.getButton() == MouseEvent.BUTTON2 && getSettings().gameType == Settings.gameTypes.local) {
			this.redo();
		} else if (event.getButton() == MouseEvent.BUTTON1) // left button
		{

			if (!blockedChessboard) {
				try {
					int x = event.getX();// get X position of mouse
					int y = event.getY();// get Y position of mouse

					Square sq = chessboard.getSquare(x, y);
					if ((sq == null && chessboard.getActiveSquare() == null)
							|| (this.chessboard.getActiveSquare() == null && sq.piece != null
									&& sq.piece.getPlayer() != this.activePlayer)) {
						return;
					}

					if (sq.piece != null && sq.piece.getPlayer() == this.activePlayer
							&& sq != chessboard.getActiveSquare()) {
						chessboard.unselect();
						chessboard.select(sq);
					} else if (chessboard.getActiveSquare() == sq) // unselect
					{
						chessboard.unselect();
					} else if (chessboard.getActiveSquare() != null && chessboard.getActiveSquare().piece != null
							&& chessboard.getActiveSquare().piece.allMoves(false).indexOf(sq) != -1) // move
					{
						if (getSettings().gameType == Settings.gameTypes.local) {
							chessboard.move(chessboard.getActiveSquare(), sq);
						} else if (getSettings().gameType == Settings.gameTypes.network) {
							getClient().sendMove(chessboard.getActiveSquare().getPozX(),
									chessboard.getActiveSquare().getPozY(), sq.getPozX(), sq.getPozY());
							chessboard.move(chessboard.getActiveSquare(), sq);
						}

						chessboard.unselect();

						// switch player
						this.nextMove();

						// checkmate or stalemate
						King king = chessboard.getKing(this.activePlayer);

						switch (king.isCheckmatedOrStalemated()) {
						case 1:
							this.endGame("Checkmate! " + king.getPlayer().getColor().toString() + " player lose!");
							break;
						case 2:
							this.endGame("Stalemate! Draw!");
							break;
						}
					}

				} catch (NullPointerException exc) {
					// System.err.println(exc.getMessage());
					LogToFile.log(exc, "Error", "NullPointerException " + exc.getMessage());
					chessboard.getDisplay().repaint();
					return;
				}
			} else if (blockedChessboard) {
				// System.out.println("Chessboard is blocked");
				LogToFile.log(null, "INFO", "Chessboard is blocked");
			}
		}
		// chessboard.repaint();
	}

	public void componentResized(ComponentEvent e) {
		int height = this.getHeight() >= this.getWidth() ? this.getWidth() : this.getHeight();
		int chess_height = (int) Math.round((height * 0.8) / 8) * 8;
		this.chessboard.getDisplay().resizeChessboard((int) chess_height);
		chess_height = this.chessboard.getDisplay().getHeight();
		this.getMoves().getScrollPane().setLocation(new Point(chess_height + 5, 100));
		this.getMoves().getScrollPane().setSize(this.getMoves().getScrollPane().getWidth(), chess_height - 100);
		this.getGameClock().setLocation(new Point(chess_height + 5, 0));
		if (this.getChat() != null) {
			this.getChat().setLocation(new Point(0, chess_height + 5));
			this.getChat().setSize(new Dimension(chess_height, this.getHeight() - (chess_height + 5)));
		}
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public IChessboard getChessboard() {
		return chessboard;
	}

	public void setChessboard(SquareBoard chessboard) {
		this.chessboard = chessboard;
	}

	public MovesTable getMoves() {
		return moves;
	}

	public void setMoves(MovesTable moves) {
		this.moves = moves;
	}

	public GameClock getGameClock() {
		return gameClock;
	}

	public void setGameClock(GameClock gameClock) {
		this.gameClock = gameClock;
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

class ReadGameError extends Exception {
}