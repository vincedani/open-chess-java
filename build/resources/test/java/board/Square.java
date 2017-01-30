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
package main.java.board;

import main.java.game.Player;
import main.java.pieces.Piece;

/**
 * Class to represent a chessboard square
 */
public class Square {

	private int posX;
	private int posY;
	private Piece piece = null;

	public Square(int pozX, int pozY, Piece piece) {
		this.setPosX(pozX);
		this.setPosY(pozY);
		this.setPiece(piece);
		
	}/*--endOf-Square--*/

	public Square(Square square) {
		this.setPosX(square.getPosX());
		this.setPosY(square.getPosY());
	}

	public Square clone(Square square) {
		return new Square(square);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	
	/**
	 * Set the given piece to this Square and set this Square to the given piece
	 * 
	 * @param piece
	 *            the piece to set
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
		if(piece != null){
		this.piece.setSquare(this);}
	}

	public Player getPlayer() {
		return piece.getPlayer();
	}

	public Piece getPiece() {
		return piece;
	}
}
