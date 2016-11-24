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
import jchess.board.IChessboard;
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

	private IChessboard chessboard; // <-- this relations isn't in class diagram,
									// but it's necessary :/
	public PieceBehaviour pieceBehaviour;
	private Square square;
	private Player player;
	private String name;
	protected String symbol;
	private Image orgImage;
	private Image image;

	public Piece(IChessboard chessboard, Player player) {
		this.setChessboard(chessboard);
		this.setPlayer(player);
		this.setName(this.getClass().getSimpleName());
		this.pieceBehaviour = new PieceBehaviour(chessboard, player);

	}
	/*
	 * Method to draw piece on chessboard
	 * 
	 * @graph : where to draw
	 */

	public final void draw2(Graphics g) {
		try {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Point topLeft = this.getChessboard().getBoardDisplay2().getTopLeftPoint();
			int height = this.getChessboard().getBoardDisplay2().getSquareHeight();
			System.out.println(height);
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

	public final void draw(Graphics g) {
		try {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			Point topLeft = this.getChessboard().getBoardDisplay2().getTopLeftPoint();
			int r = this.getChessboard().getBoardDisplay2().getWidth() / 2, cx = r, cy = r;

			int hi = (r - r / 3) / 6;
			int ri = r - (this.getSquare().getPozY() + 1) * hi;
			int rs = ri + hi;

			int a1 = (6 - this.getSquare().getPozX()) * 15;
			int a2 = (6 - this.getSquare().getPozX() - 1) * 15;
			int rm = (rs + ri) / 2;
			int am = (a1 + a2) / 2;
			
			int xm = topLeft.x + cx + (int) (rm * Math.cos(Math.toRadians(am))) - hi / 2;
			int ym = topLeft.y + cy - (int) (rm * Math.sin(Math.toRadians(am))) - hi / 2;

			if (getImage() != null && g != null) {
				Image tempImage = orgImage;
				BufferedImage resized = new BufferedImage(hi, hi, BufferedImage.TYPE_INT_ARGB_PRE);
				Graphics2D imageGr = (Graphics2D) resized.createGraphics();
				imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				imageGr.drawImage(tempImage, 0, 0, hi, hi, null);
				imageGr.dispose();
				setImage(resized.getScaledInstance(hi, hi, 0));
				g2d.drawImage(getImage(), xm, ym, null);
			} else {
				System.out.println("image is null!");
			}

		} catch (java.lang.NullPointerException exc) {
			System.out.println("Something wrong when painting piece: " + exc.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void clean() {
	}

	public King myKing(IChessboard chessboard) {
		if (getPlayer().getColor().equals(Player.colors.white)) {
			return null;
		} else if (getPlayer().getColor().equals(Player.colors.black)) {
			return null;
		}
		return null;
	}

	public abstract ArrayList<Square> allMoves(IChessboard chessboard);

	public String getSymbol() {
		return this.symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IChessboard getChessboard() {
		return chessboard;
	}

	public void setChessboard(IChessboard chessboard2) {
		this.chessboard = chessboard2;
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
		// Set the image according to the color
		if (this.getPlayer().getColor() == this.getPlayer().getColor().black) {
			image = imageBlack;
		} else {
			image = imageWhite;
		}
		orgImage = image;
	}

	public void setImage(Image image) {
		// Set the image when resizing
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
