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
package main.java.squareBoard;

import java.awt.Point;
import java.util.ArrayList;

import main.java.Constants;
import main.java.JChessApp;
import main.java.LogToFile;
import main.java.board.ChessboardDisplay;
import main.java.board.ChessboardLayout;
import main.java.board.IChessboard;
import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.pieces.ConcretePieceFactory;
import main.java.pieces.King;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

/**
 * Class to represent a SquareChessboard from 8x8 Squares for a two player Chess
 * game.
 */
public class SquareBoard implements IChessboard {

	ArrayList<Square> moves;
	private Settings settings;

	public static final int bottom = 7;
	
	public boolean breakCastling;

	// ----------------------------
	// For En passant:
	// |-> Pawn whose in last turn moved two square
	public static Piece twoSquareMovedPawn = null;
	public static Piece twoSquareMovedPawn2 = null;

	ChessboardLayout board_layout = new ChessboardLayout("chessboard.png", "sel_square.png", "able_square.png");
	public SquareBoardInitialization initial;
	private SquareBoardDisplay display;

	/**
	 * Chessboard class constructor
	 * 
	 * @param settings
	 *            reference to Settings class object for this chessboard
	 * @param moves_history
	 *            reference to MovesTable class object for this chessboard
	 */
	public SquareBoard(Settings settings) {
		this.settings = settings;
		initial = new SquareBoardInitialization(settings.upsideDown, this);
		display = new SquareBoardDisplay(null, null, new Point(0, 0), this);

	}/*--endOf-Chessboard--*/

	/**
	 * method to get reference to square from given x and y integeres
	 * 
	 * @param x
	 *            x position on chessboard
	 * @param y
	 *            y position on chessboard
	 * @return reference to searched square
	 */
	public Square getSquare(int x, int y) {
		if ((x > this.get_height(false)) || (y > this.get_widht(false))) {
			LogToFile.log(null, "INFO", "click out of chessboard.");
			return null;
		}
		x -= this.display.upDownLabel.getHeight(null);
		y -= this.display.upDownLabel.getHeight(null);

		double square_x = (int) (x / display.square_height);// count which field
		double square_y = (int) (y / display.square_height);// count which field

		/**
		 * if (square_x > (int) square_x) // if X is more than X parsed to
		 * Integer { square_x = (int) square_x + 1;// parse to integer and
		 * increment } if (square_y > (int) square_y) // if X is more than X
		 * parsed to Integer { square_y = (int) square_y + 1;// parse to integer
		 * and increment }
		 **/

		try {
			return initial.getSquares()[(int) square_x][(int) square_y];
		} catch (java.lang.ArrayIndexOutOfBoundsException exc) {
			System.out.println("!!Array out of bounds when getting Square with Chessboard.getSquare(int,int) : " + exc);
			LogToFile.log(exc, "ERROR", "Array out of bounds when getting Square with Chessboard.getSquare(int,int)");
			return null;
		}
	}

	/**
	 * Method selecting piece in chessboard
	 * 
	 * @param sq
	 *            square to select (when clicked))
	 */
	public void select(Square sq) {
		this.display.activeSquare = sq;
		this.display.active_x_square = sq.getPozX();
		this.display.active_y_square = sq.getPozY();
		LogToFile.log(null, "INFO",
				"active_x: " + this.display.active_x_square + " active_y: " + this.display.active_y_square);
		display.repaint();

	}

	/**
	 * Method set variables active_x_square and active_y_square to default
	 * values (-1).
	 */
	public void unselect() {
		this.display.active_x_square = -1;
		this.display.active_y_square = -1;
		this.display.activeSquare = null;
		display.repaint();
	}

	public int get_widht(boolean includeLables) {
		return this.display.getHeight();
	}/*--endOf-get_widht--*/

	public int get_height(boolean includeLabels) {
		return board_layout.image.getHeight(null) + display.upDownLabel.getHeight(null);

	}/*--endOf-get_height--*/

	public int get_square_height() {
		return (int) this.display.square_height;
	}

	public void move(Square begin, Square end) {
		move(begin, end, true);
	}

	public void move(Square begin, Square end, boolean refresh) {
		this.move(begin, end, refresh, true);
	}

	/**
	 * Method move piece from square to square
	 * 
	 * @param begin
	 *            square from which move piece
	 * @param end
	 *            square where we want to move piece *
	 * @param refresh
	 *            chessboard, default: true
	 */

