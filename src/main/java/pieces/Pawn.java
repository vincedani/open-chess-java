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

import java.util.ArrayList;

import main.java.board.Chessboard;
import main.java.board.Square;
import main.java.game.Player;
import main.java.gui.GUI;

import java.awt.Image;

/**
 * Class to represent a pawn piece Pawn can move only forward and can beat only
 * across In first move pawn can move 2 squares pawn can be upgraded to rook,
 * knight, bishop, Queen if it's in the squares nearest the side where opponent
 * is located First move of pawn: 
 * |_|_|_|_|_|_|_|_|7 
 * |_|_|_|_|_|_|_|_|6
 * |_|_|_|X|_|_|_|_|5 
 * |_|_|_|X|_|_|_|_|4 
 * |_|_|_|P|_|_|_|_|3 
 * |_|_|_|_|_|_|_|_|2
 * |_|_|_|_|_|_|_|_|1 
 * |_|_|_|_|_|_|_|_|0
 *  0 1 2 3 4 5 6 7
 *
 * Move of a pawn:
 * 
 * |_|_|_|_|_|_|_|_|7 
 * |_|_|_|_|_|_|_|_|6 
 * |_|_|_|_|_|_|_|_|5 
 * |_|_|_|X|_|_|_|_|4
 * |_|_|_|P|_|_|_|_|3 
 * |_|_|_|_|_|_|_|_|2 
 * |_|_|_|_|_|_|_|_|1 
 * |_|_|_|_|_|_|_|_|0 
 *  0 1 2 3 4 5 6 7 
 *  Beats with can take pawn: 
 * |_|_|_|_|_|_|_|_|7 
 * |_|_|_|_|_|_|_|_|6
 * |_|_|_|_|_|_|_|_|5 
 * |_|_|X|_|X|_|_|_|4 
 * |_|_|_|P|_|_|_|_|3 
 * |_|_|_|_|_|_|_|_|2
 * |_|_|_|_|_|_|_|_|1 
 * |_|_|_|_|_|_|_|_|0 
 * 0 1 2 3 4 5 6 7
 */
public class Pawn extends Piece {

	boolean down;
	protected static final Image imageWhite = GUI.loadImage("Pawn-W.png");
	protected static final Image imageBlack = GUI.loadImage("Pawn-B.png");
	public static short value = 1;

	public Pawn(Chessboard chessboard, Player player) {
		super(chessboard, player);
		this.symbol = "";
		this.setImage(imageBlack, imageWhite);
	}

	/**
	 * Check if the normal movement of a pawn is valid, return null when not
	 * newY is currentY+1 or currentY+2 when is the first movement
	 */

	public void regularMove(Chessboard chessboard, King myKing, int newY, ArrayList<Square> list) {
		if (!this.pieceBehaviour.isout(this.getSquare().getPozX(), newY)) {
			Square moveSq = chessboard.squares[this.getSquare().getPozX()][newY];

			if (moveSq.piece == null && myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), moveSq)) {
				list.add(moveSq);

			}
		}
	}
	
	public void captureMove(Chessboard chessboard, King myKing, int newX, int newY, ArrayList<Square> list){
		if (!this.pieceBehaviour.isout(newX, newY)) {
			Square moveSq = chessboard.squares[newX][newY];
		if (moveSq.piece != null) {// check if can hit left
			if (this.getPlayer() != moveSq.piece.getPlayer() && !moveSq.piece.getName().equals("King")) {
				//Why it can't be the king
					if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), moveSq)) {
						list.add(moveSq);
					}
				}
			}
		}
		
	}
	
	public void enPassantMove(Chessboard chessboard, King myKing, int newX, int newY, int i ,ArrayList<Square> list){
		if (!this.pieceBehaviour.isout(newX, newY + i)) {
			Square attSq = getChessboard().squares[newX][newY];
			Square moveSq = getChessboard().squares[newX][newY + i];
			if (attSq.piece != null && Chessboard.twoSquareMovedPawn != null
					&& attSq == Chessboard.twoSquareMovedPawn.getSquare()) {
				// check if can hit left
				if (this.getPlayer() != attSq.piece.getPlayer() && !attSq.piece.getName().equals("King")) {
					// Why it can't be the king
					if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), moveSq)) {
						list.add(moveSq);

					}
				}
			}
		}
	}

	/**
	 * Annotation to superclass Piece changing pawns location
	 * 
	 * @return ArrayList with new position of piece
	 */

	public ArrayList<Square> allMoves(Chessboard chessboard) {
		ArrayList<Square> list = new ArrayList<Square>();
		int first, second;
		King myKing = myKing(chessboard);
		int x= this.getSquare().getPozX(), y= this.getSquare().getPozY();

		if (this.getPlayer().isGoDown()) {// check if player "go" down or up
			first = y + 1;// if yes, change value
			second = y + 2;// if yes, change value
		} else {
			first = y - 1;// number where to move
			second = y - 2;// number where to move
													// (only in first move)
		}

		if (!pieceBehaviour.isout(x, first)) {
			regularMove(chessboard, myKing, first, list);
						
			if ((getPlayer().isGoDown() && y == 1)
					|| (!getPlayer().isGoDown() && y == 6)) {
				regularMove(chessboard, myKing, second, list);
			}
			//left
			captureMove(chessboard, myKing, x - 1, first, list);
			enPassantMove(chessboard, myKing, x - 1, y, -1, list);
			
			//right
			captureMove(chessboard, myKing, x + 1, first, list);
			enPassantMove(chessboard, myKing, x + 1, y, 1, list);
			
		}
	
		return list;
	}


	void promote(Piece newPiece) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
