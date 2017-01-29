package main.java.circleBoard;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.java.board.ChessboardDisplay;
import main.java.board.ChessboardLayout;
import main.java.board.Square;

public class CircleBoardDisplay extends ChessboardDisplay {

	private Square activeSquare;
	private Point topLeftPoint;
	
	public static final int img_x = 5;// image x position (used in JChessView
	public static final int img_y = img_x;// image y position (used in
	public static final int img_widht = 480;// image width
	public static final int img_height = img_widht;// image height

	private ChessboardLayout board_layout;
	Square[][] squares;
	CircleBoard board;

	public CircleBoardDisplay(Point topLeft, CircleBoard board) {
		this.topLeftPoint = topLeft;
		this.board_layout = board.board_layout;
		this.squares = board.getInitial().getSquares();
		this.board = board;
		
		setActiveSquare(null);
		this.setDoubleBuffered(true);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(board_layout.image, topLeftPoint.x, topLeftPoint.y, null);
		drawPieces(g2d);
		drawHighlightedSquares(g2d);

	}/*--endOf-paint--*/

	private void drawPieces(Graphics g) {
		for (int i = 0; i < 24; i++) // drawPiecesOnSquares
		{
			for (int y = 0; y < 6; y++) {
				if (squares[i][y].getPiece() != null) {
					squares[i][y].getPiece().draw(g);// draw image of Piece
				}
			}
		} // --endOf--drawPiecesOnSquares
	}

	public Point indexToCartesian(Point p1) {
		int xi = p1.x;
		int yi = p1.y;

		int ri = getRadius() - (yi + 1) * get_square_height();
		int rs = ri + get_square_height();

		int a1 = (6 - xi) * 15;
		int a2 = a1 - 15;
		int rm = (rs + ri) / 2;
		int am = (a1 + a2) / 2;

		int xm = topLeftPoint.x + getRadius() + (int) (rm * Math.cos(Math.toRadians(am)))
				- get_square_height() / 2;
		int ym = topLeftPoint.y + getRadius() - (int) (rm * Math.sin(Math.toRadians(am)))
				- get_square_height() / 2;

		return new Point(xm, ym);

	}

	public void drawHighlightedSquares(Graphics2D g2d) {

		if (getActiveSquare() != null) {
			int xi = getActiveSquare().getPosX();
			int yi = getActiveSquare().getPosY();

			Point pm = indexToCartesian(new Point(xi, yi));

			int xm = pm.x;
			int ym = pm.y;

			Image tempImage = board_layout.orgSelSquare;
			BufferedImage resized = resizeImage(tempImage, get_square_height());
			board_layout.selSquare = resized.getScaledInstance(get_square_height(), get_square_height(), 0);
			g2d.drawImage(board_layout.selSquare, xm, ym, null);

			if (getActiveSquare().getPiece() != null) {
				ArrayList<Square> moves = getActiveSquare().getPiece().allMoves(board, false);
				for (Square sq : moves) {
					Point pointSq = indexToCartesian(new Point(sq.getPosX(), sq.getPosY()));

					tempImage = board_layout.orgAbleSquare;
					resized = resizeImage(tempImage, get_square_height());
					board_layout.ableSquare = resized.getScaledInstance(get_square_height(), get_square_height(), 0);

					g2d.drawImage(board_layout.ableSquare, pointSq.x, pointSq.y, null);
				}
			}

		}
	}

	private BufferedImage resizeImage(Image tempImage, int height) {
		BufferedImage resized = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D imageGr = resized.createGraphics();
		imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		imageGr.drawImage(tempImage, 0, 0, height, height, null);
		imageGr.dispose();
		return resized;
	}

	public void resizeChessboard(int height) {
		BufferedImage resized = resizeImage(board_layout.orgImage, height);
		board_layout.image = resized.getScaledInstance(height, height, 0);
		this.setSize(height, height);
	}

	public int getHeight() {
		return board_layout.image.getHeight(null);
	}/*--endOf-get_height--*/

	public int get_square_height() {
		return (getRadius() - getRadius() / 3) / 6;
	}

	public int getRadius() {
		return board_layout.image.getHeight(null) / 2;
	}

	@Override
	public Point getTopLeftPoint() {
		return topLeftPoint;
	}


	/**
	 * @return the activeSquare
	 */
	public Square getActiveSquare() {
		return activeSquare;
	}

	/**
	 * @param activeSquare the activeSquare to set
	 */
	public void setActiveSquare(Square activeSquare) {
		this.activeSquare = activeSquare;
	}

}
