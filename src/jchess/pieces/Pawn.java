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
 * Class to represent a pawn piece
 * Pawn can move only forvard and can beat only across
 * In first move pawn can move 2 sqares
 * pawn can be upgreade to rook, knight, bishop, Queen if it's in the
 * squers nearest the side where opponent is lockated
 * Firat move of pawn:
 *       |_|_|_|_|_|_|_|_|7
|_|_|_|_|_|_|_|_|6
|_|_|_|X|_|_|_|_|5
|_|_|_|X|_|_|_|_|4
|_|_|_|P|_|_|_|_|3
|_|_|_|_|_|_|_|_|2
|_|_|_|_|_|_|_|_|1
|_|_|_|_|_|_|_|_|0
0 1 2 3 4 5 6 7
 *
 * Move of a pawn:
 *       |_|_|_|_|_|_|_|_|7
|_|_|_|_|_|_|_|_|6
|_|_|_|_|_|_|_|_|5
|_|_|_|X|_|_|_|_|4
|_|_|_|P|_|_|_|_|3
|_|_|_|_|_|_|_|_|2
|_|_|_|_|_|_|_|_|1
|_|_|_|_|_|_|_|_|0
0 1 2 3 4 5 6 7
 * Beats with can take pawn:
 *       |_|_|_|_|_|_|_|_|7
|_|_|_|_|_|_|_|_|6
|_|_|_|_|_|_|_|_|5
|_|_|X|_|X|_|_|_|4
|_|_|_|P|_|_|_|_|3
|_|_|_|_|_|_|_|_|2
|_|_|_|_|_|_|_|_|1
|_|_|_|_|_|_|_|_|0
0 1 2 3 4 5 6 7
 */
public class Pawn extends Piece
{

    boolean down;
    protected static final Image imageWhite = GUI.loadImage("Pawn-W.png");
    protected static final Image imageBlack = GUI.loadImage("Pawn-B.png");
    public static short value = 1;

    public Pawn(Chessboard chessboard, Player player)
    {
        super(chessboard, player);
        //this.setImages("Pawn-W.png", "Pawn-B.png");
        this.symbol = "";
        this.setImage(imageBlack, imageWhite);
    }


    /**
     * Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new possition of piece
     */
    @Override
    public ArrayList<Square> allMoves()
    {
        //System.out.println(this.player.goDown);//4test
        ArrayList<Square> list = new ArrayList<Square>();
        Square sq;
        Square sq1;
        int first = this.getSquare().getPozY() - 1;//number where to move
        int second = this.getSquare().getPozY() - 2;//number where to move (only in first move)
        if (this.getPlayer().isGoDown())
        {//check if player "go" down or up
            first = this.getSquare().getPozY() + 1;//if yes, change value
            second = this.getSquare().getPozY() + 2;//if yes, change value
        }
        if (this.isout(first, first))
        {//out of bounds protection
            return list;//return empty list
        }
        sq = getChessboard().squares[this.getSquare().getPozX()][first];
        if (sq.piece == null)
        {//if next is free
            //list.add(sq);//add
            if (this.getPlayer().getColor() == Player.colors.white)
            {//white

                if (this.getChessboard().kingWhite.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX()][first]))
                {
                    list.add(getChessboard().squares[this.getSquare().getPozX()][first]);
                }
            }
            else
            {//or black

                if (this.getChessboard().kingBlack.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX()][first]))
                {
                    list.add(getChessboard().squares[this.getSquare().getPozX()][first]);
                }
            }

            if ((getPlayer().isGoDown() && this.getSquare().getPozY() == 1) || (!getPlayer().isGoDown() && this.getSquare().getPozY() == 6))
            {
                sq1 = getChessboard().squares[this.getSquare().getPozX()][second];
                if (sq1.piece == null)
                {
                    //list.add(sq1);//only in first move
                    if (this.getPlayer().getColor() == Player.colors.white)
                    {//white

                        if (this.getChessboard().kingWhite.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX()][second]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX()][second]);
                        }
                    }
                    else
                    {//or black

                        if (this.getChessboard().kingBlack.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX()][second]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX()][second]);
                        }
                    }
                }
            }
        }
        if (!this.isout(this.getSquare().getPozX() - 1, this.getSquare().getPozY())) //out of bounds protection
        {
            //capture
            sq = getChessboard().squares[this.getSquare().getPozX() - 1][first];
            if (sq.piece != null)
            {//check if can hit left
                if (this.getPlayer() != sq.piece.getPlayer() && !sq.piece.getName().equals("King"))
                {
                    //list.add(sq);
                    if (this.getPlayer().getColor() == Player.colors.white)
                    {//white

                        if (this.getChessboard().kingWhite.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX() - 1][first]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX() - 1][first]);
                        }
                    }
                    else
                    {//or black

                        if (this.getChessboard().kingBlack.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX() - 1][first]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX() - 1][first]);
                        }
                    }
                }
            }

            //En passant
            sq = getChessboard().squares[this.getSquare().getPozX() - 1][this.getSquare().getPozY()];
            if (sq.piece != null
                    && this.getChessboard().twoSquareMovedPawn != null
                    && sq == this.getChessboard().twoSquareMovedPawn.getSquare())
            {//check if can hit left
                if (this.getPlayer() != sq.piece.getPlayer() && !sq.piece.getName().equals("King"))
                {// unnecessary

                    //list.add(sq);
                    if (this.getPlayer().getColor() == Player.colors.white)
                    {//white

                        if (this.getChessboard().kingWhite.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX() - 1][first]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX() - 1][first]);
                        }
                    }
                    else
                    {//or black

                        if (this.getChessboard().kingBlack.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX() - 1][first]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX() - 1][first]);
                        }
                    }
                }
            }
        }
        if (!this.isout(this.getSquare().getPozX() + 1, this.getSquare().getPozY()))
        {//out of bounds protection

            //capture
            sq = getChessboard().squares[this.getSquare().getPozX() + 1][first];
            if (sq.piece != null)
            {//check if can hit right
                if (this.getPlayer() != sq.piece.getPlayer() && !sq.piece.getName().equals("King"))
                {
                    //list.add(sq);
                    if (this.getPlayer().getColor() == Player.colors.white)
                    { //white

                        if (this.getChessboard().kingWhite.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX() + 1][first]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX() + 1][first]);
                        }
                    }
                    else
                    {//or black

                        if (this.getChessboard().kingBlack.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX() + 1][first]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX() + 1][first]);
                        }
                    }
                }
            }

            //En passant
            sq = getChessboard().squares[this.getSquare().getPozX() + 1][this.getSquare().getPozY()];
            if (sq.piece != null
                    && this.getChessboard().twoSquareMovedPawn != null
                    && sq == this.getChessboard().twoSquareMovedPawn.getSquare())
            {//check if can hit left
                if (this.getPlayer() != sq.piece.getPlayer() && !sq.piece.getName().equals("King"))
                {// unnecessary

                    //list.add(sq);
                    if (this.getPlayer().getColor() == Player.colors.white)
                    {//white

                        if (this.getChessboard().kingWhite.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX() + 1][first]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX() + 1][first]);
                        }
                    }
                    else
                    {//or black

                        if (this.getChessboard().kingBlack.willBeSafeWhenMoveOtherPiece(this.getSquare(), getChessboard().squares[this.getSquare().getPozX() + 1][first]))
                        {
                            list.add(getChessboard().squares[this.getSquare().getPozX() + 1][first]);
                        }
                    }
                }
            }
        }

        return list;
    }

    void promote(Piece newPiece)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
