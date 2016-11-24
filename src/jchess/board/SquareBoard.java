package jchess.board;

import java.awt.Graphics2D;
import java.awt.Point;

import jchess.game.MovesTable;
import jchess.game.Player;
import jchess.game.Settings;
import jchess.pieces.Bishop;
import jchess.pieces.King;
import jchess.pieces.Knight;
import jchess.pieces.Pawn;
import jchess.pieces.Queen;
import jchess.pieces.Rook;

public class SquareBoard {}
	/*
	 
	 public SquareBoard(Settings settings, MovesTable moves_history){
		 super(settings, moves_history, "square_chessboard.png", 8, 8);
		 
		
	 }
	 
	 
	 /** Method setPieces on begin of new game or loaded game
	     * @param places string with pieces to set on chessboard
	     * @param plWhite reference to white player
	     * @param plBlack reference to black player
	     */
	/*
	    public void setPieces(String places, Player[] players, boolean upsideDown)
	    {

	        if (places.equals("")) //if newGame
	        {
	        	this.setPieces4NewGame(upsideDown, players);

	        } 
	        else //if loadedGame
	        {
	            return;
	        }
	    }/*--endOf-setPieces--*/


	    /**
	     *
	     */
	/*
	    private void setPieces4NewGame(boolean upsideDown, Player[] players)
	    {
	        this.setFigures4NewGame(0, players[0], upsideDown);
	        this.setPawns4NewGame(1, players[0]);
	        this.setFigures4NewGame(7, players[1], upsideDown);
	        this.setPawns4NewGame(6, players[1]);
	    }/*--endOf-setPieces(boolean upsideDown)--*/


	    /**  method set Figures in row (and set Queen and King to right position)
	     *  @param i row where to set figures (Rook, Knight etc.)
	     *  @param player which is owner of pawns
	     *  @param upsideDown if true white pieces will be on top of chessboard
	     * */
	/*
	    private void setFigures4NewGame(int i, Player player, boolean upsideDown)
	    {

	        if (i != 0 && i != 7)
	        {
	            System.out.println("error setting figures like rook etc.");
	            return;
	        }
	        else if (i == 0)
	        {
	            player.setGoDown(true);
	        }

	        this.squares[0][i].setPiece(new Rook(this, player));
	        this.squares[7][i].setPiece(new Rook(this, player));
	        this.squares[1][i].setPiece(new Knight(this, player));
	        this.squares[6][i].setPiece(new Knight(this, player));
	        this.squares[2][i].setPiece(new Bishop(this, player));
	        this.squares[5][i].setPiece(new Bishop(this, player));
	        if (upsideDown)
	        {
	            this.squares[4][i].setPiece(new Queen(this, player));
	            if (player.getColor() == Player.colors.white)
	            {
	                this.squares[3][i].setPiece(kingWhite = new King(this, player));
	            }
	            else
	            {
	                this.squares[3][i].setPiece(kingBlack = new King(this, player));
	            }
	        }
	        else
	        {
	            this.squares[3][i].setPiece(new Queen(this, player));
	            if (player.getColor() == Player.colors.white)
	            {
	                this.squares[4][i].setPiece(kingWhite = new King(this, player));
	            }
	            else
	            {
	                this.squares[4][i].setPiece(kingBlack = new King(this, player));
	            }
	        }
	    }

	    /**  method set Pawns in row
	     *  @param i row where to set pawns
	     *  @param player player which is owner of pawns
	     * */
	/*
	    private void setPawns4NewGame(int i, Player player)
	    {
	        if (i != 1 && i != 6)
	        {
	            System.out.println("error setting pawns etc.");
	            return;
	        }
	        for (int x = 0; x < 8; x++)
	        {
	            this.squares[x][i].setPiece(new Pawn(this, player));
	        }
	    }
	    
	    /** method to get reference to square from given x and y integeres
	     * @param x x position on chessboard
	     * @param y y position on chessboard
	     * @return reference to searched square
	     */
	/*
	    public Square getSquare(int x, int y, boolean renderLabels)
	    { 
	        if ((x > this.get_height()) || (y > this.get_width())) //test if click is out of chessboard
	        {
	            System.out.println("click out of chessboard.");
	            return null;
	        }
	        if (renderLabels)
	        {
	            x -= this.getUpDownLabel().getHeight(null);
	            y -= this.getUpDownLabel().getHeight(null);
	        }
	        double square_x = x / getSquare_height();//count which field in X was clicked
	        double square_y = y / getSquare_height();//count which field in Y was clicked

	        if (square_x > (int) square_x) //if X is more than X parsed to Integer
	        {
	            square_x = (int) square_x + 1;//parse to integer and increment
	        }
	        if (square_y > (int) square_y) //if X is more than X parsed to Integer
	        {
	            square_y = (int) square_y + 1;//parse to integer and increment
	        }
	        //Square newActiveSquare = this.squares[(int)square_x-1][(int)square_y-1];//4test
	        System.out.println("square_x: " + square_x + " square_y: " + square_y + " \n"); //4tests
	        Square result;
	        try
	        {
	            result = this.squares[(int) square_x - 1][(int) square_y - 1];
	        }
	        catch (java.lang.ArrayIndexOutOfBoundsException exc)
	        {
	            System.out.println("!!Array out of bounds when getting Square with Chessboard.getSquare(int,int) : " + exc);
	            return null;
	        }
	        return this.squares[(int) square_x - 1][(int) square_y - 1];
	    }

	    public void drawHighlightedSquares(Graphics2D g2d, Point topLeftPoint) {
			if ((this.active_x_square != 0) && (this.active_y_square != 0)) // if
																			// some
																			// square
																			// is
																			// active
			{
				g2d.drawImage(sel_square, ((this.active_x_square - 1) * (int) getSquare_height()) + topLeftPoint.x,
						((this.active_y_square - 1) * (int) getSquare_height()) + topLeftPoint.y, null);// draw
																										// image
																										// of
																										// selected
																										// square

				// Square tmpSquare = this.squares[(int) (this.active_x_square -
				// 1)][(int) (this.active_y_square - 1)];
				/*
				 * if (tmpSquare.piece != null) { this.moves = this.squares[(int)
				 * (this.active_x_square - 1)][(int) (this.active_y_square -
				 * 1)].piece.allMoves(this); }
				 * 
				 * for (java.util.Iterator it = moves.iterator(); moves != null &&
				 * it.hasNext();) { Square sq = (Square) it.next();
				 * g2d.drawImage(able_square, (sq.getPozX() * (int)
				 * getSquare_height()) + topLeftPoint.x, (sq.getPozY() * (int)
				 * getSquare_height()) + topLeftPoint.y, null); }
				 */
		

