package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;
import main.java.squareBoard.SquareBoard;

public class PawnMovesInSquareBoard implements IMove {

	public void regularMove(Piece piece, ArrayList<Square> list, int i) {
		int newY = 0;
		if (piece.getPlayer().isGoDown()) {// check if player "go" down or up
			newY = piece.getPozY() + i;// if yes, change value
		} else {
			newY = piece.getPozY() - i;
			// (only in first move)
		}

		if (!piece.pieceBehaviour.isout(piece.getPozX(), newY)) {
			Square moveSq = piece.getSquares(piece.getPozX(),newY);

			if (moveSq.piece == null && piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {
				list.add(moveSq);

			}
		}
	}

	public void captureMove(Piece piece, ArrayList<Square> list, int newX) {
		int newY = 0;
		if (piece.getPlayer().isGoDown()) {// check if player "go" down or up
			newY = piece.getPozY() + 1;// if yes, change value
		} else {
			newY = piece.getPozY() - 1;
			// (only in first move)
		}
		if (!piece.pieceBehaviour.isout(newX, newY)) {
			Square moveSq = piece.getSquares(newX,newY);
			if (moveSq.piece != null) {// check if can hit left
				if (piece.getPlayer() != moveSq.piece.getPlayer() && !moveSq.piece.getName().equals("King")) {
					if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {
						list.add(moveSq);
					}
				}
			}
		}

	}

	public void enPassantMove(Piece piece, ArrayList<Square> list, int newX, int i) {
		int newY = piece.getPozY();
		if (!piece.pieceBehaviour.isout(newX, newY + i)) {
			Square attSq = piece.getSquares(newX,newY);
			Square moveSq = piece.getSquares(newX,newY + i);
			SquareBoard chessboard = (SquareBoard) piece.getChessboard();
			if (attSq.piece != null && chessboard.getTwoSquareMovedPawn() != null
					&& attSq == chessboard.getTwoSquareMovedPawn().getSquare()) {
				// check if can hit left
				if (piece.getPlayer() != attSq.piece.getPlayer() && !attSq.piece.getName().equals("King")) {
					if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {
						list.add(moveSq);

					}
				}
			}
		}
	}

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		regularMove(piece, list, 1);
		if ((piece.getPlayer().isGoDown() && piece.getPozY() == 1)
				|| (!piece.getPlayer().isGoDown() && piece.getPozY() == 6)) {
			regularMove(piece, list, 2);
		}

		captureMove(piece, list, piece.getPozX() - 1);
		enPassantMove(piece, list, piece.getPozX() - 1, -1);

		// right
		captureMove(piece, list, piece.getPozX() + 1);
		enPassantMove(piece, list, piece.getPozX() + 1, 1);

		return list;
	}
}
