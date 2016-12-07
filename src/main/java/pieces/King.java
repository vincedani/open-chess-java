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
package main.java.pieces;

/**
 * Class to represent a chess pawn king. King is the most important
 * piece for the game. Lose of king is the end of game.
 * When king is in danger by the opponent then it's a Checked, and when have
 * no other escape then stay on a square "in danger" by the opponent
 * then it's a CheckedMate, and the game is over.
 *
 *      |_|_|_|_|_|_|_|_|7
        |_|_|_|_|_|_|_|_|6
        |_|_|_|_|_|_|_|_|5
        |_|_|X|X|X|_|_|_|4
        |_|_|X|K|X|_|_|_|3
        |_|_|X|X|X|_|_|_|2
        |_|_|_|_|_|_|_|_|1
        |_|_|_|_|_|_|_|_|0
        0 1 2 3 4 5 6 7
 */
import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;

public class King extends Piece {

	public boolean wasMotion = false;// maybe change to: 'wasMotioned'
	// public boolean checked = false;
	public static short value = 99;
	ArrayList<IMove> moveBehaviour;

	public King(IChessboard chessboard, Player player) {

		super(chessboard, player, "King"); // call initializer of super type: Piece 
		this.symbol = "K";
		this.moveBehaviour = moveBehaviour;

	}

	
	public void regularMove(IChessboard chessboard, int x, int y, ArrayList<Square> list){
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!this.pieceBehaviour.isout(i, j)) {// out of bounds
														// protection
					Square sq = chessboard.getSquares()[i][j];
					if (this.getSquare() == sq) {// if we're checking square on
													// which is King
						continue;
					} else {
						if (this.pieceBehaviour.checkPiece(i, j)) {// if square
																	// is empty
							if (isSafe(sq)) {
								list.add(sq);
							}
						}
					}
				}
			}
		}

	}
	
	public void castlingLeftMove(IChessboard chessboard, int x, int y, ArrayList<Square> list){

			boolean canCastling = true;

			Rook rook = (Rook) chessboard.getSquares()[0][y].piece;
			if (!rook.wasMotion()) {
				for (int i = x - 1; i > 0; i--) {// go
																			// left
					if (chessboard.getSquares()[i][y].piece != null) {
						canCastling = false;
						break;
					}
				}
				
				Square sq = chessboard.getSquares()[x - 2][y];
				Square sq1 = chessboard.getSquares()[x - 1][y];
				if (canCastling && this.isSafe(sq) && this.isSafe(sq1)) { 
					// can do castling when neither sq nor sq1 is checked
					list.add(sq);
				}
			}
		}
	
	public void castlingRightMove(IChessboard chessboard, int x, int y, ArrayList<Square> list){

		boolean canCastling = true;

		Rook rook = (Rook) chessboard.getSquares()[7][y].piece;
		if (!rook.wasMotion()) {
			for (int i = x + 1; i < 7; i++) {// go
																		// left
				if (chessboard.getSquares()[i][y].piece != null) {
					canCastling = false;
					break;
				}
			}
			
			Square sq = chessboard.getSquares()[x + 2][y];
			Square sq1 = chessboard.getSquares()[x + 1][y];
			if (canCastling && this.isSafe(sq) && this.isSafe(sq1)) { 
				// can do castling when neither sq nor sq1 is checked
				list.add(sq);
			}
		}
	}

	/**
	 * Annotation to superclass Piece changing pawns location
	 * 
	 * @return ArrayList with new position of piece
	 */
	public ArrayList<Square> allMoves(IChessboard chessboard) {
		ArrayList<Square> list = new ArrayList<Square>();

		int x = this.getSquare().getPozX(), y = this.getSquare().getPozY();
		regularMove(chessboard, x, y, list);
		
		if (!this.wasMotion && !this.isChecked()) {
			// check if king was not moved before

			if (chessboard.getSquares()[0][y].piece != null
					&& getChessboard().getSquares()[0][y].piece.getName().equals("Rook")) {
				castlingLeftMove(chessboard, x, y, list);
			}
			if (chessboard.getSquares()[7][y].piece != null
					&& chessboard.getSquares()[7][y].piece.getName().equals("Rook")) {
				castlingRightMove(chessboard, x, y, list);
			}
		}
		return list;
	}

	/**
	 * Method to check is the king is checked
	 * 
	 * @return bool true if king is not save, else returns false
	 */
	public boolean isChecked() {
		return !isSafe(this.getSquare());
	}

	/**
	 * Method to check is the king is checked or stalemated
	 * 
	 * @return int 0 if nothing, 1 if checkmate, else returns 2
	 */
	public int isCheckmatedOrStalemated() {
		/*
		 * returns: 0-nothing, 1-checkmate, 2-stalemate
		 */
		if (this.allMoves(this.getChessboard()).size() == 0) {
			for (int i = 0; i < 8; ++i) {
				for (int j = 0; j < 8; ++j) {
					if (getChessboard().getSquares()[i][j].piece != null
							&& getChessboard().getSquares()[i][j].piece.getPlayer() == this.getPlayer()
							&& getChessboard().getSquares()[i][j].piece.allMoves(this.getChessboard()).size() != 0) {
						return 0;
					}
				}
			}

			if (this.isChecked()) {
				return 1;
			} else {
				return 2;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Method to check is the king is checked by an opponent
	 * 
	 * @param s
	 *            Squere where is a king
	 * @return bool true if king is save, else returns false
	 */
	private boolean isSafe(Square s) // A bit confusing code.
	{
		// Rook & Queen
		for (int i = s.getPozY() + 1; i <= 7; ++i) // up
		{
			if (this.getChessboard().getSquares()[s.getPozX()][i].piece == null
					|| this.getChessboard().getSquares()[s.getPozX()][i].piece == this) // if
																					// on
																					// this
																					// sqhuare
																					// isn't
																					// piece
			{
				continue;
			} else if (this.getChessboard().getSquares()[s.getPozX()][i].piece.getPlayer() != this.getPlayer()) // if
																											// isn't
																											// our
																											// piece
			{
				if (this.getChessboard().getSquares()[s.getPozX()][i].piece.getName().equals("Rook")
						|| this.getChessboard().getSquares()[s.getPozX()][i].piece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = s.getPozY() - 1; i >= 0; --i) // down
		{
			if (this.getChessboard().getSquares()[s.getPozX()][i].piece == null
					|| this.getChessboard().getSquares()[s.getPozX()][i].piece == this) // if
																					// on
																					// this
																					// sqhuare
																					// isn't
																					// piece
			{
				continue;
			} else if (this.getChessboard().getSquares()[s.getPozX()][i].piece.getPlayer() != this.getPlayer()) // if
																											// isn't
																											// our
																											// piece
			{
				if (this.getChessboard().getSquares()[s.getPozX()][i].piece.getName().equals("Rook")
						|| this.getChessboard().getSquares()[s.getPozX()][i].piece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = s.getPozX() - 1; i >= 0; --i) // left
		{
			if (this.getChessboard().getSquares()[i][s.getPozY()].piece == null
					|| this.getChessboard().getSquares()[i][s.getPozY()].piece == this) // if
																					// on
																					// this
																					// sqhuare
																					// isn't
																					// piece
			{
				continue;
			} else if (this.getChessboard().getSquares()[i][s.getPozY()].piece.getPlayer() != this.getPlayer()) // if
																											// isn't
																											// our
																											// piece
			{
				if (this.getChessboard().getSquares()[i][s.getPozY()].piece.getName().equals("Rook")
						|| this.getChessboard().getSquares()[i][s.getPozY()].piece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = s.getPozX() + 1; i <= 7; ++i) // right
		{
			if (this.getChessboard().getSquares()[i][s.getPozY()].piece == null
					|| this.getChessboard().getSquares()[i][s.getPozY()].piece == this) // if
																					// on
																					// this
																					// sqhuare
																					// isn't
																					// piece
			{
				continue;
			} else if (this.getChessboard().getSquares()[i][s.getPozY()].piece.getPlayer() != this.getPlayer()) // if
																											// isn't
																											// our
																											// piece
			{
				if (this.getChessboard().getSquares()[i][s.getPozY()].piece.getName().equals("Rook")
						|| this.getChessboard().getSquares()[i][s.getPozY()].piece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// Bishop & Queen
		for (int h = s.getPozX() - 1, i = s.getPozY() + 1; !pieceBehaviour.isout(h, i); --h, ++i) // left-up
		{
			if (this.getChessboard().getSquares()[h][i].piece == null || this.getChessboard().getSquares()[h][i].piece == this) // if
																														// on
																														// this
																														// sqhuare
																														// isn't
																														// piece
			{
				continue;
			} else if (this.getChessboard().getSquares()[h][i].piece.getPlayer() != this.getPlayer()) // if
																									// isn't
																									// our
																									// piece
			{
				if (this.getChessboard().getSquares()[h][i].piece.getName().equals("Bishop")
						|| this.getChessboard().getSquares()[h][i].piece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int h = s.getPozX() - 1, i = s.getPozY() - 1; !pieceBehaviour.isout(h, i); --h, --i) // left-down
		{
			if (this.getChessboard().getSquares()[h][i].piece == null || this.getChessboard().getSquares()[h][i].piece == this) // if
																														// on
																														// this
																														// sqhuare
																														// isn't
																														// piece
			{
				continue;
			} else if (this.getChessboard().getSquares()[h][i].piece.getPlayer() != this.getPlayer()) // if
																									// isn't
																									// our
																									// piece
			{
				if (this.getChessboard().getSquares()[h][i].piece.getName().equals("Bishop")
						|| this.getChessboard().getSquares()[h][i].piece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int h = s.getPozX() + 1, i = s.getPozY() + 1; !pieceBehaviour.isout(h, i); ++h, ++i) // right-up
		{
			if (this.getChessboard().getSquares()[h][i].piece == null || this.getChessboard().getSquares()[h][i].piece == this) // if
																														// on
																														// this
																														// sqhuare
																														// isn't
																														// piece
			{
				continue;
			} else if (this.getChessboard().getSquares()[h][i].piece.getPlayer() != this.getPlayer()) // if
																									// isn't
																									// our
																									// piece
			{
				if (this.getChessboard().getSquares()[h][i].piece.getName().equals("Bishop")
						|| this.getChessboard().getSquares()[h][i].piece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int h = s.getPozX() + 1, i = s.getPozY() - 1; !pieceBehaviour.isout(h, i); ++h, --i) // right-down
		{
			if (this.getChessboard().getSquares()[h][i].piece == null || this.getChessboard().getSquares()[h][i].piece == this) // if
																														// on
																														// this
																														// sqhuare
																														// isn't
																														// piece
			{
				continue;
			} else if (this.getChessboard().getSquares()[h][i].piece.getPlayer() != this.getPlayer()) // if
																									// isn't
																									// our
																									// piece
			{
				if (this.getChessboard().getSquares()[h][i].piece.getName().equals("Bishop")
						|| this.getChessboard().getSquares()[h][i].piece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// Knight
		int newX, newY;

		// 1
		newX = s.getPozX() - 2;
		newY = s.getPozY() + 1;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (this.getChessboard().getSquares()[newX][newY].piece == null) // if on
																		// this
																		// sqhuare
																		// isn't
																		// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																										// is
																										// our
																										// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Knight")) {
				return false;
			}
		}

		// 2
		newX = s.getPozX() - 1;
		newY = s.getPozY() + 2;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (this.getChessboard().getSquares()[newX][newY].piece == null) // if on
																		// this
																		// sqhuare
																		// isn't
																		// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																										// is
																										// our
																										// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Knight")) {
				return false;
			}
		}

		// 3
		newX = s.getPozX() + 1;
		newY = s.getPozY() + 2;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (this.getChessboard().getSquares()[newX][newY].piece == null) // if on
																		// this
																		// sqhuare
																		// isn't
																		// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																										// is
																										// our
																										// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Knight")) {
				return false;
			}
		}

		// 4
		newX = s.getPozX() + 2;
		newY = s.getPozY() + 1;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (this.getChessboard().getSquares()[newX][newY].piece == null) // if on
																		// this
																		// sqhuare
																		// isn't
																		// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																										// is
																										// our
																										// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Knight")) {
				return false;
			}
		}

		// 5
		newX = s.getPozX() + 2;
		newY = s.getPozY() - 1;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (this.getChessboard().getSquares()[newX][newY].piece == null) // if on
																		// this
																		// sqhuare
																		// isn't
																		// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																										// is
																										// our
																										// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Knight")) {
				return false;
			}
		}

		// 6
		newX = s.getPozX() + 1;
		newY = s.getPozY() - 2;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (this.getChessboard().getSquares()[newX][newY].piece == null) // if on
																		// this
																		// sqhuare
																		// isn't
																		// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																										// is
																										// our
																										// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Knight")) {
				return false;
			}
		}

		// 7
		newX = s.getPozX() - 1;
		newY = s.getPozY() - 2;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (this.getChessboard().getSquares()[newX][newY].piece == null) // if on
																		// this
																		// sqhuare
																		// isn't
																		// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																										// is
																										// our
																										// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Knight")) {
				return false;
			}
		}

		// 8
		newX = s.getPozX() - 2;
		newY = s.getPozY() - 1;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (this.getChessboard().getSquares()[newX][newY].piece == null) // if on
																		// this
																		// sqhuare
																		// isn't
																		// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																										// is
																										// our
																										// piece
			{
			} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Knight")) {
				return false;
			}
		}
/*
		// King
		King otherKing;
<<<<<<< HEAD:src/jchess/pieces/King.java
		if (this == getChessboard().kingWhite) {
		otherKing = getChessboard().kingBlack;
=======
		if (this == getChessboard().initial.kingWhite) {
			otherKing = getChessboard().initial.kingBlack;
>>>>>>> code_refactoring:src/main/java/pieces/King.java
		} else {
			otherKing = getChessboard().initial.kingWhite;
		}

		if (s.getPozX() <= otherKing.getSquare().getPozX() + 1 && s.getPozX() >= otherKing.getSquare().getPozX() - 1
				&& s.getPozY() <= otherKing.getSquare().getPozY() + 1
				&& s.getPozY() >= otherKing.getSquare().getPozY() - 1) {
			return false;
		}
*/
		// Pawn
		if (this.getPlayer().isGoDown()) // check if player "go" down or up
		{// System.out.println("go down");
			newX = s.getPozX() - 1;
			newY = s.getPozY() + 1;
			if (!pieceBehaviour.isout(newX, newY)) {
				if (this.getChessboard().getSquares()[newX][newY].piece == null) // if
																			// on
																			// this
																			// sqhuare
																			// isn't
																			// piece
				{
				} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																											// is
																											// our
																											// piece
				{
				} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Pawn")) {
					return false;
				}
			}
			newX = s.getPozX() + 1;
			if (!pieceBehaviour.isout(newX, newY)) {
				if (this.getChessboard().getSquares()[newX][newY].piece == null) // if
																			// on
																			// this
																			// sqhuare
																			// isn't
																			// piece
				{
				} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																											// is
																											// our
																											// piece
				{
				} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Pawn")) {
					return false;
				}
			}
		} else {// System.out.println("go up");
			newX = s.getPozX() - 1;
			newY = s.getPozY() - 1;
			if (!pieceBehaviour.isout(newX, newY)) {
				if (this.getChessboard().getSquares()[newX][newY].piece == null) // if
																			// on
																			// this
																			// sqhuare
																			// isn't
																			// piece
				{
				} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																											// is
																											// our
																											// piece
				{
				} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Pawn")) {
					return false;
				}
			}
			newX = s.getPozX() + 1;
			if (!pieceBehaviour.isout(newX, newY)) {
				if (this.getChessboard().getSquares()[newX][newY].piece == null) // if
																			// on
																			// this
																			// sqhuare
																			// isn't
																			// piece
				{
				} else if (this.getChessboard().getSquares()[newX][newY].piece.getPlayer() == this.getPlayer()) // if
																											// is
																											// our
																											// piece
				{
				} else if (this.getChessboard().getSquares()[newX][newY].piece.getName().equals("Pawn")) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Method to check will the king be safe when move
	 * 
	 * @return bool true if king is save, else returns false
	 */
	public boolean willBeSafeWhenMoveOtherPiece(Square sqIsHere, Square sqWillBeThere) // long
																						// name
																						// ;)
	{
		Piece tmp = sqWillBeThere.piece;
		sqWillBeThere.piece = sqIsHere.piece; // move without redraw
		sqIsHere.piece = null;

		boolean ret = isSafe(this.getSquare());

		sqIsHere.piece = sqWillBeThere.piece;
		sqWillBeThere.piece = tmp;

		return ret;
	}
}