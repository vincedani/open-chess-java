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
 * Class to represent a chess pawn rook
 * Rook can move:  
|_|_|_|X|_|_|_|_|7
|_|_|_|X|_|_|_|_|6
|_|_|_|X|_|_|_|_|5
|_|_|_|X|_|_|_|_|4
|X|X|X|B|X|X|X|X|3
|_|_|_|X|_|_|_|_|2
|_|_|_|X|_|_|_|_|1
|_|_|_|X|_|_|_|_|0
0 1 2 3 4 5 6 7
 *
 */
public class Rook extends Piece
{

    private boolean wasMotion = false;
//    protected static final Image imageWhite = GUI.loadImage("Rook-W.png");
//    protected static final Image imageBlack = GUI.loadImage("Rook-B.png");
    public static short value = 5;
	ArrayList<IMove> moveBehaviour;


    public Rook(IChessboard chessboard, Player player, ArrayList<IMove> moveBehaviour)
   {
    	super(chessboard, player, "Rook"); // call initializer of super type: Piece 
		this.symbol = "R";
		this.moveBehaviour = moveBehaviour;
    }

    /**
     *  Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new position of piece
     */
    
    public ArrayList<Square> allMoves(boolean ignoreKing)
    {	ArrayList<Square> list = new ArrayList<Square>();
        //return RookMoves.getMoves(this);
    	for (IMove iMove : moveBehaviour) {
			list.addAll(iMove.getMoves(this, ignoreKing));
		}
		return list;
    }

	public boolean wasMotion() {
		return wasMotion;
	}

	public void setWasMotion(boolean wasMotion) {
		this.wasMotion = wasMotion;
	}
}
