package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class RookMovesInCircleBoard implements IMove {

	private boolean wasMoved = false;
	
	public void passCenter(){
		wasMoved = true;
	}
	
	private static void forwardVerticalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPozX(), y = piece.getPozY();
		for (int i = y + 1; i <= 5; i++) {// up

			if (piece.pieceBehaviour.checkPiece(x, i)) {
				Square newMove = piece.getSquares(x,i);

				if (ignoreKing) {
					list.add(newMove);
				} else if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
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
		int x = piece.getPozX(), y = piece.getPozY();
		for (int i = y - 1; i >= 0; i--) {// down

			if (piece.pieceBehaviour.checkPiece(x, i)) {// if on this square
														// isn't piece

				Square newMove = piece.getSquares(x,i);

				if (ignoreKing) {
					list.add(newMove);
				} else if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
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
		int x = piece.getPozX(), y = piece.getPozY();
		if (x == 23) {
			x = -1;
		}
		for (int i = x + 1; i <= 23; ++i) {// right

			if (piece.pieceBehaviour.checkPiece(i, y)) {// if on this square
														// isn't piece

				Square newMove = piece.getSquares(i,y);

				if (ignoreKing) {
					list.add(newMove);
				} else if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
					if (!list.contains(newMove))
						list.add(newMove);
				}

				if (piece.pieceBehaviour.otherOwner(i, y) || i == x) {
					break;
				}

				if (i == 23) {
					i = -1;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private static void leftHorizontalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPozX(), y = piece.getPozY();
		if (x == 0) {
			x = 24;
		}
		for (int i = x - 1; i >= 0; --i) {// left

			if (piece.pieceBehaviour.checkPiece(i, piece.getPozY())) {
				Square newMove = piece.getSquares(i,y);

				if (ignoreKing) {
					list.add(newMove);
				} else if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
					if (!list.contains(newMove))
						list.add(newMove);
				}

				if (piece.pieceBehaviour.otherOwner(i, y) || i == x) {
					break;
				}

				if (i == 0) {
					i = 24;
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
