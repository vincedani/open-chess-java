package main.java.board;

import main.java.pieces.Piece;

public interface IKing {
	
	public enum KingState {
		safe, checkmate, stalemate
	}

	/**
	 * Method to see is the king is checked
	 * 
	 * @return bool true if king is not safe, else returns false
	 */
	boolean isChecked(IChessboard board, Piece king);

	/**
	 * Method to check is the king is checked or stalemated
	 * 
	 * @return safe, checkmate or stalemate
	 */
	KingState isCheckmatedOrStalemated(IChessboard board,Piece king);

	/**
	 * Method to check is the king is checked by an opponent
	 * 
	 * @param s
	 *            Square where is a king
	 * @return bool true if king is save, else returns false
	 */
	boolean isSafe(IChessboard board,Piece king);

	/**
	 * Method to check will the king be safe after the move of the pieces in the
	 * given squares
	 * 
	 * @param sqIsHe	re
	 *            the original square of the piece
	 * @param sqWillBeThere
	 *            the future square of the piece
	 * @return boolean true if king is save, else returns false
	 */
	boolean willBeSafeAfterMove(IChessboard board,Square sqIsHere, Square sqWillBeThere);

}