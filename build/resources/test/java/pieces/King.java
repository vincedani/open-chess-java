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
 */
import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.squareBoard.SquareBoard;

public class King extends Piece {

	public boolean wasMotion = false;// maybe change to: 'wasMotioned'
	// public boolean checked = false;
	public static short value = 99;
	ArrayList<IMove> moveBehaviour;

	public King(IChessboard chessboard, Player player, ArrayList<IMove> moveBehaviour) {

		super(chessboard, player, "King"); // call initializer of super type:
											// Piece
		this.symbol = "K";
		this.moveBehaviour = moveBehaviour;

	}

	/**
	 * Annotation to superclass Piece changing pawns location
	 * 
	 * @return ArrayList with new position of piece
	 */
	public ArrayList<Square> allMoves(boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<Square>();
		for (IMove iMove : moveBehaviour) {
			list.addAll(iMove.getMoves(this, false));
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

	public int isCheckmatedOrStalematedOld() {

		// 0:Nothing, 1:Checkmate, 2:Stalemate

		if (this.allMoves(false).isEmpty()) {
			Piece boardPiece; // Piece in Board

			for (int i = 0; i < 8; ++i) {
				for (int j = 0; j < 8; ++j) {
					boardPiece = getSquares(i, j).piece;
					if (boardPiece != null && boardPiece.getPlayer() == this.getPlayer()
							&& allMovesSize(boardPiece, false) != 0) {
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
	 * Method to check is the king is checked or stalemated
	 * 
	 * @return int 0 if nothing, 1 if checkmate, else returns 2
	 */
	public int isCheckmatedOrStalemated() {
		/*
		 * returns: 0-nothing, 1-checkmate, 2-stalemate
		 */
		int nx = 0, ny = 0;
		if (this.getChessboard() instanceof SquareBoard) {
			nx = 8;
			ny = 8;
		} else if (this.getChessboard() instanceof CircleBoard) {
			nx = 24;
			ny = 6;
		}

		if (this.allMoves(false).isEmpty()) {
			Piece boardPiece;
			for (int i = 0; i < nx; ++i) {
				for (int j = 0; j < ny; ++j) {
					boardPiece = getSquares(i, j).piece;
					if (boardPiece != null && boardPiece != this && boardPiece.getPlayer() == this.getPlayer()
							&& allMovesSize(boardPiece, false) != 0) {
						System.out.println(this +"," + boardPiece + ", " + boardPiece.getPlayer().getColor());
						System.out.println("Objeto 1");
						System.out.println(this.getSquare().getPozX() +", " + this.getSquare().getPozY() + boardPiece + ", " + boardPiece.getPlayer().getColor());

						System.out.println("Objeto 2");
						System.out.println(boardPiece.getSquare().getPozX() +", " + boardPiece.getSquare().getPozY() + boardPiece + ", " + boardPiece.getPlayer().getColor());

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

	public boolean isSafeOld(Square s) // A bit confusing code.
	{
		// Rook & Queen
		for (int i = s.getPozY() + 1; i <= 7; ++i) {
			// Up
			Piece boardPiece = this.getSquares(s.getPozX(), i).piece;
			if (boardPiece == null || boardPiece == this) {
				// No piece on this square?
				continue;
			} else if (boardPiece.getPlayer() != this.getPlayer()) {
				// Not our piece
				if (boardPiece.getName().equals("Rook") || boardPiece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = s.getPozY() - 1; i >= 0; --i) {
			// Down
			Piece boardPiece = this.getSquares(s.getPozX(), i).piece;

			if (boardPiece == null || boardPiece == this) {
				// No piece in this square?
				continue;
			} else if (boardPiece.getPlayer() != this.getPlayer()) {
				// Not our piece
				if (boardPiece.getName().equals("Rook") || boardPiece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = s.getPozX() - 1; i >= 0; --i) {
			// Left
			Piece boardPiece = this.getSquares(i, s.getPozY()).piece;

			if (boardPiece == null || boardPiece == this) {
				// No piece in square?
				continue;
			} else if (boardPiece.getPlayer() != this.getPlayer()) {
				// Not our piece
				if (boardPiece.getName().equals("Rook") || boardPiece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = s.getPozX() + 1; i <= 7; ++i) {
			// Right
			Piece boardPiece = this.getSquares(i, s.getPozY()).piece;
			if (boardPiece == null || boardPiece == this) {
				continue;
			} else if (boardPiece.getPlayer() != this.getPlayer()) {
				if (boardPiece.getName().equals("Rook") || boardPiece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		// Bishop & Queen
		for (int h = s.getPozX() - 1, i = s.getPozY() + 1; !pieceBehaviour.isout(h, i); --h, ++i) {
			// Left-up
			Piece boardPiece = this.getSquares(h, i).piece;
			if (boardPiece == null || boardPiece == this) {
				continue;
			} else if (boardPiece.getPlayer() != this.getPlayer()) {
				if (boardPiece.getName().equals("Bishop") || boardPiece.getName().equals("Queen")) {
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
			Piece boardPiece = this.getSquares(h, i).piece;
			if (boardPiece == null || boardPiece == this) {
				continue;
			} else if (boardPiece.getPlayer() != this.getPlayer()) {
				if (boardPiece.getName().equals("Bishop") || boardPiece.getName().equals("Queen")) {
					return false;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int h = s.getPozX() + 1, i = s.getPozY() + 1; !pieceBehaviour.isout(h, i); ++h, ++i) {
			// Right-up
			Piece boardPiece = this.getSquares(i, s.getPozY()).piece;
			if (boardPiece == null || boardPiece == this) {
				continue;
			} else if (boardPiece.getPlayer() != this.getPlayer()) {
				if (boardPiece.getName().equals("Bishop") || boardPiece.getName().equals("Queen")) {
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
			Piece boardPiece = this.getSquares(h, i).piece;

			if (boardPiece == null || boardPiece == this) {
				continue;
			} else if (boardPiece.getPlayer() != this.getPlayer()) {
				if (boardPiece.getName().equals("Bishop") || boardPiece.getName().equals("Queen")) {
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
		Piece boardPiece = this.getSquares(newX, newY).piece;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (boardPiece == null) {
			} else if (boardPiece.getPlayer() == this.getPlayer()) {
			} else if (boardPiece.getName().equals("Knight")) {
				return false;
			}
		}

		// 2
		newX = s.getPozX() - 1;
		newY = s.getPozY() + 2;
		boardPiece = this.getSquares(newX, newY).piece;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (boardPiece == null) {
			} else if (boardPiece.getPlayer() == this.getPlayer()) {
			} else if (boardPiece.getName().equals("Knight")) {
				return false;
			}
		}

		// 3
		newX = s.getPozX() + 1;
		newY = s.getPozY() + 2;
		boardPiece = this.getSquares(newX, newY).piece;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (boardPiece == null) {
			} else if (boardPiece.getPlayer() == this.getPlayer()) {
			} else if (boardPiece.getName().equals("Knight")) {
				return false;
			}
		}

		// 4
		newX = s.getPozX() + 2;
		newY = s.getPozY() + 1;
		boardPiece = this.getSquares(newX, newY).piece;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (boardPiece == null) {
			} else if (boardPiece.getPlayer() == this.getPlayer()) {
			} else if (boardPiece.getName().equals("Knight")) {
				return false;
			}
		}

		// 5
		newX = s.getPozX() + 2;
		newY = s.getPozY() - 1;
		boardPiece = this.getSquares(newX, newY).piece;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (boardPiece == null) {
			} else if (boardPiece.getPlayer() == this.getPlayer()) {
			} else if (boardPiece.getName().equals("Knight")) {
				return false;
			}
		}

		// 6
		newX = s.getPozX() + 1;
		newY = s.getPozY() - 2;
		boardPiece = this.getSquares(newX, newY).piece;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (boardPiece == null) {
			} else if (boardPiece.getPlayer() == this.getPlayer()) {
			} else if (boardPiece.getName().equals("Knight")) {
				return false;
			}
		}

		// 7
		newX = s.getPozX() - 1;
		newY = s.getPozY() - 2;
		boardPiece = this.getSquares(newX, newY).piece;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (boardPiece == null) {
			} else if (boardPiece.getPlayer() == this.getPlayer()) {
			} else if (boardPiece.getName().equals("Knight")) {
				return false;
			}
		}

		// 8
		newX = s.getPozX() - 2;
		newY = s.getPozY() - 1;
		boardPiece = this.getSquares(newX, newY).piece;

		if (!pieceBehaviour.isout(newX, newY)) {
			if (boardPiece == null) {
			} else if (boardPiece.getPlayer() == this.getPlayer()) {
			} else if (boardPiece.getName().equals("Knight")) {
				return false;
			}
		}
		/*
		 * // King King otherKing; <<<<<<< HEAD:src/jchess/pieces/King.java if
		 * (this == getChessboard().kingWhite) { otherKing =
		 * getChessboard().kingBlack; ======= if (this ==
		 * getChessboard().initial.kingWhite) { otherKing =
		 * getChessboard().initial.kingBlack; >>>>>>>
		 * code_refactoring:src/main/java/pieces/King.java } else { otherKing =
		 * getChessboard().initial.kingWhite; }
		 * 
		 * if (s.getPozX() <= otherKing.getSquare().getPozX() + 1 && s.getPozX()
		 * >= otherKing.getSquare().getPozX() - 1 && s.getPozY() <=
		 * otherKing.getSquare().getPozY() + 1 && s.getPozY() >=
		 * otherKing.getSquare().getPozY() - 1) { return false; }
		 */

		// Pawn
		if (this.getPlayer().isGoDown()) {
			// Down
			newX = s.getPozX() - 1;
			newY = s.getPozY() + 1;
			boardPiece = this.getSquares(newX, newY).piece;
			if (!pieceBehaviour.isout(newX, newY)) {
				if (boardPiece == null) {
				} else if (boardPiece.getPlayer() == this.getPlayer()) {
				} else if (boardPiece.getName().equals("Pawn")) {
					return false;
				}
			}

			newX = s.getPozX() + 1;
			boardPiece = this.getSquares(newX, newY).piece;
			if (!pieceBehaviour.isout(newX, newY)) {
				if (boardPiece == null) {
				} else if (boardPiece.getPlayer() == this.getPlayer()) {
				} else if (boardPiece.getName().equals("Pawn")) {
					return false;
				}
			}

		} else {
			// Go UP
			newX = s.getPozX() - 1;
			newY = s.getPozY() - 1;
			boardPiece = this.getSquares(newX, newY).piece;
			if (!pieceBehaviour.isout(newX, newY)) {
				if (boardPiece == null) {
				} else if (boardPiece.getPlayer() == this.getPlayer()) {
				} else if (boardPiece.getName().equals("Pawn")) {
					return false;
				}
			}

			newX = s.getPozX() + 1;
			boardPiece = this.getSquares(newX, newY).piece;
			if (!pieceBehaviour.isout(newX, newY)) {
				if (boardPiece == null) {
				} else if (boardPiece.getPlayer() == this.getPlayer()) {
				} else if (boardPiece.getName().equals("Pawn")) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Method to check is the king is checked by an opponent
	 * 
	 * @param s
	 *            Square where is a king
	 * @return bool true if king is save, else returns false
	 */
	public boolean isSafe(Square s) {
		int nx = 0, ny = 0;
		if (this.getChessboard() instanceof SquareBoard) {
			nx = 8;
			ny = 8;
		} else if (this.getChessboard() instanceof CircleBoard) {
			nx = 24;
			ny = 6;
		}

		for (int i = 0; i < nx; i++) {
			for (int j = 0; j < ny; j++) {
				Piece boardPiece = this.getSquares(i, j).piece;
				if (boardPiece != null && boardPiece.getPlayer() != this.getPlayer()) {
					if (boardPiece.getName().equals("Dragon")) {
						continue;
					} else {
						ArrayList<Square> pieceMoves = boardPiece.allMoves(true);
						if (pieceMoves.contains(s)) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	/**
	 * Method to check will the king be safe after the move of the pieces in the
	 * given squares
	 * 
	 * @param sqIsHe
	 *            re the original square of the piece
	 * @param sqWillBeThere
	 *            the future square of the piece
	 * @return boolean true if king is save, else returns false
	 */
	public boolean willBeSafeAfterMove(Square sqIsHere, Square sqWillBeThere) {
		Piece tmp = sqWillBeThere.piece;
		sqWillBeThere.piece = sqIsHere.piece; // move without redraw
		sqIsHere.piece = null;
		boolean ret;

		/*if (sqWillBeThere.piece.equals(this)) {
			ret = isSafe(this.getSquare());
		} else {
			ret = isSafe(sqWillBeThere);

		}*/
		
		ret = isSafe(this.getSquare());

		sqIsHere.piece = sqWillBeThere.piece;
		sqWillBeThere.piece = tmp;

		return ret;
	}

}