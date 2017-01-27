package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;
import main.java.squareBoard.SquareBoard;

public class PawnMovesInSquareBoard implements IMove {

	public void regularMove(Piece piece, ArrayList<Square> list, int i, boolean ignoreKing) {
		int newY = 0;
		if (piece.getPlayer().isGoDown()) {// check if player "go" down or up
			newY = piece.getPosY() + i;// if yes, change value
		} else {
			newY = piece.getPosY() - i;
			// (only in first move)
		}

		if (!piece.pieceBehaviour.isout(piece.getPosX(), newY)) {
			Square moveSq = piece.getSquare(piece.getPosX(),newY);

			if (moveSq.getPiece() == null && (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq))) {
				list.add(moveSq);

			}
		}
	}

	public void captureMove(Piece piece, ArrayList<Square> list, int newX, boolean ignoreKing) {
		int newY = 0;
		if (piece.getPlayer().isGoDown()) {// check if player "go" down or up
			newY = piece.getPosY() + 1;// if yes, change value
		} else {
			newY = piece.getPosY() - 1;
			// (only in first move)
		}
		if (!piece.pieceBehaviour.isout(newX, newY)) {
			Square moveSq = piece.getSquare(newX,newY);
			if (moveSq.getPiece() != null) {// check if can hit left
				if (piece.getPlayer() != moveSq.getPiece().getPlayer() && !moveSq.getPiece().getName().equals("King")) {
					if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {
						list.add(moveSq);
					}
				}
			}
		}

	}

	public void enPassantMove(Piece piece, ArrayList<Square> list, int newX, int i, boolean ignoreKing) {
		int newY = piece.getPosY();
		if (!piece.pieceBehaviour.isout(newX, newY + i)) {
			Square attSq = piece.getSquare(newX,newY);
			Square moveSq = piece.getSquare(newX,newY + i);
			SquareBoard chessboard = (SquareBoard) piece.getChessboard();
			if (attSq.getPiece() != null && chessboard.getTwoSquareMovedPawn() != null
					&& attSq == chessboard.getTwoSquareMovedPawn().getSquare()) {
				// check if can hit left
				if (piece.getPlayer() != attSq.getPiece().getPlayer() && !attSq.getPiece().getName().equals("King")) {
					if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), moveSq)) {
						list.add(moveSq);

					}
				}
			}
		}
	}

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		regularMove(piece, list, 1, ignoreKing);
		if ((piece.getPlayer().isGoDown() && piece.getPosY() == 1)
				|| (!piece.getPlayer().isGoDown() && piece.getPosY() == 6)) {
			regularMove(piece, list, 2, ignoreKing);
		}

		captureMove(piece, list, piece.getPosX() - 1, ignoreKing);
		enPassantMove(piece, list, piece.getPosX() - 1, -1, ignoreKing);

		// right
		captureMove(piece, list, piece.getPosX() + 1, ignoreKing);
		enPassantMove(piece, list, piece.getPosX() + 1, 1, ignoreKing);

		return list;
	}
}
