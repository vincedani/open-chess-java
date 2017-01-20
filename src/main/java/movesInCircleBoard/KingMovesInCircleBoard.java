package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.pieces.King;
import main.java.pieces.Piece;

public class KingMovesInCircleBoard implements IMove {

	/**
	 * Method to check is the king is checked by an opponent
	 * 
	 * @param s
	 *            Square where is a king
	 * @return bool true if king is save, else returns false
	 */
	public boolean isSafe(CircleBoard board, Piece king, Square s) 
	{
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 6; j++) {
				Piece boardPiece = board.getSquares()[i][j].piece;
				if (boardPiece != null && boardPiece.getPlayer() != king.getPlayer()) {
						ArrayList<Square> pieceMoves = boardPiece.allMoves(true);
						if (pieceMoves.contains(s)) {
							return false;
						}
					}
				}
			}
		

		return true;
	}
	private void regularMove(Piece piece1, ArrayList<Square> list, int x, int y, boolean ignoreKing) {
		King piece = (King) piece1;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!piece.pieceBehaviour.isout(i, j)) {// out of bounds
														// protection
					Square sq = piece.getSquares(i, j);
					if (piece.getSquare() == sq) {// if we're checking square on
													// which is King
						continue;
					} else {
						if (piece.pieceBehaviour.checkPiece(i, j)) {// if square
							//Check if will be checked
							if(piece.isSafe(sq)){
								list.add(sq);}

						}
					}
				}
			}
		}

	}

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		int x = piece.getPozX(), y = piece.getPozY();
		regularMove(piece, list, x, y, ignoreKing);
		return list;
	}

}
