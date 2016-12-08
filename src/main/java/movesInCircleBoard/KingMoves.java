package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.King;
import main.java.pieces.Piece;

public class KingMoves implements IMove {
	
	public void regularMove(Piece piece1,  ArrayList<Square> list, int x, int y){
		King piece = (King) piece1;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!piece.pieceBehaviour.isout(i, j)) {// out of bounds
														// protection
					Square sq = piece.getChessboard().getSquares()[i][j];
					if (piece.getSquare() == sq) {// if we're checking square on
													// which is King
						continue;
					} else {
						if (piece.pieceBehaviour.checkPiece(i, j)) {// if square
																	// is empty
							if (piece.isSafe(sq)) {
								list.add(sq);
							}
						}
					}
				}
			}
		}

	}
	
	public ArrayList<Square> getMoves(Piece piece){
		ArrayList<Square> list = new ArrayList<>();
		int x = piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		regularMove(piece, list, x, y);
		return list;
	}
	
}
