/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package jchess.pieces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import jchess.board.Chessboard;
import jchess.board.Square;
import jchess.game.Player;

import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Class to represent a piece (any kind) - this class should be extended to
 * represent pawn, bishop etc.
 */
public abstract class Piece {

	private Chessboard chessboard; // <-- this relations isn't in class diagram,
									// but it's necessary :/
	private Square square;
	private Player player;
	private String name;
	protected String symbol;
	protected static Image imageBlack;// = null;
	protected static Image imageWhite;// = null;
	private Image orgImage;
	private Image image;

	public Piece(Chessboard chessboard, Player player) {
		this.setChessboard(chessboard);
		this.setPlayer(player);
		this.setName(this.getClass().getSimpleName());

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
			Point topLeft = this.getChessboard().getTopLeftPoint();
			int height = this.getChessboard().get_square_height();
			int x = (this.getSquare().getPozX() * height) + topLeft.x;
			int y = (this.getSquare().getPozY() * height) + topLeft.y;
			float addX = (height - getImage().getWidth(null)) / 2;
			float addY = (height - getImage().getHeight(null)) / 2;
			if (getImage() != null && g != null) {
				Image tempImage = orgImage;
				BufferedImage resized = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB_PRE);
				Graphics2D imageGr = (Graphics2D) resized.createGraphics();
				imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				imageGr.drawImage(tempImage, 0, 0, height, height, null);
				imageGr.dispose();
				setImage(resized.getScaledInstance(height, height, 0));
				g2d.drawImage(getImage(), x, y, null);
			} else {
				System.out.println("image is null!");
			}

		} catch (java.lang.NullPointerException exc) {
			System.out.println("Something wrong when painting piece: " + exc.getMessage());
		}
	}

	void clean() {
	}

	/**
	 * method check if Piece can move to given square
	 * 
	 * @param square
	 *            square where piece want to move (Square object)
	 * @param allmoves
	 *            all moves which can piece do
	 */
	boolean canMove(Square square, ArrayList<Square> allmoves) {
		// throw new UnsupportedOperationException("Not supported yet.");
		ArrayList<Square> moves = allmoves;
		for (Iterator<Square> it = moves.iterator(); it.hasNext();) {
			Square sq = it.next();// get next from iterator
			if (sq == square) {// if address is the same
				return true; // piece canMove
			}
		}
		return false;// if not, piece cannot move
	}

	abstract public ArrayList<Square> allMoves();

	/**
	 * Method is useful for out of bounds protection
	 * 
	 * @param x
	 *            x position on chessboard
	 * @param y
	 *            y position on chessboard
	 * @return true if parameters are out of bounds (array)
	 */
	protected boolean isout(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return true;
		}
		return false;
	}

	/**
	 * @param x
	 *            y position on chessboard
	 * @param y
	 *            y position on chessboard
	 * @return true if can move, false otherwise
	 */
	protected boolean checkPiece(int x, int y) {
		if (getChessboard().squares[x][y].piece != null
				&& getChessboard().squares[x][y].piece.getName().equals("King")) {
			return false;
		}
		Piece piece = getChessboard().squares[x][y].piece;
		if (piece == null || // if this sqhuare is empty
				piece.getPlayer() != this.getPlayer()) // or piece is another
														// player
		{
			return true;
		}
		return false;
	}

	/**
	 * Method check if piece has other owner than calling piece
	 * 
	 * @param x
	 *            x position on chessboard
	 * @param y
	 *            y position on chessboard
	 * @return true if owner(player) is different
	 */
	protected boolean otherOwner(int x, int y) {
		Square sq = getChessboard().squares[x][y];
		if (sq.piece == null) {
			return false;
		}
		if (this.getPlayer() != sq.piece.getPlayer()) {
			return true;
		}
		return false;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Chessboard getChessboard() {
		return chessboard;
	}

	public void setChessboard(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image imageBlack, Image imageWhite) {
		//Set the image according to the color
		if (this.getPlayer().getColor() == this.getPlayer().getColor().black) {
			image = imageBlack;
		} else {
			image = imageWhite;
		}
		orgImage = image;
	}

	public void setImage(Image image) {
		//Set the image when resizing
		this.image = image;
	}

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public Image getOrgImage() {
		return orgImage;
	}

	public void setOrgImage(Image orgImage) {
		this.orgImage = orgImage;
	}
}
