package main.java.board;

import java.util.ArrayList;

import main.java.pieces.Piece;
/**
 * Interface to manage the movement behaviors of a piece in any type of chessboard. 
 */
public interface IMove {
	/**
	 * Return the possible moves of the given piece
	 * 
	 * @param piece
	 *            the instance of the piece to extract the possible moves
	 * 
	 * @param ignoreKing
	 *            if active, it will not be checked if the King is in Check or
	 *            Stalemate
	 * 
	 * @return an ArrayList of Squares with the possible moves of the piece
	 * 
	 */

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing);
}
