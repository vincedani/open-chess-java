package main.java.pieces;

public interface IKing {

	/**
	 * Method to check is the king is checked
	 * 
	 * @return bool true if king is not save, else returns false
	 */
	boolean isChecked();

	/**
	 * Method to check is the king is checked or stalemated
	 * 
	 * @return int 0 if nothing, 1 if checkmate, else returns 2
	 */
	int isCheckmatedOrStalemated();

	/**
	 * Method to check is the king is checked by an opponent
	 * 
	 * @param s
	 *            Square where is a king
	 * @return bool true if king is save, else returns false
	 */
	boolean isSafe(Square s);

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
	boolean willBeSafeAfterMove(Square sqIsHere, Square sqWillBeThere);

}