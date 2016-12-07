package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.King;
import main.java.pieces.Piece;
import main.java.pieces.Rook;

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
	
	public void castlingLeftMove(Piece piece1, ArrayList<Square> list, int x, int y){
			King piece = (King) piece1;
			boolean canCastling = true;

			Rook rook = (Rook) piece.getChessboard().getSquares()[0][y].piece;
			if (!rook.wasMotion()) {
				for (int i = x - 1; i > 0; i--) {// go
																			// left
					if (piece.getChessboard().getSquares()[i][y].piece != null) {
						canCastling = false;
						break;
					}
				}
				
				Square sq = piece.getChessboard().getSquares()[x - 2][y];
				Square sq1 = piece.getChessboard().getSquares()[x - 1][y];
				if (canCastling && piece.isSafe(sq) && piece.isSafe(sq1)) { 
					// can do castling when neither sq nor sq1 is checked
					list.add(sq);
				}
			}
		}
	
	public void castlingRightMove(Piece piece1, ArrayList<Square> list, int x, int y){
		King piece = (King) piece1;
		boolean canCastling = true;

		Rook rook = (Rook) piece.getChessboard().getSquares()[7][y].piece;
		if (!rook.wasMotion()) {
			for (int i = x + 1; i < 7; i++) {// go
																		// left
				if (piece.getChessboard().getSquares()[i][y].piece != null) {
					canCastling = false;
					break;
				}
			}
			
			Square sq = piece.getChessboard().getSquares()[x + 2][y];
			Square sq1 = piece.getChessboard().getSquares()[x + 1][y];
			if (canCastling && piece.isSafe(sq) && piece.isSafe(sq1)) { 
				// can do castling when neither sq nor sq1 is checked
				list.add(sq);
			}
		}
	}
	
	public ArrayList<Square> getMoves(Piece piece){
		ArrayList<Square> list = new ArrayList<>();
		int x = piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		regularMove(piece, list, x, y);
		King king = (King) piece;
		if (!king.wasMotion && !king.isChecked()) {
			// check if king was not moved before

			if (king.getChessboard().getSquares()[0][y].piece != null
					&& king.getChessboard().getSquares()[0][y].piece.getName().equals("Rook")) {
				castlingLeftMove(king, list, x, y);
			}
			if (king.getChessboard().getSquares()[7][y].piece != null
					&& king.getChessboard().getSquares()[0][y].piece.getName().equals("Rook")) {
				castlingLeftMove(king, list, x, y);
			}
		}
		
		
		return list;
	}
}
