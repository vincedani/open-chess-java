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
	public boolean isSafe(CircleBoard board, Piece king) 
	{
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 6; j++) {
				Piece boardPiece = board.getSquares()[i][j].piece;
				if (boardPiece != null && boardPiece.getPlayer() != king.getPlayer()) {
						ArrayList<Square> pieceMoves = boardPiece.allMoves(true);
						if (pieceMoves.contains(king.getSquare())) {
							return false;
						}
					}
				}
			}
		

		return true;
	}
	
	/**
	 * Method to check will the king be safe after the move of the pieces in the
	 * given squares
	 * 
	 * @param sqIsHere
	 *            the original square of the piece
	 * @param sqWillBeThere
	 *            the future square of the piece
	 * @return boolean true if king is save, else returns false
	 */
	public boolean willBeSafeAfterMove(CircleBoard board, Piece king, Square sqIsHere, Square sqWillBeThere) {
		Piece tmp = sqWillBeThere.piece;
		sqWillBeThere.piece = sqIsHere.piece; // move without redraw
		sqIsHere.piece = null;
		boolean ret;
		
		ret = isSafe(board, king);
		
		sqIsHere.piece = sqWillBeThere.piece;
		sqWillBeThere.piece = tmp;

		return ret;
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
							if(piece.willBeSafeAfterMove(piece.getSquare(), sq)){
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
