package jchess.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class CircleBoardDisplay extends JOptionPane implements IBoardDisplay{

	Point topLeftPoint;
	int radius, square_height;
	private Image upDownLabel = null;
	private Image LeftRightLabel = null;
	boolean renderLabels;
	BoardLayout board_layout;
	private Square activeSquare;
	
	public void setActiveSquare(Square activeSquare) {
		this.activeSquare = activeSquare;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	Square[][] squares;

	public CircleBoardDisplay(BoardLayout board_layout, boolean renderLabels) {
		topLeftPoint = new Point(0, 0);
		radius = board_layout.image.getWidth(null) / 2;
		square_height = (radius - radius / 3) / 6;
		this.renderLabels = renderLabels;
		this.board_layout = board_layout;
		drawLabels(square_height);
	}

	public Point getTopLeftPoint() {
		if (renderLabels) {
			return new Point(topLeftPoint.x + upDownLabel.getHeight(null),
					topLeftPoint.y + upDownLabel.getHeight(null));
		}
		return topLeftPoint;
	}

	public void draw()
    {
       this.getGraphics().drawImage(board_layout.image, this.getTopLeftPoint().x, this.getTopLeftPoint().y, null);//draw an Image of chessboard
       this.drawLabels(square_height);
       this.repaint();
    }
	
	@Override
    public void update(Graphics g)
    {
        repaint();
    }
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Point topLeftPoint = this.getTopLeftPoint();
		if (renderLabels) {
			if (topLeftPoint.x <= 0 && topLeftPoint.y <= 0) {
				drawLabels(square_height);
			}

			g2d.drawImage(this.upDownLabel, 0, 0, null);
			g2d.drawImage(this.upDownLabel, 0, board_layout.image.getHeight(null) + topLeftPoint.y, null);
			g2d.drawImage(this.LeftRightLabel, 0, 0, null);
			g2d.drawImage(this.LeftRightLabel, board_layout.image.getHeight(null) + topLeftPoint.x, 0, null);
		}
		g2d.drawImage(board_layout.image, topLeftPoint.x, topLeftPoint.y, null);
		drawPieces(g);
		drawHighlightedSquares(g2d);

	}/*--endOf-paint--*/ 
	

	private void drawPieces(Graphics g) {
		for (int i = 0; i < 24; i++) // drawPiecesOnSquares
		{
			for (int y = 0; y < 6; y++) {
				if (squares[i][y].piece != null) {
					squares[i][y].piece.draw(g);// draw image of Piece
				}
			}
		} // --endOf--drawPiecesOnSquares
	}

	public void drawHighlightedSquares(Graphics2D g2d) {

		if (activeSquare != null) {
			int xi = activeSquare.getPozX();
			int yi = activeSquare.getPozY();

			int ri = radius - (yi + 1) * square_height;
			int rs = ri + square_height;

			int a1 = (6 - xi) * 15;
			int a2 = a1 - 15;
			int rm = (rs + ri) / 2;
			int am = (a1 + a2) / 2;

			int xm = topLeftPoint.x + radius + (int) (rm * Math.cos(Math.toRadians(am))) - square_height / 2;
			int ym = topLeftPoint.y + radius - (int) (rm * Math.sin(Math.toRadians(am))) - square_height / 2;

			Image tempImage = board_layout.org_sel_square;
			BufferedImage resized = new BufferedImage(square_height, square_height, BufferedImage.TYPE_INT_ARGB_PRE);
			Graphics2D imageGr = (Graphics2D) resized.createGraphics();
			imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			imageGr.drawImage(tempImage, 0, 0, square_height, square_height, null);
			imageGr.dispose();
			board_layout.sel_square = resized.getScaledInstance(square_height, square_height, 0);
			g2d.drawImage(board_layout.sel_square, xm, ym, null);

		}
	}

	public void resizeChessboard(int height) {
		BufferedImage resized = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics g = resized.createGraphics();
		g.drawImage(board_layout.orgImage, 0, 0, height, height, null);
		g.dispose();
		board_layout.image = resized.getScaledInstance(height, height, 0);
		square_height = (height - height / 3) / 6;
		;
		if (renderLabels) {
			height += 2 * (this.upDownLabel.getHeight(null));
		}
		this.setSize(height, height);

		resized = new BufferedImage((int) square_height, (int) square_height, BufferedImage.TYPE_INT_ARGB_PRE);
		g = resized.createGraphics();
		g.drawImage(board_layout.org_able_square, 0, 0, (int) square_height, (int) square_height, null);
		//g.dispose();
		board_layout.able_square = resized.getScaledInstance((int) square_height, (int) square_height, 0);

		resized = new BufferedImage((int) square_height, (int) square_height, BufferedImage.TYPE_INT_ARGB_PRE);
		g = resized.createGraphics();
		g.drawImage(board_layout.org_sel_square, 0, 0, (int) square_height, (int) square_height, null);
		g.dispose();
		board_layout.sel_square = resized.getScaledInstance((int) square_height, (int) square_height, 0);
		this.drawLabels(square_height);
	}

	protected final void drawLabels(int square_height) {
		// BufferedImage uDL = new BufferedImage(800, 800,
		// BufferedImage.TYPE_3BYTE_BGR);
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
		int addX = (square_height / 2);
		if (renderLabels) {
			addX += labelHeight;
		}

		String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h" };

		int j = 1;
		for (int i = letters.length; i > 0; i--, j++) {
			uDL2D.drawString(letters[i - 1], (square_height * (j - 1)) + addX, 10 + (labelHeight / 3));
		}

		uDL2D.dispose();
		this.upDownLabel = uDL;

		uDL = new BufferedImage(labelHeight, labelWidth + min_label_height, BufferedImage.TYPE_3BYTE_BGR);
		uDL2D = (Graphics2D) uDL.createGraphics();
		uDL2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		uDL2D.setColor(Color.white);
		// uDL2D.fillRect(0, 0, 800, 800);
		uDL2D.fillRect(0, 0, labelHeight, labelWidth + min_label_height);
		uDL2D.setColor(Color.black);
		uDL2D.setFont(new Font("Arial", Font.BOLD, 12));

		j = 1;
		for (int i = 8; i > 0; i--, j++) {
			uDL2D.drawString(new Integer(i).toString(), 3 + (labelHeight / 3), (square_height * (j - 1)) + addX);
		}

		uDL2D.dispose();
		this.LeftRightLabel = uDL;
	}


	public int getUpDownLabelHeight() {
		// TODO Auto-generated method stub
		return upDownLabel.getHeight(null);
	}

	@Override
	public int getSquareHeight() {
		// TODO Auto-generated method stub
		return square_height;
	}

	public int getHeight()
    {
        return board_layout.image.getHeight(null);
    }/*--endOf-get_height--*/

}
