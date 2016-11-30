package jchess.board;

import java.awt.Image;

import main.java.gui.GUI;

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