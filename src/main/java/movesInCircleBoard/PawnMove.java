package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class PawnMove implements IMove {
	boolean passedCenter = false;

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

	public ArrayList<Square> getMoves(Piece piece) {
		ArrayList<Square> list = new ArrayList<>();
		if (piece.getSquare().getPozY() < 5) {
			regularMoveBeforeCenter(piece, list);
		} else if(piece.getSquare().getPozY() == 5) {
			passCenter(piece, list);
			regularMoveAfterCenter(piece, list);
		} else{
			regularMoveAfterCenter(piece, list);
		}

		return list;
	}
}
