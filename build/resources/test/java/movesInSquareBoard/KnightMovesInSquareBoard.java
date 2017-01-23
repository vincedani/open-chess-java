package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class KnightMovesInSquareBoard implements IMove {
	public static void LMove(Piece piece, int newX, int newY, ArrayList<Square> list, boolean ignoreKing) {

		if (!piece.pieceBehaviour.isout(newX, newY) && piece.pieceBehaviour.checkPiece(newX, newY)) {
			Square newMove = piece.getSquares(newX,newY);
			if (ignoreKing || piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
				list.add(newMove);
			}
		}
	}

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		int newX, newY;

		ArrayList<Square> list = new ArrayList<>();
		// 1
		newX = piece.getPozX() - 2;
		newY = piece.getPozY() + 1;

		LMove(piece, newX, newY, list, ignoreKing);

		// 2
		newX = piece.getPozX() - 1;
		newY = piece.getPozY() + 2;

		LMove(piece, newX, newY, list, ignoreKing);

		// 3
		newX = piece.getPozX() + 1;
		newY = piece.getPozY() + 2;

		LMove(piece, newX, newY, list, ignoreKing);

		// 4
		newX = piece.getPozX() + 2;
		newY = piece.getPozY() + 1;

		LMove(piece, newX, newY, list, ignoreKing);

		// 5
		newX = piece.getPozX() + 2;
		newY = piece.getPozY() - 1;

		LMove(piece, newX, newY, list, ignoreKing);

		// 6
		newX = piece.getPozX() + 1;
		newY = piece.getPozY() - 2;

		LMove(piece, newX, newY, list, ignoreKing);

		// 7
		newX = piece.getPozX() - 1;
		newY = piece.getPozY() - 2;

		LMove(piece, newX, newY, list, ignoreKing);

		// 8
		newX = piece.getPozX() - 2;
		newY = piece.getPozY() - 1;

		LMove(piece, newX, newY, list, ignoreKing);

		return list;
	}
}
