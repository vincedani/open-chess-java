package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class PawnMovesInCircleBoard implements IMove {

	private boolean passedCenter = false;

	public void passCenter() {
		passedCenter = true;
	}

	private void regularMoveBeforeCenter(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newY = piece.getPosY() + 1;
		if (!piece.isout(piece.getPosX(), newY)) {
			Square moveSq = board.getSquareFromIndexes(piece.getPosX(), newY);
			if (moveSq.getPiece() == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.getPiece() == null && piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), moveSq)) {

				list.add(moveSq);

			}
		}
	}

	private void passCenter(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newX = piece.getPosX() + 8;
		if (newX >= 24) {
			newX -= 24;
		}
		if (!piece.isout(newX, piece.getPosY())) {
			Square moveSq = board.getSquareFromIndexes(newX, piece.getPosY());

			if (moveSq.getPiece() == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.getPiece() == null && piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), moveSq)) {

				list.add(moveSq);
			}
		}
	}

	private void regularMoveAfterCenter(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int newY = piece.getPosY() - 1;

		if (!piece.isout(piece.getPosX(), newY)) {
			Square moveSq = board.getSquareFromIndexes(piece.getPosX(), newY);

			if (moveSq.getPiece() == null && ignoreKing) {
				list.add(moveSq);

			} else if (moveSq.getPiece() == null && piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), moveSq)) {

				list.add(moveSq);

			}
		}
	}

	private void captureMove(IChessboard board, Piece piece, ArrayList<Square> list, int newX, int newY,
			boolean ignoreKing) {

		if (!piece.isout(newX, newY) && piece.checkPiece(newX, newY) && piece.otherOwner(newX, newY)) {
			if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), board.getSquareFromIndexes(newX, newY))) {

				list.add(board.getSquareFromIndexes(newX, newY));
			}
		}
	}

	

	private void captureMovesBeforeCenter(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX();
		int y = piece.getPosY();
		if (x == 0) {
			captureMove(board,piece, list, x + 1, y + 1, ignoreKing);
			captureMove(board,piece, list, 23, y + 1, ignoreKing);

		} else if (x == 23) {
			captureMove(board,piece, list, 0, y + 1, ignoreKing);
			captureMove(board,piece, list, x - 1, y + 1, ignoreKing);
		} else {
			captureMove(board,piece, list, x + 1, y + 1, ignoreKing);
			captureMove(board,piece, list, x - 1, y + 1, ignoreKing);
		}
	}

	private void captureMovesAfterCenter(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 0) {
			captureMove(board,piece, list, x + 1, y - 1, ignoreKing);
			captureMove(board,piece, list, 23, y - 1, ignoreKing);

		} else if (x == 23) {
			captureMove(board,piece, list, 0, y - 1, ignoreKing);
			captureMove(board,piece, list, x - 1, y - 1, ignoreKing);
		} else {
			captureMove(board,piece, list, x + 1, y - 1, ignoreKing);
			captureMove(board,piece, list, x - 1, y - 1, ignoreKing);
		}
	}

	public ArrayList<Square> getMoves(IChessboard board, Piece piece, boolean ignoreKing) {

		ArrayList<Square> list = new ArrayList<>();

		if (piece.getPosY() < 5) {
			if (!passedCenter) {
				regularMoveBeforeCenter(board, piece, list, ignoreKing);
				captureMovesBeforeCenter(board, piece, list, ignoreKing);
			} else {
				regularMoveAfterCenter(board, piece, list, ignoreKing);
				captureMovesAfterCenter(board, piece, list, ignoreKing);
			}
		} else if (piece.getPosY() == 5) {
			if (!passedCenter) {
				passCenter(board, piece, list, ignoreKing);
			} else {
				passCenter(board, piece, list, ignoreKing);
				regularMoveAfterCenter(board, piece, list, ignoreKing);
				captureMovesAfterCenter(board, piece, list, ignoreKing);
			}

		}

		return list;
	}

}
