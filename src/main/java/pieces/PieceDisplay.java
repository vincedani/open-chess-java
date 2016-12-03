package main.java.pieces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import main.java.LogToFile;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.squareBoard.SquareBoard;

public class PieceDisplay {
	IPieceDisplay display;
	public PieceDisplay(Piece piece) {
		
		if(piece.getChessboard() instanceof CircleBoard)
			display = new CirclePieceDisplay(piece);
		else if(piece.getChessboard() instanceof SquareBoard)
			display = new SquarePieceDisplay(piece);
	}
	public void draw(Graphics g){
		display.draw(g);
	}
	
	public interface IPieceDisplay{
		public void draw(Graphics g);
	}
	private class CirclePieceDisplay implements IPieceDisplay {
		CircleBoard board;
		Square square;
		private PieceLayout layout;
		
		public CirclePieceDisplay(Piece piece){
			board = (CircleBoard) piece.getChessboard();
			square = piece.getSquare();
			layout = piece.getLayout();
			
		}
		
		public final void draw(Graphics g) {
			try {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				Point topLeft = board.getDisplay().getTopLeftPoint();
				//int r = this.getChessboard().getDisplay().getWidth() / 2, cx = r, cy = r;

				int r = board.getRadius();
				int hi = board.get_square_height();
				
				int ri = r - (square.getPozY() + 1) * hi;
				int rs = ri + hi;
				int rm = (rs + ri) / 2;
				
				int ai = (6 - square.getPozX()) * 15;
				int as = (6 - square.getPozX() - 1) * 15;
				int am = (as + ai) / 2;
				
				int x = topLeft.x + r + (int) (rm * Math.cos(Math.toRadians(am))) - hi / 2;
				int y = topLeft.y + r - (int) (rm * Math.sin(Math.toRadians(am))) - hi / 2;

				if (layout.image != null && g != null) {
					Image tempImage = layout.orgImage;
					
					BufferedImage resized = new BufferedImage(hi, hi, BufferedImage.TYPE_INT_ARGB_PRE);
					Graphics2D imageGr = (Graphics2D) resized.createGraphics();
					imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					imageGr.drawImage(tempImage, 0, 0, hi, hi, null);
					imageGr.dispose();
					
					layout.setImage(resized.getScaledInstance(hi, hi, 0));
					g2d.drawImage(layout.image, x, y, null);
				} else {
					//System.out.println("image is null!");
					LogToFile.log(null, "Debug", "image is null!");
				}

			} catch (java.lang.NullPointerException exc) {
				//System.out.println("Something wrong when painting piece: " + exc.getMessage());
				LogToFile.log(exc, "Error", "Something wrong when painting piece: " + exc.getMessage());
			}
		}
	}
	private class SquarePieceDisplay implements IPieceDisplay {
		
		SquareBoard board;
		Square square;
		private PieceLayout layout;
		
		public SquarePieceDisplay(Piece piece){
			board = (SquareBoard) piece.getChessboard();
			square = piece.getSquare();
			layout = piece.getLayout();
			
		}
		/*
		 * Method to draw piece on chessboard
		 * 
		 * @graph : where to draw
		 */

		public final void draw(Graphics g) {
			try {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				Point topLeft = board.getDisplay().getTopLeftPoint();
				int height = board.get_square_height();

				int x = (square.getPozX() * height) + topLeft.x;
				int y = (square.getPozY() * height) + topLeft.y;
				
				if (layout.image != null && g != null) {
					Image tempImage = layout.orgImage;
					
					BufferedImage resized = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB_PRE);
					Graphics2D imageGr = (Graphics2D) resized.createGraphics();
					imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					imageGr.drawImage(tempImage, 0, 0, height, height, null);
					imageGr.dispose();
					
					layout.setImage(resized.getScaledInstance(height, height, 0));
					g2d.drawImage(layout.image, x, y, null);
				} else {
					//System.out.println("image is null!");
					LogToFile.log(null, "Debug", "image is null!");
				}

			} catch (java.lang.NullPointerException exc) {
				//System.out.println("Something wrong when painting piece: " + exc.getMessage());
				LogToFile.log(exc, "Error", "Something wrong when painting piece: " + exc.getMessage());
			}
		}
	}
}


