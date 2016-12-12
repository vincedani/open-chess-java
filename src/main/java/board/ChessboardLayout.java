package main.java.board;

import java.awt.Image;

import main.java.gui.GUI;

/**
 * Class to manage the layout of a given Chessboard. Load the background image and the corresponding select and able square icons. 
 */

public class ChessboardLayout {
	public Image orgImage;
	public Image image;
	public Image orgSelSquare;
	public Image selSquare;
	public Image orgAbleSquare;
	public Image ableSquare;
	

	public ChessboardLayout(String imagePath, String selSquarePath, String ableSquarePath) {
		orgImage = GUI.loadImage(imagePath);
		orgSelSquare = GUI.loadImage(selSquarePath);
		orgAbleSquare = GUI.loadImage(ableSquarePath);
	
		image=orgImage;
		selSquare= orgSelSquare;
		ableSquare = orgAbleSquare;
	}
}