package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class BishopMovesInSquareBoard implements IMove {

	private void backwardRightMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX();
		int y = piece.getPosY();
		for (int h = x - 1, i = y + 1; !piece.isout(h, i); --h, ++i) // left-up
		{
			if (piece.checkPiece(h, i)) 
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board, piece.getSquare(), board.getSquareFromIndexes(h,i))) {
					list.add(board.getSquareFromIndexes(h,i));
				}

				if (piece.otherOwner(h, i)) {
					break;
				}
			} else {
				break;
			}
		}
	}

	private void backwardLeftMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int h = x - 1, i = y - 1; !piece.isout(h, i); h--, i--) // left-down
		{
			if (piece.checkPiece(h, i)){
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), board.getSquareFromIndexes(h,i))) {
					list.add(board.getSquareFromIndexes(h,i));
				}

				if (piece.otherOwner(h, i)) {
					break;
				}
			} else {
				break;
			}
		}
	}

	private void forwardRightMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int h = x + 1, i = y + 1; !piece.isout(h, i); ++h, ++i) // right-up
		{
			if (piece.checkPiece(h, i)) // if on this square
														// isn't
														// piece
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), board.getSquareFromIndexes(h,i))) {
					list.add(board.getSquareFromIndexes(h,i));
				}

				if (piece.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private void forwardLeftMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int h = x + 1, i = y - 1; !piece.isout(h, i); ++h, --i) // right-down
		{
			if (piece.checkPiece(h, i)) // if on this square
														// isn't piece
			{
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), board.getSquareFromIndexes(h,i))) {
					list.add(board.getSquareFromIndexes(h,i));
				}

				if (piece.otherOwner(h, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
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
