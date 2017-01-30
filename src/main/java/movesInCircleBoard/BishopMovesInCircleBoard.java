package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
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
	private void backwardRightMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX();
		int y = piece.getPosY();

		if (x == 0) // Go after borders
			x = 24;

		for (int h = x - 1, i = y + 1; !piece.isout(h, i); h--, i++) // left-up
		{
			if (piece.checkPiece(h, i)) {
				if (ignoreKing
						|| piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), board.getSquareFromIndexes(h, i))) {
					list.add(board.getSquareFromIndexes(h, i));

					if (piece.otherOwner(h, i)) {
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
	}

	private void backwardLeftMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 0) {
			x = 24;
		}
		for (int h = x - 1, i = y - 1; !piece.isout(h, i); --h, --i) // left-down
		{
			if (piece.checkPiece(h, i)) // if on this square
										// isn't piece
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), board.getSquareFromIndexes(h, i))) {
					list.add(board.getSquareFromIndexes(h, i));
				}

				if (piece.otherOwner(h, i)) {
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

	private void forwardRightMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 23) {
			x = -1;
		}
		for (int h = x + 1, i = y + 1; !piece.isout(h, i); ++h, ++i) // right-up
		{
			if (piece.checkPiece(h, i)) // if on this square
										// isn't
										// piece
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), board.getSquareFromIndexes(h, i))) {
					list.add(board.getSquareFromIndexes(h, i));
				}

				if (piece.otherOwner(h, i)) {
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

	private void forwardLeftMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 23) {
			x = -1;
		}
		for (int h = x + 1, i = y - 1; !piece.isout(h, i); ++h, --i) // right-down
		{
			if (piece.checkPiece(h, i)) // if on this square
										// isn't piece
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), board.getSquareFromIndexes(h, i))) {
					list.add(board.getSquareFromIndexes(h, i));
				}

				if (piece.otherOwner(h, i)) {
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

	public ArrayList<Square> getMoves(IChessboard board, Piece piece, boolean ignoreKing) {

		ArrayList<Square> list = new ArrayList<>();
		forwardLeftMoves(board, piece, list, ignoreKing);
		forwardRightMoves(board, piece, list, ignoreKing);
		backwardLeftMoves(board, piece, list, ignoreKing);
		backwardRightMoves(board, piece, list, ignoreKing);

		return list;
	}
}
