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
 * Class to represent a chess pawn knight
 */
public class Knight extends Piece
{

    public static short value = 3;
//    protected static final Image imageWhite = GUI.loadImage("Knight-W.png");
//    protected static final Image imageBlack = GUI.loadImage("Knight-B.png");
	ArrayList<IMove> moveBehaviour;

    
    public Knight(IChessboard chessboard, Player player, ArrayList<IMove> moveBehaviour)
    {
    	super(chessboard, player, "Knight"); // call initializer of super type: Piece 
		this.symbol = "N";
		this.moveBehaviour = moveBehaviour;

    }

    public void regularMove(IChessboard chessboard, King myKing, int newX, int newY, ArrayList<Square> list){
    	
    	if (!pieceBehaviour.isout(newX, newY) && pieceBehaviour.checkPiece(newX, newY))
        {		Square newMove= chessboard.getSquares()[newX][newY];
                if (myKing.willBeSafeAfterMove(this.getSquare(), newMove))
                {
                    list.add(newMove);
                }   
        }
    }
    /**
     *  Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new position of pawn
     */
    public ArrayList<Square> allMoves(boolean ignoreKing)
    {
    	ArrayList<Square> list = new ArrayList<Square>();
    	for (IMove iMove : moveBehaviour) {
			list.addAll(iMove.getMoves(this, ignoreKing));
		}
		return list;
        //return KnightMoves.getMoves(this);
    }
}
