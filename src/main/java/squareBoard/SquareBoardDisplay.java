package main.java.squareBoard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import main.java.board.ChessboardDisplay;
import main.java.board.ChessboardLayout;
import main.java.board.Square;

@SuppressWarnings("serial")
public class SquareBoardDisplay extends ChessboardDisplay {
	public Square activeSquare;
	public Image upDownLabel;
	public Image LeftRightLabel;
	public Point topLeft;
	public int active_x_square;
	public int active_y_square;
	public float square_height;
	
	public static final int img_x = 5;//image x position (used in JChessView class!)
    public static final int img_y = img_x;//image y position (used in JChessView class!)
    public static final int img_widht = 480;//image width
    public static final int img_height = img_widht;//image height
    
	private ChessboardLayout board_layout;
	boolean renderLabels, upsideDown;
	Square[][] squares;
	SquareBoard board;

	public SquareBoardDisplay(Image upDownLabel, Image leftRightLabel, Point topLeft, boolean renderLabels, boolean upsideDown, SquareBoard board) {
		this.upDownLabel = upDownLabel;
		LeftRightLabel = leftRightLabel;
		this.topLeft = topLeft;
		this.board_layout=board.board_layout;
		this.renderLabels= renderLabels;
		this.upsideDown= upsideDown;
		this.squares= board.initial.getSquares();
		this.board= board;
		
		activeSquare = null;
        square_height = img_height / 8;
        active_x_square = -1;
        active_y_square = -1;
        
        this.setDoubleBuffered(true);
        drawLabels((int) square_height);
	}
	
	/**
     * Method to draw Chessboard and their elements (pieces etc.)
     * @deprecated 
     */
    public void draw()
    {
        this.getGraphics().drawImage(board_layout.image, this.getTopLeftPoint().x, this.getTopLeftPoint().y, null);//draw an Image of chessboard
        this.drawLabels();
        this.repaint();
    }/*--endOf-draw--*/


    /**
     * Annotations to superclass Game updateing and painting the crossboard
     */
    @Override
    public void update(Graphics g)
    {
        repaint();
    }

    public Point getTopLeftPoint()
    {
        if (renderLabels)
        {
            return new Point(topLeft.x + upDownLabel.getHeight(null), topLeft.y + upDownLabel.getHeight(null));
        }
        return topLeft;
    }

