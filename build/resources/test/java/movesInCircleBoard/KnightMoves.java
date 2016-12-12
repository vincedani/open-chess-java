package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class KnightMoves implements IMove {
	public static void LMove(Piece piece, int newX, int newY, ArrayList<Square> list, boolean ignoreKing) {
		if (newX < 0) {
			newX += 24;
		} else if (newX > 24) {
			newX -= 24;
		}
		if (!piece.pieceBehaviour.isout(newX, newY) && piece.pieceBehaviour.checkPiece(newX, newY)) {
			Square newMove = piece.getChessboard().getSquares()[newX][newY];
			if (ignoreKing){
				list.add(newMove);
			}else if (piece.myKing().willBeSafeWhenMoveOtherPiece(piece.getSquare(), newMove)) {
				list.add(newMove);
			}
		}
	}

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		int newX, newY;

		ArrayList<Square> list = new ArrayList<>();

		// 1
		newX = piece.getSquare().getPozX() - 2;
		newY = piece.getSquare().getPozY() + 1;

		LMove(piece, newX, newY, list, ignoreKing);

		// 2
		newX = piece.getSquare().getPozX() - 1;
		newY = piece.getSquare().getPozY() + 2;

		LMove(piece, newX, newY, list, ignoreKing);

		// 3
		newX = piece.getSquare().getPozX() + 1;
		newY = piece.getSquare().getPozY() + 2;

		LMove(piece, newX, newY, list, ignoreKing);

		// 4
		newX = piece.getSquare().getPozX() + 2;
		newY = piece.getSquare().getPozY() + 1;

		LMove(piece, newX, newY, list, ignoreKing);

		// 5
		newX = piece.getSquare().getPozX() + 2;
		newY = piece.getSquare().getPozY() - 1;

		LMove(piece, newX, newY, list, ignoreKing);

		// 6
		newX = piece.getSquare().getPozX() + 1;
		newY = piece.getSquare().getPozY() - 2;

		LMove(piece, newX, newY, list, ignoreKing);

		// 7
		newX = piece.getSquare().getPozX() - 1;
		newY = piece.getSquare().getPozY() - 2;

		LMove(piece, newX, newY, list, ignoreKing);

		// 8
		newX = piece.getSquare().getPozX() - 2;
		newY = piece.getSquare().getPozY() - 1;

		LMove(piece, newX, newY, list, ignoreKing);

		return list;
	}
}
