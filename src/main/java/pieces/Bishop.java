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
 * Class to represent a chess pawn bishop.
 *
 */
public class Bishop extends Piece {

	public int value = 3;

	// protected static final Image imageWhite = GUI.loadImage("Bishop-W.png");
	// protected static final Image imageBlack = GUI.loadImage("Bishop-B.png");
	ArrayList<IMove> moveBehaviour;

	public Bishop(IChessboard chessboard, Player player, ArrayList<IMove> moveBehaviour) {

		super(chessboard, player, "Bishop"); // call initializer of super type:
												// Piece
		this.symbol = "B";
		this.moveBehaviour = moveBehaviour;

	}

	/**
	 * Annotation to superclass Piece changing pawns location
	 * 
	 * @return ArrayList with new position of piece
	 */

	public ArrayList<Square> allMoves(boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<Square>();
		for (IMove iMove : moveBehaviour) {
			list.addAll(iMove.getMoves(this, ignoreKing));
		}
		return list;

		// return BishopMoves.getMoves(this);
	}

}
