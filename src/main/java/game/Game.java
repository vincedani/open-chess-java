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

import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.LogToFile;
import main.java.board.IChessboard;
import main.java.board.IKing;
import main.java.board.IKing.KingState;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.pieces.Piece;
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

	public Game() {

		this.blockedChessboard = false;
		this.setLayout(null);
		this.addComponentListener(this);
		this.setDoubleBuffered(true);
	}

	private void initializeChessboardPanel() {
		
		chessboard.getDisplay().setVisible(true);
		chessboard.getDisplay().addMouseListener(this);
		chessboard.getDisplay().setLocation(new Point(0, 0));
		this.add(chessboard.getDisplay());
	}

	public void newGame(Settings gameSettings) {

		switch (gameSettings.boardType) {
		case circleBoard:
			chessboard = new CircleBoard();
			break;
		case squareBoard:
			chessboard = new SquareBoard(gameSettings);
			break;
		default:
			break;
		}

		chessboard.setPieces(gameSettings.players);
		activePlayer = gameSettings.players[0];
		this.blockedChessboard = false;
		this.settings = gameSettings;

		
		this.chessboard.getDisplay().revalidate();
		this.chessboard.getDisplay().repaint();
		initializeChessboardPanel();
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
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * Method to switch active players after move
	 */
	public void switchActivePlayer() {

		activePlayer = getSettings().nextPlayer(activePlayer);

	}

	/**
	 * Method to go to next move (checks if game is local/network etc.)
	 */
	public void nextMove() {
		switchActivePlayer();

		System.out.println("next move, active player: " + activePlayer.name + ", color: "
				+ activePlayer.getColor().name() + ", type: " + activePlayer.playerType.name());

	}

	@Override
	public void mousePressed(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1) // left button
		{

			if (!blockedChessboard) {
				try {
					int x = event.getX();// get X position of mouse
					int y = event.getY();// get Y position of mouse

					Square sq = chessboard.getSquareFromCoordinates(x, y);

					if (sq != null) {
						if (sq.getPiece() != null && sq.getPlayer() == this.activePlayer
								&& sq != chessboard.getActiveSquare()) {
							// Select piece and display possible moves
							chessboard.unselect();
							chessboard.select(sq);
						} else if (chessboard.getActiveSquare() == sq) {
							// Unselect
							chessboard.unselect();
						} else if (chessboard.getActiveSquare() != null
								&& chessboard.getActiveSquare().getPiece() != null
								&& chessboard.getActiveSquare().getPiece().allMoves(chessboard, false).indexOf(sq) != -1) {
							// Move

							chessboard.move(chessboard.getActiveSquare(), sq, true);
							chessboard.unselect();
							checkGameState();
							this.nextMove();

						}
					}

				} catch (NullPointerException exc) {
					exc.printStackTrace();
					LogToFile.log(exc, "Error", "NullPointerException " + exc.getMessage());
					exc.printStackTrace();
					chessboard.getDisplay().repaint();
					return;
				}
			}
		} else if (blockedChessboard) {
			LogToFile.log(null, "INFO", "Chessboard is blocked");
		}
	}

	private void checkGameState() {
		// checkmate or stalemate
		for (Player player : this.getSettings().players) {
			Piece king = chessboard.getKing(player);
			IKing kingBeh = (IKing) king.getMoveBehaviour();
			KingState kingState = kingBeh.isCheckmatedOrStalemated(chessboard, king);
			if (kingState.equals(IKing.KingState.checkmate)) {
				this.endGame("Checkmate! " + king.getPlayer().getColor().toString() + " player lose!");
			} else if (kingState.equals(IKing.KingState.stalemate)) {
				this.endGame("Stalemate! Draw!");
			} else {
				System.out.println("The King "+ king.getPlayer().getColor()+" is ok!");
			}
		}

	}

	@Override
	public void componentResized(ComponentEvent e) {
		int height = this.getHeight() >= this.getWidth() ? this.getWidth() : this.getHeight();
		int chessHeight = (int) Math.round((height * 0.8) / 8) * 8;
		this.chessboard.getDisplay().resizeChessboard((int) chessHeight);

	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public IChessboard getChessboard() {
		return chessboard;
	}

	public void setChessboard(SquareBoard chessboard) {
		this.chessboard = chessboard;
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		}

	@Override
	public void mouseClicked(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
	}
}
