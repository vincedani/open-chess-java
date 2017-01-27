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
		int newY = piece.getPosY() + 1;
		if (!piece.pieceBehaviour.isout(piece.getPosX(), newY)) {
			Square moveSq = piece.getSquare(piece.getPosX(),newY);
			if (moveSq.getPiece() == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.getPiece() == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

				list.add(moveSq);

			}
		}
	}

	private void passCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newX = piece.getPosX() + 8;
		if (newX >= 24) {
			newX -= 24;
		}
		if (!piece.pieceBehaviour.isout(newX, piece.getPosY())) {
			Square moveSq = piece.getSquare(newX,piece.getPosY());

			if (moveSq.getPiece() == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.getPiece() == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

				list.add(moveSq);
			}
		}
	}

	private void regularMoveAfterCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newY = piece.getPosY() - 1;

		if (!piece.pieceBehaviour.isout(piece.getPosX(), newY)) {
			Square moveSq = piece.getSquare(piece.getPosX(),newY);

			if (moveSq.getPiece() == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.getPiece() == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

				list.add(moveSq);

			}
		}
	}

	private void captureMove(Piece piece, ArrayList<Square> list, int newX, int newY, boolean ignoreKing) {

		if (!piece.pieceBehaviour.isout(newX, newY)) {
			Square moveSq = piece.getSquare(newX,newY);
			if (moveSq.getPiece() != null) {// check if can hit left
				if (piece.getPlayer() != moveSq.getPiece().getPlayer() && !moveSq.getPiece().getName().equals("King")) {
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
		int x = piece.getPosX(), y = piece.getPosY();
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
		int x = piece.getPosX(), y = piece.getPosY();
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

		if (piece.getPosY() < 5) {
			if (!passedCenter) {
				regularMoveBeforeCenter(piece, list, ignoreKing);
				captureMovesBeforeCenter(piece, list, ignoreKing);
			} else {
				regularMoveAfterCenter(piece, list, ignoreKing);
				captureMovesAfterCenter(piece, list, ignoreKing);
			}
		} else if (piece.getPosY() == 5) {
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
