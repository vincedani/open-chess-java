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

import main.java.JChessApp;
import main.java.LogToFile;
import main.java.board.ChessboardDisplay;
import main.java.board.ChessboardLayout;
import main.java.board.IChessboard;
import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.pieces.Piece;
import main.java.pieces.PieceBehaviour;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;

/**
 * Class to represent a SquareChessboard from 8x8 Squares for a two player Chess
 * game.
 */
public class SquareBoard implements IChessboard {

	ArrayList<Square> moves;
	public static final int bottom = 7;

	public boolean breakCastling;

	// ----------------------------
	// For En passant:
	// |-> Pawn whose in last turn moved two square
	public static Piece twoSquareMovedPawn = null;
	public static Piece twoSquareMovedPawn2 = null;

	ChessboardLayout board_layout = new ChessboardLayout("square_chessboard.png", "sel_square.png", "able_square.png");
	public SquareBoardInitialization initial;
	private SquareBoardDisplay display;
	PieceBehaviour pieceBehaviour;
	/**
	 * Chessboard class constructor
	 * 
	 * @param settings
	 *            reference to Settings class object for this chessboard
	 */
	public SquareBoard(Settings settings) {
		initial = new SquareBoardInitialization(this);
		display = new SquareBoardDisplay(null, null, new Point(0, 0), this);
		pieceBehaviour = new PieceBehaviour(this);

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
	public Square getSquareFromCoordinates(int x, int y) {
		if ((x > this.get_height(false)) || (y > this.get_widht(false))) {
			LogToFile.log(null, "INFO", "click out of chessboard.");
			return null;
		}
		x -= this.display.upDownLabel.getHeight(null);
		y -= this.display.upDownLabel.getHeight(null);

		double square_x = (int) (x / display.square_height);// count which field
		double square_y = (int) (y / display.square_height);// count which field

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
		LogToFile.log(null, "INFO",
				"active_x: " + this.display.activeSquare.getPosX() + " active_y: " + this.display.activeSquare.getPosY());
		display.repaint();

	}

	/**
	 * Method set variables active_x_square and active_y_square to default
	 * values (-1).
	 */
	public void unselect() {
		this.display.activeSquare = null;
		display.repaint();
	}

	public int get_widht(boolean includeLables) {
		return this.display.getHeight();
	}/*--endOf-get_widht--*/

	public int get_height(boolean includeLabels) {
		if (includeLabels)
			return board_layout.image.getHeight(null) + display.upDownLabel.getHeight(null);
		else
			return board_layout.image.getHeight(null);

	}/*--endOf-get_height--*/

	public int get_square_height() {
		return (int) this.display.square_height;
	}

	/**
	 * Method move piece from square to square
	 * 
	 * @param begin
	 *            square from which move piece
	 * @param end
	 *            square where we want to move piece *
	 * @param displayWindow
	 *            check if show instructions window, false for testing
	 */

	public void move(Square begin, Square end, Boolean displayWindow) {

		if (end.getPiece() != null) {
			end.getPiece().setSquare(null);
		}

		Square tempEnd = new Square(end);

		breakCastling = false;
		twoSquareMovedPawn2 = twoSquareMovedPawn;

		if (!begin.getPiece().wasMoved()) {
			begin.getPiece().setWasMoved(true);

			if (begin.getPiece().getType().equals(PieceType.King)
					|| begin.getPiece().getType().equals(PieceType.Rook)) {
				breakCastling = true;
			}
		}

		begin.getPiece().setSquare(end);// set square of piece to ending
		end.setPiece(begin.getPiece());// for ending square set piece from
										// beginning
		// square
		begin.setPiece(null);// make null piece for beginning square

		if (end.getPiece().getType().equals(PieceType.King)) {

			// Castling
			if (begin.getPosX() + 2 == end.getPosX()) {
				move(initial.getSquares()[7][begin.getPosY()], initial.getSquares()[end.getPosX() - 1][begin.getPosY()],
						false);

			} else if (begin.getPosX() - 2 == end.getPosX()) {
				move(initial.getSquares()[0][begin.getPosY()], initial.getSquares()[end.getPosX() + 1][begin.getPosY()],
						false);

			}

		} else if (end.getPiece().getType().equals(PieceType.Pawn)) {
			if (twoSquareMovedPawn != null
					&& initial.getSquares()[end.getPosX()][begin.getPosY()] == twoSquareMovedPawn.getSquare()) // en
																												// passant
			{
				tempEnd.setPiece(initial.getSquares()[end.getPosX()][begin.getPosY()].getPiece()); // ugly
				// hack
				initial.getSquares()[end.getPosX()][begin.getPosY()].setPiece(null);
			}

			if (begin.getPosY() - end.getPosY() == 2 || end.getPosY() - begin.getPosY() == 2) // moved
																								// two
																								// square
			{
				breakCastling = true;
				twoSquareMovedPawn = end.getPiece();
			} else {
				twoSquareMovedPawn = null;
			}

			if (end.getPiece().getSquare().getPosY() == 0 || end.getPiece().getSquare().getPosY() == 7) {
				// Promote
					String color = null;
					if (end.getPiece().getPlayer().getColor() == Player.colors.white) {
						color = "White";
					} else if (end.getPiece().getPlayer().getColor() == Player.colors.black) {
						color = "Black";
					} else if (end.getPiece().getPlayer().getColor() == Player.colors.blue) {
						color = "Blue";
					}

					String newPiece = JChessApp.getJcv().showPawnPromotionBox(color);
					if (newPiece.equals("Queen")) {
						end.setPiece(PieceFactory.createSpecificPieceForSquareBoard(this, end.getPiece().getPlayer(),
								PieceType.Queen));
					} else if (newPiece.equals("Rook")) {
						end.setPiece(PieceFactory.createSpecificPieceForSquareBoard(this, end.getPiece().getPlayer(),
								PieceType.Rook));

					} else if (newPiece.equals("Bishop")) {
						end.setPiece(PieceFactory.createSpecificPieceForSquareBoard(this, end.getPiece().getPlayer(),
								PieceType.Bishop));

					} else if (newPiece.equals("Knight")) {
						end.setPiece(PieceFactory.createSpecificPieceForSquareBoard(this, end.getPiece().getPlayer(),
								PieceType.Knight));
					}
				}
			
		} else if (!end.getPiece().getType().equals(PieceType.Pawn))

		{
			twoSquareMovedPawn = null; // erase last saved move (for En passant)
		}

		this.unselect();// unselect square
		display.repaint();

	}/* endOf-move()- */

	public ChessboardDisplay getDisplay() {
		return display;
	}

	public void setPieces(Player[] players) {
		initial.setPieces(players);
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

	public Piece getKing(Player player) {
		if (player.getColor().equals(Player.colors.white)) {
			return initial.kingWhite;
		} else if (player.getColor().equals(Player.colors.black)) {
			return initial.kingBlack;
		}else if (player.getColor().equals(Player.colors.blue)) {
			return initial.kingBlue;
		}
		return null;
	}

	public Piece getTwoSquareMovedPawn() {
		return twoSquareMovedPawn;
	}

	@Override
	public Square getSquareFromIndexes(int i, int j) {
		return initial.getSquares()[i][j];
	}

	@Override
	public PieceBehaviour getPieceBehaviour() {
		return pieceBehaviour;
	}
	
	public void setKing(Piece king){
		initial.setKing(king);
	}

}
