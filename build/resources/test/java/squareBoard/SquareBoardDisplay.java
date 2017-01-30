package main.java.squareBoard;

/**
 * Class to manage the displaying of a SquareChessboard. 
 */

import java.awt.Color;
import java.awt.Font;
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

public class SquareBoardDisplay extends ChessboardDisplay {
	public Square activeSquare;
	public Point topLeft;
	public int square_height;

	public Image upDownLabel;
	public Image LeftRightLabel;

	public static final int img_x = 5;// image x position (used in JChessView
	public static final int img_y = img_x;// image y position (used in
	public static final int img_widht = 480;// image width
	public static final int img_height = img_widht;// image height

	private ChessboardLayout board_layout;
	boolean renderLabels, upsideDown;
	Square[][] squares;
	SquareBoard board;

	public SquareBoardDisplay(Image upDownLabel, Image leftRightLabel, Point topLeft, SquareBoard board) {
		this.upDownLabel = upDownLabel;
		LeftRightLabel = leftRightLabel;
		this.topLeft = topLeft;
		this.board_layout = board.board_layout;
		this.renderLabels = true;
		this.upsideDown = false;
		this.squares = board.initial.getSquares();
		this.board = board;

		activeSquare = null;
		square_height = img_height / 8;

		this.setDoubleBuffered(true);
		drawLabels();
	}

	public Point getTopLeftPoint() {
		if (renderLabels) {
			return new Point(topLeft.x + upDownLabel.getHeight(null), topLeft.y + upDownLabel.getHeight(null));
		}
		return topLeft;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Point topLeftPoint = this.getTopLeftPoint();
		if (renderLabels) {
			if (topLeftPoint.x <= 0 && topLeftPoint.y <= 0) {
				this.drawLabels();
			}
			g2d.drawImage(upDownLabel, 0, 0, null);
			g2d.drawImage(upDownLabel, 0, board_layout.image.getHeight(null) + topLeftPoint.y, null);
			g2d.drawImage(LeftRightLabel, 0, 0, null);
			g2d.drawImage(LeftRightLabel, board_layout.image.getHeight(null) + topLeftPoint.x, 0, null);
		}
		g2d.drawImage(board_layout.image, topLeftPoint.x, topLeftPoint.y, null);
		drawPieces(g2d);
		drawHighlightedSquares(g2d);
	}/*--endOf-paint--*/

	private void drawHighlightedSquares(Graphics2D g2d) {
		if (activeSquare != null) {
			int xi = activeSquare.getPosX();
			int yi = activeSquare.getPosY();
			Point topLeft = this.getTopLeftPoint();
			Image tempImage = board_layout.orgSelSquare;
			BufferedImage resized = resizeImage(tempImage, square_height);
			board_layout.selSquare = resized.getScaledInstance(square_height, square_height, 0);
			g2d.drawImage(board_layout.selSquare, topLeft.x + xi * square_height, topLeft.y + yi * square_height, null);

			if (activeSquare.getPiece() != null) {
				ArrayList<Square> moves = getActiveSquare().getPiece().allMoves(board, false);
				for (Square sq : moves) {
					Point pointSq = new Point(sq.getPosX()*square_height, sq.getPosY()*square_height);
					tempImage = board_layout.orgAbleSquare;
					resized = resizeImage(tempImage, square_height);
					board_layout.ableSquare = resized.getScaledInstance(square_height, square_height, 0);

					g2d.drawImage(board_layout.ableSquare, topLeft.x + pointSq.x, topLeft.y + pointSq.y, null);
				}
			}

		}
	}

	private BufferedImage resizeImage(Image tempImage, int height) {
		BufferedImage resized = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D imageGr = resized.createGraphics();
		imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		imageGr.drawImage(tempImage, topLeft.y, topLeft.y, height, height, null);
		imageGr.dispose();
		return resized;
	}

	private Square getActiveSquare() {
		return activeSquare;
	}

	private void drawPieces(Graphics g) {
		for (int i = 0; i < 8; i++) // drawPiecesOnSquares
		{
			for (int y = 0; y < 8; y++) {
				if (squares[i][y].getPiece() != null) {
					squares[i][y].getPiece().draw(g);// draw image of Piece
				}
			}
		} // --endOf--drawPiecesOnSquares
	}

	public void resizeChessboard(int height) {
		BufferedImage resized = resizeImage(board_layout.orgImage, height);
		board_layout.image = resized.getScaledInstance(height, height, 0);
		square_height = height / 8;
		if (renderLabels) {
			height += 2 * (upDownLabel.getHeight(null));
		}
		this.setSize(height, height);
		this.drawLabels();
	}

	protected final void drawLabels() {
		int min_label_height = 20;
		int labelHeight = (int) Math.ceil(square_height / 4);
		labelHeight = (labelHeight < min_label_height) ? min_label_height : labelHeight;
		int labelWidth = (int) Math.ceil(square_height * 8 + (2 * labelHeight));
		BufferedImage uDL = new BufferedImage(labelWidth + min_label_height, labelHeight, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D uDL2D = (Graphics2D) uDL.createGraphics();
		uDL2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		uDL2D.setColor(Color.white);

		uDL2D.fillRect(0, 0, labelWidth + min_label_height, labelHeight);
		uDL2D.setColor(Color.black);
		uDL2D.setFont(new Font("Arial", Font.BOLD, 12));
		int addX = (int) (square_height / 2);
		if (renderLabels) {
			addX += labelHeight;
		}

		String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h" };
		if (!upsideDown) {
			for (int i = 1; i <= letters.length; i++) {
				uDL2D.drawString(letters[i - 1], (square_height * (i - 1)) + addX, 10 + (labelHeight / 3));
			}
		} else {
			int j = 1;
			for (int i = letters.length; i > 0; i--, j++) {
				uDL2D.drawString(letters[i - 1], (square_height * (j - 1)) + addX, 10 + (labelHeight / 3));
			}
		}
		uDL2D.dispose();
		upDownLabel = uDL;

		uDL = new BufferedImage(labelHeight, labelWidth + min_label_height, BufferedImage.TYPE_3BYTE_BGR);
		uDL2D = (Graphics2D) uDL.createGraphics();
		uDL2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		uDL2D.setColor(Color.white);
		// uDL2D.fillRect(0, 0, 800, 800);
		uDL2D.fillRect(0, 0, labelHeight, labelWidth + min_label_height);
		uDL2D.setColor(Color.black);
		uDL2D.setFont(new Font("Arial", Font.BOLD, 12));

		if (upsideDown) {
			for (int i = 1; i <= 8; i++) {
				uDL2D.drawString(new Integer(i).toString(), 3 + (labelHeight / 3), (square_height * (i - 1)) + addX);
			}
		} else {
			int j = 1;
			for (int i = 8; i > 0; i--, j++) {
				uDL2D.drawString(new Integer(i).toString(), 3 + (labelHeight / 3), (square_height * (j - 1)) + addX);
			}
		}
		uDL2D.dispose();
		LeftRightLabel = uDL;
	}

}