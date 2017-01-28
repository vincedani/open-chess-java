package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory.PieceType;
import main.java.squareBoard.SquareBoard;

public class PawnMovesInSquareBoard implements IMove {

	public void regularMove(IChessboard board, Piece piece, ArrayList<Square> list, int i, boolean ignoreKing) {
		int newY = 0;
		if (piece.getPlayer().isGoDown()) {// check if player "go" down or up
			newY = piece.getPosY() + i;// if yes, change value
		} else {
			newY = piece.getPosY() - i;
			// (only in first move)
		}

		if (!piece.isout(piece.getPosX(), newY)) {
			Square moveSq = board.getSquareFromIndexes(piece.getPosX(), newY);

			if (moveSq.getPiece() == null
					&& (ignoreKing || piece.myKing().willBeSafeAfterMove(board, piece.getSquare(), moveSq))) {
				list.add(moveSq);

			}
		}
	}

	public void captureMove(IChessboard board, Piece piece, ArrayList<Square> list, int newX, boolean ignoreKing) {
		int newY = 0;
		if (piece.getPlayer().isGoDown()) {// check if player "go" down or up
			newY = piece.getPosY() + 1;// if yes, change value
		} else {
			newY = piece.getPosY() - 1;
			// (only in first move)
		}
		if (!piece.isout(newX, newY) && piece.checkPiece(newX, newY) && piece.otherOwner(newX, newY)) {
			if (ignoreKing || piece.myKing().willBeSafeAfterMove(board, piece.getSquare(), board.getSquareFromIndexes(newX, newY))) {
				list.add(board.getSquareFromIndexes(newX, newY));
			}
		}
	}

	public void enPassantMove(IChessboard board, Piece piece, ArrayList<Square> list, int newX, int i,
			boolean ignoreKing) {
		int newY = piece.getPosY();
		if (!piece.isout(newX, newY + i)) {
			Square attSq = board.getSquareFromIndexes(newX, newY);
			Square moveSq = board.getSquareFromIndexes(newX, newY + i);
			SquareBoard chessboard = (SquareBoard) piece.getChessboard();
			if (attSq.getPiece() != null && chessboard.getTwoSquareMovedPawn() != null
					&& attSq == chessboard.getTwoSquareMovedPawn().getSquare()) {
				// check if can hit left
				if (piece.getPlayer() != attSq.getPiece().getPlayer() && !attSq.getPiece().getType().equals(PieceType.King)) {
					if (ignoreKing || piece.myKing().willBeSafeAfterMove(board, piece.getSquare(), moveSq)) {
						list.add(moveSq);

					}
				}
			}
		}
	}

	public ArrayList<Square> getMoves(IChessboard board, Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		regularMove(board, piece, list, 1, ignoreKing);
		if ((piece.getPlayer().isGoDown() && piece.getPosY() == 1)
				|| (!piece.getPlayer().isGoDown() && piece.getPosY() == 6)) {
			regularMove(board, piece, list, 2, ignoreKing);
		}

		captureMove(board, piece, list, piece.getPosX() - 1, ignoreKing);
		enPassantMove(board, piece, list, piece.getPosX() - 1, -1, ignoreKing);

		// right
		captureMove(board, piece, list, piece.getPosX() + 1, ignoreKing);
		enPassantMove(board, piece, list, piece.getPosX() + 1, 1, ignoreKing);

		return list;
	}
}
