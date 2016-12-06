package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Pawn;
import main.java.pieces.Piece;

public class PawnMoves implements IMove {
	public void regularMoveBeforeCenter(Piece piece, ArrayList<Square> list) {
		int newY = piece.getSquare().getPozY() + 1;
		if (!piece.pieceBehaviour.isout(piece.getSquare().getPozX(), newY)) {
			Square moveSq = piece.getChessboard().getSquares()[piece.getSquare().getPozX()][newY];

			if (moveSq.piece == null && piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(), moveSq)) {
				list.add(moveSq);

			}
		}
	}

	public void passCenter(Piece piece, ArrayList<Square> list) {
		int newX = piece.getSquare().getPozX() + 8;
		if (!piece.pieceBehaviour.isout(newX, piece.getSquare().getPozY())) {
			Square moveSq = piece.getChessboard().getSquares()[newX][piece.getSquare().getPozY()];

			if (moveSq.piece == null && piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(), moveSq)) {
				list.add(moveSq);
			}
		}
	}

	public void regularMoveAfterCenter(Piece piece, ArrayList<Square> list) {
		int newY = piece.getSquare().getPozY() - 1;

		if (!piece.pieceBehaviour.isout(piece.getSquare().getPozX(), newY)) {
			Square moveSq = piece.getChessboard().getSquares()[piece.getSquare().getPozX()][newY];

			if (moveSq.piece == null && piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(), moveSq)) {
				list.add(moveSq);

			}
		}
	}

	public void captureMoveBeforeCenter(Piece piece, ArrayList<Square> list, int newX) {
		int newY = piece.getSquare().getPozY() + 1;
		if (!piece.pieceBehaviour.isout(newX, newY)) {
			Square moveSq = piece.getChessboard().getSquares()[newX][newY];
			if (moveSq.piece != null) {// check if can hit left
				if (piece.getPlayer() != moveSq.piece.getPlayer() && !moveSq.piece.getName().equals("King")) {
					if (piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(), moveSq)) {
						list.add(moveSq);
					}
				}
			}
		}

	}

	public void captureMoveAfterCenter(Piece piece, ArrayList<Square> list, int newX) {
		int newY = piece.getSquare().getPozY() - 1;
		if (!piece.pieceBehaviour.isout(newX, newY)) {
			Square moveSq = piece.getChessboard().getSquares()[newX][newY];

			if (moveSq.piece != null) {// check if can hit left
				if (piece.getPlayer() != moveSq.piece.getPlayer() && !moveSq.piece.getName().equals("King")) {
					if (piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(), moveSq)) {
						list.add(moveSq);
					}
				}
			}
		}
	}

	public ArrayList<Square> getMoves(Piece piece1) {
		Pawn piece = (Pawn) piece1;
		ArrayList<Square> list = new ArrayList<>();
		if (piece.getSquare().getPozY() < 5) {
			if (!piece.passedCenter) {
				regularMoveBeforeCenter(piece, list);
				captureMoveBeforeCenter(piece, list, piece.getSquare().getPozX() + 1);
				captureMoveBeforeCenter(piece, list, piece.getSquare().getPozX() - 1);
			} else {
				regularMoveAfterCenter(piece, list);
				captureMoveAfterCenter(piece, list, piece.getSquare().getPozX() + 1);
				captureMoveAfterCenter(piece, list, piece.getSquare().getPozX() - 1);
			}
		} else if (piece.getSquare().getPozY() == 5) {
			if (!piece.passedCenter) {
				passCenter(piece, list);
			} else {
				passCenter(piece, list);
				regularMoveAfterCenter(piece, list);
				captureMoveAfterCenter(piece, list, piece.getSquare().getPozX() + 1);
				captureMoveAfterCenter(piece, list, piece.getSquare().getPozX() - 1);
			}

		}

		return list;
	}
}
