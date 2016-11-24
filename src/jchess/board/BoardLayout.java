package jchess.board;

import java.awt.Image;

import jchess.gui.GUI;

public class BoardLayout {

	public static Image orgImage;// image of chessboard
	public Image image;// image of chessboard
	public Image org_sel_square;
	public Image sel_square;
	public Image org_able_square;
	public Image able_square;

	public BoardLayout(String imagePath, String selPath, String ablePath) {

		orgImage = GUI.loadImage(imagePath);
		image = orgImage;
		org_sel_square = GUI.loadImage(selPath);
		sel_square = org_sel_square;
		org_able_square = GUI.loadImage(ablePath);
		able_square = org_able_square;
	}

	

}
