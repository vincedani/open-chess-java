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
import main.java.board.IKing;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Player.colors;
import main.java.game.Settings.BoardType;
import main.java.pieces.PieceFactory.PieceType;

/**
 * Class to represent a piece (any kind) - this class should be extended to
 * represent pawn, bishop etc.
 */
public class Piece {

	private PieceBehaviour pieceBehaviour;
	private Square square;
	private Player player;
	private PieceType type;
	protected String symbol;
	private PieceLayout layout;
	private PieceDisplay display;
	private IMove moveBehaviour;
	private boolean wasMoved = false;

	
	public Piece(IChessboard chessboard, Player player, PieceType pieceType) {

		this.setPlayer(player);

		colors color = player.getColor();
		String imageColorPath = "";
		
		if(pieceType.equals(PieceType.Knight)){
			symbol = pieceType.toString().substring(0, 2);
		}else{
			symbol = pieceType.toString().substring(0, 1);
		}
		
		switch (color) {
		case black:
			imageColorPath = "-Black.png";
			symbol += "-Bk";
			break;
		case white:
			imageColorPath = "-White.png";
			symbol += "-W";
			break;
		case blue:
			imageColorPath = "-Blue.png";
			symbol += "-Bl";
			break;
		
		}
		this.setType(pieceType);
		imageColorPath = pieceType.toString()+ imageColorPath;
		setLayout(new PieceLayout(imageColorPath));
		this.pieceBehaviour = chessboard.getPieceBehaviour();
		this.display = new PieceDisplay(this);
	}

	public ArrayList<Square> allMoves(IChessboard board, boolean ignoreKing) {
		
		return getMoveBehaviour().getMoves(board, this, ignoreKing);
	}
	/*
	public IKing myKing() {
		return chessboard.getKing(player);
	}
*/
	
	public int getPosX(){
		return getSquare().getPosX();
	}
	
	public int getPosY(){
		return getSquare().getPosY();
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
	 * @return if wasMoved
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

	/**
	 * @return the type
	 */
	public PieceType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PieceType type) {
		this.type = type;
	}
	
	public IMove getMoveBehaviour() {
		return moveBehaviour;
	}

	public boolean isout(int x, int y) {
		
		return pieceBehaviour.isout(x, y);
	}

	public boolean checkPiece(int i, int j) {
		
		return pieceBehaviour.checkPiece(this, i, j);
	}

	public IKing myKing() {
		return pieceBehaviour.getKing(this.player);
	}

	public Piece myKingAsPiece() {
		return pieceBehaviour.getKingAsPiece(this.player);
	}
	
	public boolean otherOwner(int i, int j) {
		return pieceBehaviour.otherOwner(this, i, j);
	}

	/**
	 * @param moveBehaviour the moveBehaviour to set
	 */
	public void setMoveBehaviour(IMove moveBehaviour) {
		this.moveBehaviour = moveBehaviour;
	}

	public BoardType getChessboardType() {
		return pieceBehaviour.getChessboardType();
	}

	public IChessboard getChessboard() {
		return pieceBehaviour.getChessboard();
	}

}
