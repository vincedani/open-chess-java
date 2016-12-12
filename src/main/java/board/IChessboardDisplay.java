package main.java.board;

import java.awt.Point;

/**
 * Interface to manage the displaying behaviors of any type of chessboard.
 */
public interface IChessboardDisplay {
	/**
	 * Resize the chessboard to the given height. The chessboard keeps the same
	 * aspect ratio (width = height)
	 * 
	 * @param newHeight
	 *            the new height to resize the chessboard
	 * 
	 */
	void resizeChessboard(int newHeight);

	/**
	 * 
	 * @return current height of the chessboard
	 * 
	 */
	int getHeight();

	/**
	 * 
	 * @deprecated
	 * 
	 * 			used in the network game
	 */
	void draw();

	/**
	 * @return upper point of the chessboard
	 * 
	 */

	Point getTopLeftPoint();

}
