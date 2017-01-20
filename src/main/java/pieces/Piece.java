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
import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Player.colors;

/**
 * Class to represent a piece (any kind) - this class should be extended to
 * represent pawn, bishop etc.
 */
public class Piece {

	private IChessboard chessboard; // <-- this relations isn't in class
									// diagram,
									// but it's necessary :/
	public PieceBehaviour pieceBehaviour;
	private Square square;
	private Player player;
	private String name;
	protected String symbol;
	private PieceLayout layout;
	private PieceDisplay display;
	ArrayList<IMove> moveBehaviour;
	private boolean wasMoved = false;

	
	public IMove getMoveBehaviour() {
		return moveBehaviour.get(0);
	}

	public Piece(IChessboard chessboard, Player player, String name) {

		this.setChessboard(chessboard);
		this.setPlayer(player);

		colors color = player.getColor();
		String imageColorPath = "";
		switch (color) {
		case black:
			imageColorPath = "-Black.png";
			break;
		case white:
			imageColorPath = "-White.png";
			break;
		case blue:
			imageColorPath = "-Blue.png";
			break;
		}
		this.setName(name);
		name += imageColorPath;
		setLayout(new PieceLayout(name));
		this.pieceBehaviour = new PieceBehaviour(chessboard, player);
		this.display = new PieceDisplay(this);
	}

	public ArrayList<Square> allMoves(boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<Square>();
		for (IMove iMove : moveBehaviour) {
			list.addAll(iMove.getMoves(this, ignoreKing));
		}
		return list;
	}
	
	public King myKing() {
		return chessboard.getKing(player);
	}

	public IChessboard getChessboard() {
		return chessboard;
	}
	
	public Square getSquares(int i, int j) {
		Square square = getChessboard().getSquares()[i][j];
		return square;
	}

	public int getPozX(){
		return getSquare().getPozX();
	}
	
	public int getPozY(){
		return getSquare().getPozY();
	}
	
	public int allMovesSize (Piece boardPiece, boolean ignoreKing){
		return boardPiece.allMoves(false).size();
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


	public void draw(Graphics g) {
		display.draw(g);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the layout
	 */
	public PieceLayout getLayout() {
		return layout;
	}

	/**
	 * @param layout the layout to set
	 */
	public void setLayout(PieceLayout layout) {
		this.layout = layout;
	}

	/**
	 * @return the wasMoved
	 */
	public boolean wasMoved() {
		return wasMoved;
	}

	/**
	 * @param wasMoved the wasMoved to set
	 */
	public void setWasMoved(boolean wasMoved) {
		this.wasMoved = wasMoved;
	}

	public String getSymbol() {
		return this.symbol;
	}
}
