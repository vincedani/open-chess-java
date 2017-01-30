package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class RookMovesInSquareBoard implements IMove {

	private void forwardVerticalMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = y + 1; i <= 7; i++) {// up

			if (piece.checkPiece(x, i)) {
				Square newMove = board.getSquareFromIndexes(x,i);

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board, piece.getSquare(), newMove)) {
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

	private void backwardVerticalMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = y - 1; i >= 0; i--) {// down

			if (piece.checkPiece(x, i)) {// if on this square
														// isn't piece

				Square newMove = board.getSquareFromIndexes(x,i);

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board, piece.getSquare(), newMove)) {
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

	private void rightHorizontalMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = x + 1; i <= 7; ++i) {// right

			if (piece.checkPiece(i, y)) {// if on this square
														// isn't piece

				Square newMove = board.getSquareFromIndexes(i,y);

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board, piece.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (piece.otherOwner(i, y)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}
		}
	}

	private void leftHorizontalMoves(IChessboard board, Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = x - 1; i >= 0; --i) {// left

			if (piece.checkPiece(i, piece.getPosY())) {// if
				Square newMove = board.getSquareFromIndexes(i,y);

				if (ignoreKing || piece.myKing().willBeSafeAfterMove(board, piece.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (piece.otherOwner(i, y)) {
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
		forwardVerticalMoves(board, piece, list, ignoreKing);
		backwardVerticalMoves(board, piece, list, ignoreKing);
		leftHorizontalMoves(board, piece, list, ignoreKing);
		rightHorizontalMoves(board, piece, list, ignoreKing);

		return list;
	}
}
