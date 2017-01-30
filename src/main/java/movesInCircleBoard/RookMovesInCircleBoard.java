package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class RookMovesInCircleBoard implements IMove {

	
	private void forwardVerticalMoves(IChessboard board,Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = y + 1; i <= 5; i++) {// up
			Square newMove = board.getSquareFromIndexes(x, i);
			if (newMove.getPiece() == null || (piece.otherOwner(x, i) && piece.wasMoved())) {
				
				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (piece.otherOwner(x, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}

		}
	}

	private void backwardVerticalMoves(IChessboard board,Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = y - 1; i >= 0; i--) {// down
			Square newMove = board.getSquareFromIndexes(x,i);
			if (newMove.getPiece() == null || (piece.otherOwner(x, i) && piece.wasMoved())) {

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (piece.otherOwner(x, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private void rightHorizontalMoves(IChessboard board,Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 23) {
			x = -1;
		}
		for (int i = x + 1; i <= 23; ++i) {// right

				Square newMove =board.getSquareFromIndexes(i,y);
				if (newMove.getPiece() == null || (piece.otherOwner(i,y) && piece.wasMoved())) {

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), newMove)) {
					if (!list.contains(newMove))
						list.add(newMove);
				}

				if (piece.otherOwner(i, y) || i == x) {
					break;
				}

				if (i == 23) {
					i = -1;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private void leftHorizontalMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		if (x == 0) {
			x = 24;
		}
		for (int i = x - 1; i >= 0; --i) {// left

			
				Square newMove = board.getSquareFromIndexes(i,y);
				if (newMove.getPiece() == null || (piece.otherOwner(i,y) && piece.wasMoved())) {

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board,piece.getSquare(), newMove)) {
					if (!list.contains(newMove))
						list.add(newMove);
				}

				if (piece.otherOwner(i, y) || i == x) {
					break;
				}

				if (i == 0) {
					i = 24;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	public ArrayList<Square> getMoves(IChessboard board, Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		forwardVerticalMoves(board,piece, list, ignoreKing);
		backwardVerticalMoves(board,piece, list, ignoreKing);
		leftHorizontalMoves(board, piece, list, ignoreKing);
		rightHorizontalMoves(board, piece, list, ignoreKing);

		return list;
	}
}
