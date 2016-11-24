package jchess.board;

import javax.swing.JOptionPane;

import jchess.game.Player;

public interface IChessboard {
	public void initializeBoard(Player[] players, String places);
	public JOptionPane getBoardDisplay();
	public IBoardDisplay getBoardDisplay2();
	public CircleBoardDisplay getBoardDisplay3();
	public Square getActiveSquare();
	public Square getSquare(int x, int y);
	public Square[][] getSquares();
	public void select(Square sq);
	public void unselect();

}
