package main.java.board;

import java.awt.Point;

public interface IChessboardDisplay {

	void resizeChessboard(int get_height);

	int getHeight();

	void draw();

	Point getTopLeftPoint();

}
