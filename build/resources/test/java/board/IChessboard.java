package main.java.board;

import main.java.game.Player;
import main.java.pieces.King;

public interface IChessboard {

	/**
	 * Method to get reference to square from given x and y integers after click
	 * 
	 * @param x
	 *            x position on the window
	 * @param y
	 *            y position on the window
	 * 
	 * @return reference to searched square
	 */
	public Square getSquare(int x, int y);
	
	/**
	 * Method selecting a Square in chessboard
	 * 
	 * @param sq
	 *            square to select (when clicked))
	 */
	public void select(Square sq);

	/**
	 * Method to set variables active_x_square and active_y_square to default values (-1).
	 */
	
	public void unselect();
	
	/**
	 * Method returning the Display Object of the Chessboard.
	 * 
	 * @return ChesboardDisplay Object of the Chessboard.
	 */
	public ChessboardDisplay getDisplay();
	
	/**
	 * Method setting the pieces of the Chessboard for the given set of players.
	 * 
	 * @param players
	 *            Array of Players of the game
	 */
	public void setPieces(Player[] players);

	/**
	 * Method returning the Squares Array
	 * 
	 * @return Squares Array of Squares of the Chessboard
	 */
	public Square[][] getSquares();
	
	/**
	 * Method move piece from square to square
	 * 
	 * @param begin
	 *            square from which move piece
	 * @param end
	 *            square where we want to move piece *
	 */
	public void move(Square begin, Square end);

	public boolean undo();

	public boolean redo();
	
	/**
	 * Method returning the selected square
	 * 
	 * @return selected square
	 */
	public Square getActiveSquare();
	
	/**
	 * Method returning the king of the given player
	 * 
	 * @param player given player to know the King
	 *            
	 * @return King of the same color of the given player
	 
	 */
	public King getKing(Player player);


}
