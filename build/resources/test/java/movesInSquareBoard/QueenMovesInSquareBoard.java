package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;

public class QueenMovesInSquareBoard implements IMove {

	@Override
	public ArrayList<Square> getMoves(IChessboard board, Piece piece, boolean ignoreKing) {
		ArrayList<IMove> pieceMoves = new ArrayList<>();
		pieceMoves.add(new RookMovesInSquareBoard());
		pieceMoves.add(new BishopMovesInSquareBoard());
		ArrayList<Square> list = new ArrayList<>();
		for (IMove iMove : pieceMoves) {
			list.addAll(iMove.getMoves(board, piece, ignoreKing));
		}
		return list;
	}

}
