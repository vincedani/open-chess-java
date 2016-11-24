package jchess.board;

import java.awt.Point;

public interface IBoardDisplay {
	public Point getTopLeftPoint();
	public int getSquareHeight();
	public int getWidth();
	public void repaint();
	public int getHeight();
	public void resizeChessboard(int height);
	public void draw();
}
