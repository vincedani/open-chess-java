package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class BishopMoves implements IMove{

	private static void backwardRightMoves(Piece piece, ArrayList<Square> list) {
		int x = piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		for (int h = x - 1, i = y + 1; !piece.pieceBehaviour.isout(h, i); --h, ++i) // left-up
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't
														// piece
			{
				if (piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(),
						piece.getChessboard().getSquares()[h][i])) {
					list.add(piece.getChessboard().getSquares()[h][i]);
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

	private static void backwardLeftMoves(Piece piece, ArrayList<Square> list) {
		int x = piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		for (int h = x - 1, i = y - 1; !piece.pieceBehaviour.isout(h, i); --h, --i) // left-down
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't piece
			{
				if (piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(), piece.getChessboard().getSquares()[h][i])) {
					list.add(piece.getChessboard().getSquares()[h][i]);
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
	private static void forwardRightMoves(Piece piece, ArrayList<Square> list) {
		int x = piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		for (int h = x + 1, i = y + 1; !piece.pieceBehaviour.isout(h, i); ++h, ++i) // right-up
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't
														// piece
			{
				if (piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(), piece.getChessboard().getSquares()[h][i])) {
					list.add(piece.getChessboard().getSquares()[h][i]);
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
	
	private static void forwardLeftMoves(Piece piece, ArrayList<Square> list) {
		int x = piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		for (int h = x + 1, i = y - 1; !piece.pieceBehaviour.isout(h, i); ++h, --i) // right-down
		{
			if (piece.pieceBehaviour.checkPiece(h, i)) // if on this square
														// isn't piece
			{
				if (piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(), piece.getChessboard().getSquares()[h][i])) {
					list.add(piece.getChessboard().getSquares()[h][i]);
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
	
	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing){
		ArrayList<Square> list = new ArrayList<>();
		forwardLeftMoves(piece, list);
		forwardRightMoves(piece, list);
		backwardLeftMoves(piece, list);
		backwardRightMoves(piece, list);
		
		return list;
	}
}
