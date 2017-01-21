package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class BishopMovesInSquareBoard implements IMove {

	private static void backwardRightMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPozX(), y = piece.getPozY();
		for (int h = x - 1, i = y + 1; !piece.pieceBehaviour.isout(h, i); --h, ++i) // left-up
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't
														// piece
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), piece.getSquares(h,i))) {
					list.add(piece.getSquares(h,i));
				}

				if (piece.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private static void backwardLeftMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPozX(), y = piece.getPozY();
		for (int h = x - 1, i = y - 1; !piece.pieceBehaviour.isout(h, i); --h, --i) // left-down
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't piece
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), piece.getSquares(h,i))) {
					list.add(piece.getSquares(h,i));
				}

				if (piece.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private static void forwardRightMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPozX(), y = piece.getPozY();
		for (int h = x + 1, i = y + 1; !piece.pieceBehaviour.isout(h, i); ++h, ++i) // right-up
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't
														// piece
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), piece.getSquares(h,i))) {
					list.add(piece.getSquares(h,i));
				}

				if (piece.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private static void forwardLeftMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPozX(), y = piece.getPozY();
		for (int h = x + 1, i = y - 1; !piece.pieceBehaviour.isout(h, i); ++h, --i) // right-down
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't piece
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), piece.getSquares(h,i))) {
					list.add(piece.getSquares(h,i));
				}

				if (piece.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		forwardLeftMoves(piece, list, ignoreKing);
		forwardRightMoves(piece, list, ignoreKing);
		backwardLeftMoves(piece, list, ignoreKing);
		backwardRightMoves(piece, list, ignoreKing);

		return list;
	}
}
