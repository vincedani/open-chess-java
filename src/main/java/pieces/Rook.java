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

import java.awt.Image;
import java.util.ArrayList;

import jchess.board.Chessboard;
import jchess.board.Square;
import main.java.game.Player;
import main.java.gui.GUI;


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
    protected static final Image imageWhite = GUI.loadImage("Rook-W.png");
    protected static final Image imageBlack = GUI.loadImage("Rook-B.png");
    public static short value = 5;

    public Rook(Chessboard chessboard, Player player)
    {
        super(chessboard, player);//call initializer of super type: Piece
        //this.setImages("Rook-W.png", "Rook-B.png");
        this.symbol = "R";
        this.setImage(imageBlack, imageWhite);
    }

    /**
     *  Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new position of piece
     */
    
    public ArrayList<Square> allMoves(Chessboard chessboard)
    {
        ArrayList<Square> list = new ArrayList<Square>();
        int x= this.getSquare().getPozX(), y=this.getSquare().getPozY();
        King myKing = myKing(chessboard);
        for (int i = y + 1; i <= 7; ++i)
        {//up

            if (this.pieceBehaviour.checkPiece(x, i))
            {//if on this square isn't piece
            	Square newMove= chessboard.initial.squares[x][i];
                
                    if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), newMove))
                    {
                        list.add(newMove);
                    }
                                
                if (this.pieceBehaviour.otherOwner(x, i))
                {
                    break;
                }
            }
            else
            {
                break;//we've to break because we cannot go beside other piece!!
            }

        }

        for (int i = y - 1; i >= 0; --i)
        {//down

            if (this.pieceBehaviour.checkPiece(this.getSquare().getPozX(), i))
            {//if on this square isn't piece

            	Square newMove= chessboard.initial.squares[x][i];
                
                if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), newMove))
                {
                    list.add(newMove);
                }

                if (this.pieceBehaviour.otherOwner(x, i))
                {
                    break;
                }
            }
            else
            {
                break;//we've to break because we cannot go beside other piece!!
            }
        }

        for (int i = x - 1; i >= 0; --i)
        {//left

            if (this.pieceBehaviour.checkPiece(i, this.getSquare().getPozY()))
            {//if on this square isn't piece
            	Square newMove= chessboard.initial.squares[i][y];
                
                if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), newMove))
                {
                    list.add(newMove);
                }

                if (this.pieceBehaviour.otherOwner(i, y))
                {
                    break;
                }
            }
            else
            {
                break;//we've to break because we cannot go beside other piece!!
            }
        }

        for (int i = x + 1; i <= 7; ++i)
        {//right

            if (this.pieceBehaviour.checkPiece(i, this.getSquare().getPozY()))
            {//if on this square isn't piece

Square newMove= chessboard.initial.squares[i][y];
                
                if (myKing.willBeSafeWhenMoveOtherPiece(this.getSquare(), newMove))
                {
                    list.add(newMove);
                }

                if (this.pieceBehaviour.otherOwner(i, y))
                {
                    break;
                }
            }
            else
            {
                break;//we've to break because we cannot go beside other piece!!
            }
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
