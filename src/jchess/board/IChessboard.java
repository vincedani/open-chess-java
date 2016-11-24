package jchess.board;

import javax.swing.JPanel;

import main.java.game.Player;

public interface IChessboard {

	public Square getSquare(int x, int y);

	public void select(Square sq);

	public void unselect();
	
	public IChessboardDisplay getDisplay();
	
	public JPanel getDisplayPanel();
	
	public void setPieces(String places, Player plWhite, Player plBlack);

	public int get_height(boolean b);

	public Square[][] getSquares();

	public void move(Square square, Square square2);

	public boolean undo();

	public boolean redo();

	public SquareBoardInitialization getInitial();

	public Square getActiveSquare();

	public void setActiveSquare(Square sq);
	
	

}