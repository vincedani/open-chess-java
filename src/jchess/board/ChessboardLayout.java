package jchess.board;

import java.awt.Image;

import jchess.game.MovesTable;
import jchess.gui.GUI;
import jchess.pieces.Piece;

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