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
import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;

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
 *  
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
	public boolean passedCenter;
//	protected static final Image imageWhite = GUI.loadImage("Pawn-W.png");
//	protected static final Image imageBlack = GUI.loadImage("Pawn-B.png");
	public static short value = 1;
	ArrayList<IMove> moveBehaviour;
	
	public Pawn(IChessboard chessboard, Player player,ArrayList<IMove> moveBehaviour ) {
		super(chessboard, player, "Pawn"); // call initializer of super type: Piece 
		this.symbol = "";
		this.moveBehaviour = moveBehaviour;
	}

	/**
	 * Annotation to superclass Piece changing pawns location
	 * 
	 * @return ArrayList with new position of piece
	 */

	public ArrayList<Square> allMoves(boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<Square>();
		/*
		ArrayList<Square> list = new ArrayList<Square>();
		int first, second;
		King myKing = myKing();
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
	*/
		for (IMove iMove : moveBehaviour) {
			list.addAll(iMove.getMoves(this, ignoreKing));
		}
		return list;
	}


	void promote(Piece newPiece) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
