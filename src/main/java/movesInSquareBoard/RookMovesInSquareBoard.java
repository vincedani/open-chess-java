package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class RookMovesInSquareBoard implements IMove {

	private static void forwardVerticalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = y + 1; i <= 7; i++) {// up

			if (piece.pieceBehaviour.checkPiece(x, i)) {
				Square newMove = piece.getSquare(x,i);

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (piece.pieceBehaviour.otherOwner(x, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}

		}
	}

	private static void backwardVerticalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = y - 1; i >= 0; i--) {// down

			if (piece.pieceBehaviour.checkPiece(x, i)) {// if on this square
														// isn't piece

				Square newMove = piece.getSquare(x,i);

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (piece.pieceBehaviour.otherOwner(x, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private static void rightHorizontalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = x + 1; i <= 7; ++i) {// right

			if (piece.pieceBehaviour.checkPiece(i, y)) {// if on this square
														// isn't piece

				Square newMove = piece.getSquare(i,y);

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (piece.pieceBehaviour.otherOwner(i, y)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private static void leftHorizontalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = x - 1; i >= 0; --i) {// left

			if (piece.pieceBehaviour.checkPiece(i, piece.getPosY())) {// if
				Square newMove = piece.getSquare(i,y);

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (piece.pieceBehaviour.otherOwner(i, y)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		forwardVerticalMoves(piece, list, ignoreKing);
		backwardVerticalMoves(piece, list, ignoreKing);
		leftHorizontalMoves(piece, list, ignoreKing);
		rightHorizontalMoves(piece, list, ignoreKing);

		return list;
	}
}
