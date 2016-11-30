package main.java.board;

import javax.swing.JPanel;

import main.java.game.Player;
import main.java.pieces.King;
import main.java.pieces.Piece;

public interface IChessboard {

	public Square getSquare(int x, int y);

	public void select(Square sq);

	public void unselect();
	
	public ChessboardDisplay getDisplay();
	
	public void setPieces(String places, Player plWhite, Player plBlack);

	public int get_height(boolean b);

	public Square[][] getSquares();

	public void move(Square square, Square square2);

	public boolean undo();

	public boolean redo();

	public Square getActiveSquare();

	public void setActiveSquare(Square sq);
	
	public King getKing(Player player);

	public Piece getTwoSquareMovedPawn();

	public int get_square_height();

}
