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
package main.java.pieces;

import java.awt.Graphics;
import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Player.colors;

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
	private PieceLayout layout;
	private PieceDisplay display;

	public Piece(IChessboard chessboard, Player player, String imagePath) {

		this.setChessboard(chessboard);
		this.setPlayer(player);
		
		colors color = player.getColor();
		String imageColorPath = "";
		switch (color) {
			case black:	imageColorPath ="-Black.png";
						break;
			case white:	imageColorPath ="-White.png";
						break;
			case blue:	imageColorPath ="-Blue.png";
						break;
			case red:	imageColorPath ="-Red.png";
						break;
			case green:	imageColorPath ="-Green.png";
						break;
		}
		//System.out.println("Color escogido: " + color);
		imagePath += imageColorPath;
		System.out.println("Image: " + imagePath);
		
		this.setName(this.getClass().getSimpleName());
		this.setLayout(new PieceLayout(imagePath));
		this.pieceBehaviour = new PieceBehaviour(chessboard, player);
		this.display = new PieceDisplay(this);
	}
	

	public King myKing() {
		return chessboard.getKing(player);
	}
	
	public abstract ArrayList<Square> allMoves(IChessboard chessboard2);

	public IChessboard getChessboard() {
		return chessboard;
	}

	public void setChessboard(IChessboard chessboard2) {
		this.chessboard = chessboard2;
	}
	
	public PieceBehaviour getPieceBehaviour() {
		return pieceBehaviour;
	}

	public void setPieceBehaviour(PieceBehaviour pieceBehaviour) {
		this.pieceBehaviour = pieceBehaviour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	
	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	

	public PieceLayout getLayout() {
		return layout;
	}


	public void setLayout(PieceLayout layout) {
		this.layout = layout;
	}


	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		display.draw(g);
	}
}