    @Override
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Point topLeftPoint = this.getTopLeftPoint();
        if (renderLabels)
        {
            if(topLeftPoint.x <= 0 && topLeftPoint.y <= 0) //if renderLabels and (0,0), than draw it! (for first run)
            {
                this.drawLabels();
            }
            g2d.drawImage(upDownLabel, 0, 0, null);
            g2d.drawImage(upDownLabel, 0, board_layout.image.getHeight(null) + topLeftPoint.y, null);
            g2d.drawImage(LeftRightLabel, 0, 0, null);
            g2d.drawImage(LeftRightLabel, board_layout.image.getHeight(null) + topLeftPoint.x, 0, null);
        }
        g2d.drawImage(board_layout.image, topLeftPoint.x, topLeftPoint.y, null);//draw an Image of chessboard
        drawPieces(g);
        if ((active_x_square != -1) && (active_y_square != -1)) //if some square is active
        {
            g2d.drawImage(board_layout.selSquare, 
                            (active_x_square * (int) square_height) + topLeftPoint.x,
                            (active_y_square * (int) square_height) + topLeftPoint.y, null);//draw image of selected square
            Square tmpSquare = squares[active_x_square][active_y_square];
            if (tmpSquare.piece != null)
            {
                board.moves = squares[active_x_square][active_y_square].piece.allMoves(board);
                for (Iterator it = board.moves.iterator(); board.moves != null && it.hasNext();)
                {
                    Square sq = (Square) it.next();
                    g2d.drawImage(board_layout.ableSquare, 
                            (sq.getPozX() * (int) square_height) + topLeftPoint.x,
                            (sq.getPozY() * (int) square_height) + topLeftPoint.y, null);
                }
            }

            
        }
    }/*--endOf-paint--*/

	private void drawPieces(Graphics g) {
		for (int i = 0; i < 8; i++) //drawPiecesOnSquares
        {
            for (int y = 0; y < 8; y++)
            {
                if (squares[i][y].piece != null)
                {
                    squares[i][y].piece.draw(g);//draw image of Piece
                }
            }
        }//--endOf--drawPiecesOnSquares
	}


    public void resizeChessboard(int height)
    {
        BufferedImage resized = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics g = resized.createGraphics();
        g.drawImage(board_layout.orgImage, 0, 0, height, height, null);
        g.dispose();
        board_layout.image = resized.getScaledInstance(height, height, 0);
        square_height = (float) (height / 8);
        if (renderLabels)
        {
            height += 2 * (upDownLabel.getHeight(null));
        }
        this.setSize(height, height);

        resized = new BufferedImage((int)  square_height, (int)  square_height, BufferedImage.TYPE_INT_ARGB_PRE);
        g = resized.createGraphics();
        g.drawImage(board_layout.orgAbleSquare, 0, 0, (int)  square_height, (int)  square_height, null);
        g.dispose();
        board_layout.ableSquare = resized.getScaledInstance((int)  square_height, (int)  square_height, 0);

        resized = new BufferedImage((int)  square_height, (int)  square_height, BufferedImage.TYPE_INT_ARGB_PRE);
        g = resized.createGraphics();
        g.drawImage(board_layout.orgSelSquare, 0, 0, (int)  square_height, (int)  square_height, null);
        g.dispose();
        board_layout.selSquare = resized.getScaledInstance((int)  square_height, (int)  square_height, 0);
        this.drawLabels();
    }

    protected void drawLabels()
    {
        this.drawLabels((int) square_height);
    }

    protected final void drawLabels(int square_height)
    {
        //BufferedImage uDL = new BufferedImage(800, 800, BufferedImage.TYPE_3BYTE_BGR);
        int min_label_height = 20;
        int labelHeight = (int) Math.ceil(square_height / 4);
        labelHeight = (labelHeight < min_label_height) ? min_label_height : labelHeight;
        int labelWidth =  (int) Math.ceil(square_height * 8 + (2 * labelHeight)); 
        BufferedImage uDL = new BufferedImage(labelWidth + min_label_height, labelHeight, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D uDL2D = (Graphics2D) uDL.createGraphics();
        uDL2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        uDL2D.setColor(Color.white);

        uDL2D.fillRect(0, 0, labelWidth + min_label_height, labelHeight);
        uDL2D.setColor(Color.black);
        uDL2D.setFont(new Font("Arial", Font.BOLD, 12));
        int addX = (square_height / 2);
        if (renderLabels)
        {
            addX += labelHeight;
        }

        String[] letters =
        {
            "a", "b", "c", "d", "e", "f", "g", "h"
        };
        if (!upsideDown)
        {
            for (int i = 1; i <= letters.length; i++)
            {
                uDL2D.drawString(letters[i - 1], (square_height * (i - 1)) + addX, 10 + (labelHeight / 3));
            }
        }
        else
        {
            int j = 1;
            for (int i = letters.length; i > 0; i--, j++)
            {
                uDL2D.drawString(letters[i - 1], (square_height * (j - 1)) + addX, 10 + (labelHeight / 3));
            }
        }
        uDL2D.dispose();
        upDownLabel = uDL;

        uDL = new BufferedImage(labelHeight, labelWidth + min_label_height, BufferedImage.TYPE_3BYTE_BGR);
        uDL2D = (Graphics2D) uDL.createGraphics();
        uDL2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        uDL2D.setColor(Color.white);
        //uDL2D.fillRect(0, 0, 800, 800);
        uDL2D.fillRect(0, 0, labelHeight, labelWidth + min_label_height);
        uDL2D.setColor(Color.black);
        uDL2D.setFont(new Font("Arial", Font.BOLD, 12));

        if (upsideDown)
        {
            for (int i = 1; i <= 8; i++)
            {
                uDL2D.drawString(new Integer(i).toString(), 3 + (labelHeight / 3), (square_height * (i - 1)) + addX);
            }
        }
        else
        {
            int j = 1;
            for (int i = 8; i > 0; i--, j++)
            {
                uDL2D.drawString(new Integer(i).toString(), 3 + (labelHeight / 3), (square_height * (j - 1)) + addX);
            }
        }
        uDL2D.dispose();
        LeftRightLabel = uDL;
    }
    public void componentMoved(ComponentEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentShown(ComponentEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentHidden(ComponentEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}