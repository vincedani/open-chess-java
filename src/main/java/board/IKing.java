package main.java.board;

import main.java.pieces.Piece;

public interface IKing {

	public enum KingState {
		safe, checkmate, stalemate
	}

	/**
	 * Method to see is the king is checked
	 * 
	 * @param board
	 *            instance of the chessboard that contains the king
	 * @param king
	 *            instance of the king to check
	 * @return bool true if king is not safe, else returns false
	 */
	boolean isChecked(IChessboard board, Piece king);

	/**
	 * Method to check is the king is checked or stalemated
	 * 
	 * @param board
	 *            instance of the chessboard that contains the king
	 * @param king
	 *            instance of the king piece to check
	 * @return safe, checkmate or stalemate
	 */
	KingState isCheckmatedOrStalemated(IChessboard board, Piece king);

	/**
	 * Method to check is the king is checked by an opponent
	 * 
	 * @param board
	 *            instance of the chessboard that contains the king
	 * @param king
	 *            instance of the king piece to check
	 * @param sq
	 *            Square where is a king
	 * @return bool true if king is save, else returns false
	 */
	boolean isSafe(IChessboard board, Piece king, Square sq);

	/**
	 * Method to check will the king be safe after the move of the pieces in the
	 * given squares
	 * 
	 * @param board
	 *            instance of the chessboard that contains the king
	 * @param sqIsHere
	 *            the original square of the piece
	 * @param sqWillBeThere
	 *            the future square of the piece
	 * @return boolean true if king is save, else returns false
	 */
	boolean willBeSafeAfterMove(IChessboard board, Square sqIsHere, Square sqWillBeThere);

}