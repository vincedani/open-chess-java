package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Pawn;
import main.java.pieces.Piece;

public class PawnMoves implements IMove {
	public void regularMoveBeforeCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newY = piece.getSquare().getPozY() + 1;
		if (!piece.pieceBehaviour.isout(piece.getSquare().getPozX(), newY)) {
			Square moveSq = piece.getChessboard().getSquares()[piece.getSquare().getPozX()][newY];
			if (moveSq.piece == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.piece == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

				list.add(moveSq);

			}
		}
	}

	public void passCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newX = piece.getSquare().getPozX() + 8;
		if (newX >= 24) {
			newX -= 24;
		}
		if (!piece.pieceBehaviour.isout(newX, piece.getSquare().getPozY())) {
			Square moveSq = piece.getChessboard().getSquares()[newX][piece.getSquare().getPozY()];

			if (moveSq.piece == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.piece == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

				list.add(moveSq);
			}
		}
	}

	public void regularMoveAfterCenter(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newY = piece.getSquare().getPozY() - 1;

		if (!piece.pieceBehaviour.isout(piece.getSquare().getPozX(), newY)) {
			Square moveSq = piece.getChessboard().getSquares()[piece.getSquare().getPozX()][newY];

			if (moveSq.piece == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.piece == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {

				list.add(moveSq);

			}
		}
	}

	public void captureMove(Piece piece, ArrayList<Square> list, int newX, int newY, boolean ignoreKing) {

		if (!piece.pieceBehaviour.isout(newX, newY)) {
			Square moveSq = piece.getChessboard().getSquares()[newX][newY];
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

	public ArrayList<Square> getMoves(Piece piece1, boolean ignoreKing) {
		Pawn piece = (Pawn) piece1;
		ArrayList<Square> list = new ArrayList<>();

		if (piece.getSquare().getPozY() < 5) {
			if (!piece.passedCenter) {
				regularMoveBeforeCenter(piece, list, ignoreKing);
				captureMovesBeforeCenter(piece, list, ignoreKing);
			} else {
				regularMoveAfterCenter(piece, list, ignoreKing);
				captureMovesAfterCenter(piece, list, ignoreKing);
			}
		} else if (piece.getSquare().getPozY() == 5) {
			if (!piece.passedCenter) {
				passCenter(piece, list, ignoreKing);
			} else {
				passCenter(piece, list, ignoreKing);
				regularMoveAfterCenter(piece, list, ignoreKing);
				captureMovesAfterCenter(piece, list, ignoreKing);
			}

		}

		return list;
	}

	private void captureMovesBeforeCenter(Pawn piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
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

	private void captureMovesAfterCenter(Pawn piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
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
}
