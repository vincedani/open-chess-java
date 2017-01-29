package main.java.board;

import main.java.game.Player;
import main.java.pieces.Piece;
import main.java.pieces.PieceBehaviour;

/**
 * Interface to manage the logical behaviors of any type of chessboard.
 */
public interface IChessboard {

	/**
	 * Get the reference to the square for the given x and y integers after
	 * click
	 * 
	 * @param x
	 *            x position on the window
	 * @param y
	 *            y position on the window
	 * 
	 * @return reference to searched square
	 */
	public Square getSquareFromCoordinates(int x, int y);

	/**
	 * Get the reference to the square for the given i and j indexes
	 * 
	 * @param i
	 *            i index in the board
	 * @param j
	 *            j index in the board
	 * 
	 * @return reference to searched square
	 */
	public Square getSquareFromIndexes(int i, int j);
	
	/**
	 * Select a Square in chessboard
	 * 
	 * @param sq
	 *            square to select (when clicked))
	 */
	public void select(Square sq);

	/**
	 * Set variables active_x_square and active_y_square to default values (-1).
	 */

	public void unselect();

	/**
	 * 
	 * @return selected square of the Chessboard
	 */
	public Square getActiveSquare();
	
	/**
	 * 
	 * @return ChesboardDisplay Object of the Chessboard.
	 */
	public ChessboardDisplay getDisplay();

	/**
	 * Set the initial configuration of the pieces before the game for the given
	 * set of players.
	 * 
	 * @param players
	 *            Array of players of the game
	 */
	public void setPieces(Player[] players);


	/**
	 * Move piece from a square to a different one
	 * 
	 * @param begin
	 *            square from which move piece
	 * @param end
	 *            square where we want to move piece
	 * @param displayWindow 
	 */
	public void move(Square begin, Square end, Boolean displayWindow);
	

	/**
	 * 
	 * @param player
	 *            given player whose King is requested
	 * 
	 * @return King of the same color of the given player, null if the color is
	 *         not found
	 * 
	 */
	public Piece getKing(Player player);

	public PieceBehaviour getPieceBehaviour();
}
