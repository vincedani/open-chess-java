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

import java.util.ArrayList;

import jchess.board.Chessboard;
import jchess.board.Square;
import jchess.game.Player;
import jchess.gui.GUI;

import java.awt.Image;

/**
 * Class to represent a chess pawn knight
 */
public class Knight extends Piece
{

    public static short value = 3;
    protected static final Image imageWhite = GUI.loadImage("Knight-W.png");
    protected static final Image imageBlack = GUI.loadImage("Knight-B.png");

    public Knight(Chessboard chessboard, Player player)
    {
        super(chessboard, player);//call initializer of super type: Piece
        this.symbol = "N";
        this.setImage(imageBlack, imageWhite);
    }

    public void regularMove(Chessboard chessboard, King myKing, int newX, int newY, ArrayList<Square> list){
    	
    	if (!pieceBehaviour.isout(newX, newY) && pieceBehaviour.checkPiece(newX, newY))
        {		Square newMove= chessboard.squares[newX][newY];
                if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), newMove))
                {
                    list.add(newMove);
                }   
        }
    }
    /**
     *  Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new position of pawn
     */
    public ArrayList<Square> allMoves(Chessboard chessboard)
    {
        ArrayList<Square> list = new ArrayList<Square>();
        King myKing = myKing(chessboard);

        // knight all moves
        //  _______________ Y:
        // |_|_|_|_|_|_|_|_|7
        // |_|_|_|_|_|_|_|_|6
        // |_|_|2|_|3|_|_|_|5
        // |_|1|_|_|_|4|_|_|4
        // |_|_|_|K|_|_|_|_|3
        // |_|8|_|_|_|5|_|_|2
        // |_|_|7|_|6|_|_|_|1
        // |_|_|_|_|_|_|_|_|0
        //X:0 1 2 3 4 5 6 7
        //

        int newX, newY;

        //1
        newX = this.getSquare().getPozX() - 2;
        newY = this.getSquare().getPozY() + 1;

       regularMove(chessboard,myKing, newX, newY, list);

        //2
        newX = this.getSquare().getPozX() - 1;
        newY = this.getSquare().getPozY() + 2;

        regularMove(chessboard, myKing,newX, newY, list);

        //3
        newX = this.getSquare().getPozX() + 1;
        newY = this.getSquare().getPozY() + 2;

        regularMove(chessboard, myKing,newX, newY, list);

        //4
        newX = this.getSquare().getPozX() + 2;
        newY = this.getSquare().getPozY() + 1;

        regularMove(chessboard, myKing,newX, newY, list);

        //5
        newX = this.getSquare().getPozX() + 2;
        newY = this.getSquare().getPozY() - 1;

        regularMove(chessboard, myKing,newX, newY, list);

        //6
        newX = this.getSquare().getPozX() + 1;
        newY = this.getSquare().getPozY() - 2;

        regularMove(chessboard, myKing,newX, newY, list);

        //7
        newX = this.getSquare().getPozX() - 1;
        newY = this.getSquare().getPozY() - 2;

        regularMove(chessboard, myKing,newX, newY, list);

        //8
        newX = this.getSquare().getPozX() - 2;
        newY = this.getSquare().getPozY() - 1;

        regularMove(chessboard, myKing,newX, newY, list);

        return list;
    }
}