	public void move(Square begin, Square end, boolean refresh, boolean clearForwardHistory) {

		Piece promotedPiece = null;
		boolean wasEnPassant = false;
		ConcretePieceFactory pieceFac = new ConcretePieceFactory();
		
		if (end.piece != null) {
			end.piece.setSquare(null);
		}

		Square tempBegin = new Square(begin);
		Square tempEnd = new Square(end);
		
		breakCastling = false;
		twoSquareMovedPawn2 = twoSquareMovedPawn;

		if (!begin.piece.wasMoved()) {
			begin.piece.setWasMoved(true);

			if (begin.piece.getName().equals("King") || begin.piece.getName().equals("Rook")) {
				breakCastling = true;
			}
		}

		begin.piece.setSquare(end);// set square of piece to ending
		end.piece = begin.piece;// for ending square set piece from beginning
								// square
		begin.piece = null;// make null piece for beginning square

		if (end.piece.getName().equals("King")) {

			// Castling
			if (begin.getPozX() + 2 == end.getPozX()) {
				move(initial.getSquares()[7][begin.getPozY()], initial.getSquares()[end.getPozX() - 1][begin.getPozY()],
						false, false);
				
			} else if (begin.getPozX() - 2 == end.getPozX()) {
				move(initial.getSquares()[0][begin.getPozY()], initial.getSquares()[end.getPozX() + 1][begin.getPozY()],
						false, false);
				
			}
			
		} else if (end.piece.getName().equals("Pawn")) {
			if (twoSquareMovedPawn != null
					&& initial.getSquares()[end.getPozX()][begin.getPozY()] == twoSquareMovedPawn.getSquare()) // en
																												// passant
			{
				tempEnd.piece = initial.getSquares()[end.getPozX()][begin.getPozY()].piece; // ugly
																							// hack
				initial.getSquares()[end.getPozX()][begin.getPozY()].piece = null;
				wasEnPassant = true;
			}

			if (begin.getPozY() - end.getPozY() == 2 || end.getPozY() - begin.getPozY() == 2) // moved
																								// two
																								// square
			{
				breakCastling = true;
				twoSquareMovedPawn = end.piece;
			} else {
				twoSquareMovedPawn = null; // erase last saved move (for En
											// passant)
			}

			if (end.piece.getSquare().getPozY() == 0 || end.piece.getSquare().getPozY() == 7) // promote
																								// Pawn
			{
				if (clearForwardHistory) {
					String color;
					if (end.piece.getPlayer().getColor() == Player.colors.white) {
						color = "W"; // promotionWindow was show with pieces in
										// this color
					} else {
						color = "B";
					}

					String newPiece = JChessApp.getJcv().showPawnPromotionBox(color); // return

					if (newPiece.equals("Queen")) // transform pawn to queen
					{
						/*end.piece = PieceFactory.createQueenInSquareBoard(end.piece.getChessboard(),
								end.piece.getPlayer());*/
						end.piece = pieceFac.GetPieceForSquareBoard(Constants.Symbols.Queen.toString(),Constants.Pieces.Queen.toString(), end.piece.getChessboard(), end.piece.getPlayer());
					} else if (newPiece.equals("Rook")) // transform pawn to
														// rook
					{
						//PieceFactory.createRookInSquareBoard(end.piece.getChessboard(), end.piece.getPlayer());
						pieceFac.GetPieceForSquareBoard(Constants.Symbols.Rook.toString(),Constants.Pieces.Rook.toString(), end.piece.getChessboard(), end.piece.getPlayer());
					} else if (newPiece.equals("Bishop")) // transform pawn to
															// bishop
					{
						//PieceFactory.createBishopInSquareBoard(end.piece.getChessboard(), end.piece.getPlayer());
						pieceFac.GetPieceForSquareBoard(Constants.Symbols.Bishop.toString(),Constants.Pieces.Bishop.toString(), end.piece.getChessboard(), end.piece.getPlayer());
					} else // transform pawn to knight
					{
						//PieceFactory.createKnightInSquareBoard(end.piece.getChessboard(), end.piece.getPlayer());
						pieceFac.GetPieceForSquareBoard(Constants.Symbols.Knight.toString(),Constants.Pieces.Knight.toString(), end.piece.getChessboard(), end.piece.getPlayer());
					}
					promotedPiece = end.piece;
				}
			}
		} else if (!end.piece.getName().equals("Pawn")) {
			twoSquareMovedPawn = null; // erase last saved move (for En passant)
		}
		// }

		
			this.unselect();// unselect square
			display.repaint();
		

	}/* endOf-move()- */

	public ChessboardDisplay getDisplay() {
		return display;
	}

	public void setPieces(Player[] players) {
		initial.setPieces(players[0], players[1]);
	}

	public Square[][] getSquares() {
		return initial.getSquares();
	}

	public SquareBoardInitialization getInitial() {
		return initial;
	}

	public Square getActiveSquare() {
		return display.activeSquare;
	}

	public void setActiveSquare(Square sq) {
		display.activeSquare = sq;
	}

	public King getKing(Player player) {
		if (player.getColor().equals(Player.colors.white)) {
			return initial.kingWhite;
		} else if (player.getColor().equals(Player.colors.black)) {
			return initial.kingBlack;
		} 
		return null;
	}

	public Piece getTwoSquareMovedPawn() {
		return twoSquareMovedPawn;
	}

	@Override
	public boolean undo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean redo() {
		// TODO Auto-generated method stub
		return false;
	}
}
