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
package jchess.pieces;

import java.util.ArrayList;

import jchess.board.Chessboard;
import jchess.board.Square;
import jchess.game.Player;
import jchess.gui.GUI;

import java.awt.Image;

/**
 * Class to represent a queen piece Queen can move almost in every way:
 * |_|_|_|X|_|_|_|X|7 
 * |X|_|_|X|_|_|X|_|6 
 * |_|X|_|X|_|X|_|_|5 
 * |_|_|X|X|x|_|_|_|4
 * |X|X|X|Q|X|X|X|X|3 
 * |_|_|X|X|X|_|_|_|2 
 * |_|X|_|X|_|X|_|_|1 
 * |X|_|_|X|_|_|X|_|0 
 *  0 1 2 3 4 5 6 7
 */
public class Queen extends Piece {

	public static short value = 9;
	protected static final Image imageWhite = GUI.loadImage("Queen-W.png");
	protected static final Image imageBlack = GUI.loadImage("Queen-B.png");

	public Queen(Chessboard chessboard, Player player) {
		super(chessboard, player);// call initializer of super type: Piece
		// this.setImages("Queen-W.png", "Queen-B.png");
		this.symbol = "Q";
		this.setImage(imageBlack, imageWhite);
	}

	/**
	 * Annotation to superclass Piece changing pawns location
	 * 
	 * @return ArrayList with new possition of piece
	 */

	public ArrayList<Square> allMoves(Chessboard chessboard) {
		ArrayList<Square> list = new ArrayList<Square>();
		int x = this.getSquare().getPozX(), y = this.getSquare().getPozY();

		// ------------- as Rook --------------
		King myKing = myKing(chessboard);
		for (int i = y + 1; i <= 7; ++i) {// up

			if (this.pieceBehaviour.checkPiece(x, i)) {
				// if on this square isn't piece
				Square newMove = chessboard.squares[x][i];

				if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (this.pieceBehaviour.otherOwner(x, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}

		}

		for (int i = y - 1; i >= 0; --i) {// down

			if (this.pieceBehaviour.checkPiece(this.getSquare().getPozX(), i)) {
				// if on this square isn't piece
				Square newMove = chessboard.squares[x][i];

				if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (this.pieceBehaviour.otherOwner(x, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}

		for (int i = x - 1; i >= 0; --i) {// left

			if (this.pieceBehaviour.checkPiece(i, this.getSquare().getPozY())) {
				// if on this square isn't piece
				Square newMove = chessboard.squares[i][y];

				if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (this.pieceBehaviour.otherOwner(i, y)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}

		for (int i = x + 1; i <= 7; ++i) {// right

			if (this.pieceBehaviour.checkPiece(i, this.getSquare().getPozY())) {
				// if on this square isn't piece
				Square newMove = chessboard.squares[i][y];

				if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (this.pieceBehaviour.otherOwner(i, y)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}

		// ------------- as Bishop ------------------
		for (int h = x - 1, i = y + 1; !pieceBehaviour.isout(h, i); --h, ++i) // left-up
		{
			if (this.pieceBehaviour.checkPiece(h, i)) // if on this square isn't
														// piece
			{
				if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[h][i])) {
					list.add(getChessboard().squares[h][i]);
				}

				if (this.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}

		for (int h = x - 1, i = y - 1; !pieceBehaviour.isout(h, i); --h, --i) // left-down
		{
			if (this.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't piece
			{
				if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[h][i])) {
					list.add(getChessboard().squares[h][i]);
				}

				if (this.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}

		for (int h = x + 1, i = y + 1; !pieceBehaviour.isout(h, i); ++h, ++i) // right-up
		{
			if (this.pieceBehaviour.checkPiece(h, i)) // if on this square isn't
														// piece
			{
				if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[h][i])) {
					list.add(getChessboard().squares[h][i]);
				}

				if (this.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}

		for (int h = x + 1, i = y - 1; !pieceBehaviour.isout(h, i); ++h, --i) // right-down
		{
			if (this.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't piece
			{
				if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[h][i])) {
					list.add(getChessboard().squares[h][i]);
				}

				if (this.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
		// ------------------------------------

		return list;
	}
}
