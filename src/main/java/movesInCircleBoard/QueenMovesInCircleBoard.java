package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class QueenMovesInCircleBoard implements IMove{

	@Override
	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		// TODO Auto-generated method stub
		ArrayList<IMove> pieceMoves = new ArrayList<>();
		pieceMoves.add(new RookMovesInCircleBoard());
   	 pieceMoves.add(new BishopMovesInCircleBoard());
   	ArrayList<Square> list = new ArrayList<>();
   	 for ( IMove iMove : pieceMoves) {
		list.addAll(iMove.getMoves(piece, ignoreKing));
	}
		return list;
	}

}
