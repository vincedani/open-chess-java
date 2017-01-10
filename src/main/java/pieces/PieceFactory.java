package main.java.pieces;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.game.Player;
import main.java.movesInCircleBoard.BishopMovesInCircleBoard;
import main.java.movesInCircleBoard.KnightMovesInCircleBoard;
import main.java.movesInSquareBoard.BishopMovesInSquareBoard;
import main.java.movesInSquareBoard.KnightMovesInSquareBoard;

public class PieceFactory {

		
	public static Piece createBishopInCircleBoard(IChessboard chessboard, Player player) {

		Piece bishop = new Piece(chessboard, player, "Bishop"); 
		bishop.symbol = "B";
		ArrayList<IMove> bishopMoves = new ArrayList<>();
		bishopMoves.add(new BishopMovesInCircleBoard());
		
		bishop.moveBehaviour = bishopMoves;
		
		return bishop;

	}
	
	public static Piece createBishopInSquareBoard(IChessboard chessboard, Player player) {

		Piece bishop = new Piece(chessboard, player, "Bishop"); 
		bishop.symbol = "B";
		ArrayList<IMove> bishopMoves = new ArrayList<>();
		bishopMoves.add(new BishopMovesInSquareBoard());
		
		bishop.moveBehaviour = bishopMoves;
		
		return bishop;

	}
	
	public static Piece createKnightInCircleBoard(IChessboard chessboard, Player player) {
		Piece knight = new Piece(chessboard, player, "Knight"); // call initializer of super type:
												// Piece
		knight.symbol = "N";
		ArrayList<IMove> knightMoves = new ArrayList<>();
		knightMoves.add(new KnightMovesInCircleBoard());
		
		knight.moveBehaviour = knightMoves;
		
		return knight;
	}
	
	public static Piece createKnightInSquareBoard(IChessboard chessboard, Player player) {
		Piece knight = new Piece(chessboard, player, "Knight"); // call initializer of super type:
												// Piece
		knight.symbol = "N";
		ArrayList<IMove> knightMoves = new ArrayList<>();
		knightMoves.add(new KnightMovesInSquareBoard());
		
		knight.moveBehaviour = knightMoves;
		
		return knight;
	}

}
