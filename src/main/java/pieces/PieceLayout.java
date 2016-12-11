package main.java.pieces;

import java.awt.Image;

import main.java.gui.GUI;

public class PieceLayout {
	public Image orgImage;
	public Image image;

	public PieceLayout(String imagePath) {
		orgImage = GUI.loadImage(imagePath);
		image = orgImage;
	}

	public void setImage(Image scaledInstance) {
		// TODO Auto-generated method stub
		this.image = scaledInstance;
	}

}