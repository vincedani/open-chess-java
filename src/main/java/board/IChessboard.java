package main.java.board;

import main.java.game.Player;
import main.java.pieces.King;
import main.java.pieces.Piece;

public interface IChessboard {

	/**
	 * method to get reference to square from given x and y integers after click
	 * 
	 * @param x
	 *            x position on chessboard
	 * @param y
	 *            y position on chessboard
	 * @return reference to searched square
	 */
	public Square getSquare(int x, int y);
	
	/**
	 * Method selecting piece in chessboard
	 * 
	 * @param sq
	 *            square to select (when clicked))
	 */
	public void select(Square sq);

	/**
	 * Method set variables active_x_square & active_y_square to default values (-1).
	 */
	
	public void unselect();
	
	public ChessboardDisplay getDisplay();
	
	public void setPieces(String places, Player[] players);

	public int get_height(boolean b);

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

	public Square getActiveSquare();

	public void setActiveSquare(Square sq);
	
	/**
	 * Method return the king of the given player
	 * 
	 * @param plater
	 *            square from which move piece
	 * @param end
	 *            square where we want to move piece *
	 */
	public King getKing(Player player);

	public Piece getTwoSquareMovedPawn();

	public int get_square_height();

}
