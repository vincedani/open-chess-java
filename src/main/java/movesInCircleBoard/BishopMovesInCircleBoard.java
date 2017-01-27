package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class BishopMovesInCircleBoard implements IMove {

	/**
	 * Method returning the possible moves of the given piece
	 * 
	 * @param Piece
	 *            the instance of the piece to extract the possible moves
	 * 
	 * @param ignoreKing
	 *            if active, it will not be checked if the King is in Check or
	 *            Stalemate
	 * 
	 * @return an ArrayList of Squares with the possible moves of the piece
	 * 
	 */
	private static void backwardRightMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 0) {
			x = 24;
		}
		for (int h = x - 1, i = y + 1; ! piece.pieceBehaviour.isout(h, i); h--, i++) // left-up
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) {
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), piece.getSquare(h, i)) ) {
					list.add(piece.getSquare(h, i));
				} else if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), piece.getSquare(h, i))) {
					list.add(piece.getSquare(h, i));
				}

				if (piece.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;
			}

			if (h == 0) {
				h = 24;
			}
		}
	}

	private static void backwardLeftMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 0) {
			x = 24;
		}
		for (int h = x - 1, i = y - 1; !piece.pieceBehaviour.isout(h, i); --h, --i) // left-down
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't piece
			{
				if (ignoreKing) {
					list.add(piece.getSquare(h, i));
				} else if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), piece.getSquare(h, i))) {
					list.add(piece.getSquare(h, i));
				}

				if (piece.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
			if (h == 0) {
				h = 24;
			}
		}
	}

	private static void forwardRightMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 23) {
			x = -1;
		}
		for (int h = x + 1, i = y + 1; !piece.pieceBehaviour.isout(h, i); ++h, ++i) // right-up
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't
														// piece
			{
				if (ignoreKing) {
					list.add(piece.getSquare(h, i));
				} else if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), piece.getSquare(h, i))) {
					list.add(piece.getSquare(h, i));
				}

				if (piece.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}

			if (h == 23) {
				h = -1;
			}
		}
	}

	private static void forwardLeftMoves(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 23) {
			x = -1;
		}
		for (int h = x + 1, i = y - 1; !piece.pieceBehaviour.isout(h, i); ++h, --i) // right-down
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't piece
			{
				if (ignoreKing) {
					list.add(piece.getSquare(h, i));
				} else if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), piece.getSquare(h, i))) {
					list.add(piece.getSquare(h, i));
				}

				if (piece.pieceBehaviour.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}

			if (h == 23) {
				h = -1;
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
