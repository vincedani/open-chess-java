package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.King;
import main.java.pieces.Piece;

public class KingMovesInSquareBoard implements IMove {

	public void regularMove(Piece piece1, ArrayList<Square> list, int x, int y) {
		King piece = (King) piece1;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!piece.pieceBehaviour.isout(i, j)) {// out of bounds
														// protection
					Square sq = piece.getSquare(i, j);
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

	public void castlingLeftMove(Piece piece1, ArrayList<Square> list, int x, int y) {
		King tempPiece = (King) piece1;
		boolean canCastling = true;

		Piece rook = tempPiece.getSquare(0, y).piece;
		if (!rook.wasMoved()) {
			for (int i = x - 1; i > 0; i--) {// go
												// left
				if (tempPiece.getSquare(i,y).piece != null) {
					canCastling = false;
					break;
				}
			}

			Square sq = tempPiece.getSquare(x - 2, y);
			Square sq1 = tempPiece.getSquare(x - 1, y);
			if (canCastling && tempPiece.isSafe(sq) && tempPiece.isSafe(sq1)) {
				// can do castling when neither sq nor sq1 is checked
				list.add(sq);
			}
		}
	}

	public void castlingRightMove(Piece piece1, ArrayList<Square> list, int x, int y) {
		King piece = (King) piece1;
		boolean canCastling = true;

		Piece rook = piece.getSquare(7,y).piece;
		if (!rook.wasMoved()) {
			for (int i = x + 1; i < 7; i++) {// go
												// left
				if (piece.getSquare(i,y).piece != null) {
					canCastling = false;
					break;
				}
			}

			Square sq = piece.getSquare(x + 2,y);
			Square sq1 = piece.getSquare(x + 1,y);
			if (canCastling && piece.isSafe(sq) && piece.isSafe(sq1)) {
				// can do castling when neither sq nor sq1 is checked
				list.add(sq);
			}
		}
	}

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		int x = piece.getPosX(), y = piece.getPosY();
		regularMove(piece, list, x, y);
		Piece king = piece;
		if (king.wasMoved()) {
			// check if king was not moved before
			Piece tempPiece = king.getSquare(0, y).piece;
			if (tempPiece != null && tempPiece.getName().equals("Rook")) {
				castlingLeftMove(king, list, x, y);
			}
			if (king.getSquare(7, y).piece != null && tempPiece.getName().equals("Rook")) {
				castlingLeftMove(king, list, x, y);
			}
		}

		return list;
	}
}
