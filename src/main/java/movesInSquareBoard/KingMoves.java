package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.King;
import main.java.pieces.Piece;
import main.java.pieces.Rook;

public class KingMoves implements IMove {
	
	public void regularMove(Piece piece, int x, int y, ArrayList<Square> list){
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!piece.pieceBehaviour.isout(i, j)) {// out of bounds
														// protection
					Square sq = piece.getSquare()[i][j]; //esto era de chessboard
					if (piece.getSquare() == sq) {// if we're checking square on
													// which is King
						continue;
					} else {
						if (piece.pieceBehaviour.checkPiece(i, j)) {// if square
																	// is empty
							if (isSafe(sq)) {
								list.add(sq);
							}
						}
					}
				}
			}
		}

	}
	
	public void castlingLeftMove(IChessboard chessboard, int x, int y, ArrayList<Square> list){

			boolean canCastling = true;

			Rook rook = (Rook) chessboard.getSquares()[0][y].piece;
			if (!rook.wasMotion()) {
				for (int i = x - 1; i > 0; i--) {// go
																			// left
					if (chessboard.getSquares()[i][y].piece != null) {
						canCastling = false;
						break;
					}
				}
				
				Square sq = chessboard.getSquares()[x - 2][y];
				Square sq1 = chessboard.getSquares()[x - 1][y];
				if (canCastling && this.isSafe(sq) && this.isSafe(sq1)) { 
					// can do castling when neither sq nor sq1 is checked
					list.add(sq);
				}
			}
		}
	
	public void castlingRightMove(IChessboard chessboard, int x, int y, ArrayList<Square> list){

		boolean canCastling = true;

		Rook rook = (Rook) chessboard.getSquares()[7][y].piece;
		if (!rook.wasMotion()) {
			for (int i = x + 1; i < 7; i++) {// go
																		// left
				if (chessboard.getSquares()[i][y].piece != null) {
					canCastling = false;
					break;
				}
			}
			
			Square sq = chessboard.getSquares()[x + 2][y];
			Square sq1 = chessboard.getSquares()[x + 1][y];
			if (canCastling && this.isSafe(sq) && this.isSafe(sq1)) { 
				// can do castling when neither sq nor sq1 is checked
				list.add(sq);
			}
		}
	}
	
	public ArrayList<Square> getMoves(Piece piece){
		ArrayList<Square> list = new ArrayList<>();
		regularMove(piece, list, 1);
		if ((piece.getPlayer().isGoDown() && piece.getSquare().getPozY() == 1)
				|| (!piece.getPlayer().isGoDown() && piece.getSquare().getPozY() == 6)) {
			regularMove(piece, list, 2);
		}
		
		captureMove(piece, list, piece.getSquare().getPozX()-1);
		enPassantMove(piece, list, piece.getSquare().getPozX()-1, -1);
		
		//right
		captureMove(piece, list, piece.getSquare().getPozX()+1);
		enPassantMove(piece, list, piece.getSquare().getPozX()+1, 1);
		
		
		return list;
	}
}
