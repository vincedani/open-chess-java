package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class PawnMovesInCircleBoard implements IMove {
	
	private boolean passedCenter = false;
	
	public void passCenter(){
		passedCenter = true;
	}
	
	private void regularMoveBeforeCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newY = piece.getPozY() + 1;
		if (!piece.pieceBehaviour.isout(piece.getPozX(), newY)) {
			Square moveSq = piece.getSquares(piece.getPozX(),newY);
			if (moveSq.piece == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.piece == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

				list.add(moveSq);

			}
		}
	}

	private void passCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newX = piece.getPozX() + 8;
		if (newX >= 24) {
			newX -= 24;
		}
		if (!piece.pieceBehaviour.isout(newX, piece.getPozY())) {
			Square moveSq = piece.getSquares(newX,piece.getPozY());

			if (moveSq.piece == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.piece == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

				list.add(moveSq);
			}
		}
	}

	private void regularMoveAfterCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newY = piece.getPozY() - 1;

		if (!piece.pieceBehaviour.isout(piece.getPozX(), newY)) {
			Square moveSq = piece.getSquares(piece.getPozX(),newY);

			if (moveSq.piece == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.piece == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

				list.add(moveSq);

			}
		}
	}

	private void captureMove(Piece piece, ArrayList<Square> list, int newX, int newY, boolean ignoreKing) {

		if (!piece.pieceBehaviour.isout(newX, newY)) {
			Square moveSq = piece.getSquares(newX,newY);
			if (moveSq.piece != null) {// check if can hit left
				if (piece.getPlayer() != moveSq.piece.getPlayer() && !moveSq.piece.getName().equals("King")) {
					if (ignoreKing) {
						list.add(moveSq);

					} else if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

						list.add(moveSq);
					}
				}
			}
		}

	}

	private void captureMovesBeforeCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPozX(), y = piece.getPozY();
		if (x == 0) {
			captureMove(piece, list, x + 1, y + 1, ignoreKing);
			captureMove(piece, list, 23, y + 1, ignoreKing);

		} else if (x == 23) {
			captureMove(piece, list, 0, y + 1, ignoreKing);
			captureMove(piece, list, x - 1, y + 1, ignoreKing);
		} else {
			captureMove(piece, list, x + 1, y + 1, ignoreKing);
			captureMove(piece, list, x - 1, y + 1, ignoreKing);
		}
	}

	private void captureMovesAfterCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPozX(), y = piece.getPozY();
		if (x == 0) {
			captureMove(piece, list, x + 1, y - 1, ignoreKing);
			captureMove(piece, list, 23, y - 1, ignoreKing);

		} else if (x == 23) {
			captureMove(piece, list, 0, y - 1, ignoreKing);
			captureMove(piece, list, x - 1, y - 1, ignoreKing);
		} else {
			captureMove(piece, list, x + 1, y - 1, ignoreKing);
			captureMove(piece, list, x - 1, y - 1, ignoreKing);
		}
	}
	
	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {

		ArrayList<Square> list = new ArrayList<>();

		if (piece.getPozY() < 5) {
			if (!passedCenter) {
				regularMoveBeforeCenter(piece, list, ignoreKing);
				captureMovesBeforeCenter(piece, list, ignoreKing);
			} else {
				regularMoveAfterCenter(piece, list, ignoreKing);
				captureMovesAfterCenter(piece, list, ignoreKing);
			}
		} else if (piece.getPozY() == 5) {
			if (!passedCenter) {
				passCenter(piece, list, ignoreKing);
			} else {
				passCenter(piece, list, ignoreKing);
				regularMoveAfterCenter(piece, list, ignoreKing);
				captureMovesAfterCenter(piece, list, ignoreKing);
			}

		}

		return list;
	}

	
}
