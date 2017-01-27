package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class RookMovesInCircleBoard implements IMove {

	
	private void forwardVerticalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = y + 1; i <= 5; i++) {// up
			Square newMove = piece.getSquare(x, i);
			if (newMove.getPiece() == null || (piece.pieceBehaviour.otherOwner(x, i) && piece.wasMoved())) {
				
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

	private void backwardVerticalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = y - 1; i >= 0; i--) {// down
			Square newMove = piece.getSquare(x,i);
			if (newMove.getPiece() == null || (piece.pieceBehaviour.otherOwner(x, i) && piece.wasMoved())) {

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

	private void rightHorizontalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 23) {
			x = -1;
		}
		for (int i = x + 1; i <= 23; ++i) {// right

				Square newMove = piece.getSquare(i,y);
				if (newMove.getPiece() == null || (piece.pieceBehaviour.otherOwner(i,y) && piece.wasMoved())) {

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

	private void leftHorizontalMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 0) {
			x = 24;
		}
		for (int i = x - 1; i >= 0; --i) {// left

			
				Square newMove = piece.getSquare(i,y);
				if (newMove.getPiece() == null || (piece.pieceBehaviour.otherOwner(i,y) && piece.wasMoved())) {

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
