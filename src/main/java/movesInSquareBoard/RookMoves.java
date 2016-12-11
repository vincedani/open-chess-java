package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class RookMoves implements IMove {	
	
	private static void forwardVerticalMoves(Piece piece, ArrayList<Square> list) {
		int x= piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		for (int i = y + 1; i <= 7; i++) {// up

			if (piece.pieceBehaviour.checkPiece(x, i)) {
				Square newMove = piece.getChessboard().getSquares()[x][i];

				if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove)) {
					list.add(newMove);
				}

				if (piece.pieceBehaviour.otherOwner(x, i)) {
					break;
				}
			} else {
				break;// we've to break because we cannot go beside other
						// piece!!
			}

		}
	}
	
	private static void backwardVerticalMoves(Piece piece, ArrayList<Square> list){
		int x= piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		for (int i = y - 1; i >= 0; i--)
        {//down

            if (piece.pieceBehaviour.checkPiece(x, i))
            {//if on this square isn't piece

            	Square newMove= piece.getChessboard().getSquares()[x][i];
                
                if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove))
                {
                    list.add(newMove);
                }

                if (piece.pieceBehaviour.otherOwner(x, i))
                {
                    break;
                }
            }
            else
            {
                break;//we've to break because we cannot go beside other piece!!
            }
        }
	}
	
	private static void rightHorizontalMoves(Piece piece, ArrayList<Square> list){
		int x= piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		for (int i = x + 1; i <= 7; ++i)
        {//right

            if (piece.pieceBehaviour.checkPiece(i, y))
            {//if on this square isn't piece


				Square newMove = piece.getChessboard().getSquares()[i][y];
                
                if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove))
                {
                    list.add(newMove);
                }

                if (piece.pieceBehaviour.otherOwner(i, y))
                {
                    break;
                }
            }
            else
            {
                break;//we've to break because we cannot go beside other piece!!
            }
        }
	}
	
	private static void leftHorizontalMoves(Piece piece, ArrayList<Square> list){
		int x= piece.getSquare().getPozX(), y = piece.getSquare().getPozY();
		for (int i = x - 1; i >= 0; --i)
        {//left

            if (piece.pieceBehaviour.checkPiece(i, piece.getSquare().getPozY()))
            {//if on this square isn't piece
            	Square newMove= piece.getChessboard().getSquares()[i][y];
                
                if (piece.myKing().willBeSafeAfterMove(piece.getSquare(), newMove))
                {
                    list.add(newMove);
                }

                if (piece.pieceBehaviour.otherOwner(i, y))
                {
                    break;
                }
            }
            else
            {
                break;//we've to break because we cannot go beside other piece!!
            }
        }
	}
	
	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing){
		ArrayList<Square> list = new ArrayList<>();
		forwardVerticalMoves(piece, list);
		backwardVerticalMoves(piece, list);
		leftHorizontalMoves(piece, list);
		rightHorizontalMoves(piece, list);
		
		return list;
	}
}
