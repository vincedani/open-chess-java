package main.java.pieces;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.game.Player;
import main.java.movesInCircleBoard.BishopMovesInCircleBoard;
import main.java.movesInCircleBoard.DragonMovesInCircleBoard;
import main.java.movesInCircleBoard.KnightMovesInCircleBoard;
import main.java.movesInCircleBoard.PawnMovesInCircleBoard;
import main.java.movesInCircleBoard.RookMovesInCircleBoard;
import main.java.movesInSquareBoard.BishopMovesInSquareBoard;
import main.java.movesInSquareBoard.KnightMovesInSquareBoard;
import main.java.movesInSquareBoard.PawnMovesInSquareBoard;
import main.java.movesInSquareBoard.RookMovesInSquareBoard;

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
		Piece knight = new Piece(chessboard, player, "Knight"); // call
																// initializer
																// of super
																// type:
		// Piece
		knight.symbol = "N";
		ArrayList<IMove> knightMoves = new ArrayList<>();
		knightMoves.add(new KnightMovesInCircleBoard());

		knight.moveBehaviour = knightMoves;

		return knight;
	}

	public static Piece createKnightInSquareBoard(IChessboard chessboard, Player player) {
		Piece knight = new Piece(chessboard, player, "Knight"); // call
																// initializer
																// of super
																// type:
		// Piece
		knight.symbol = "N";
		ArrayList<IMove> knightMoves = new ArrayList<>();
		knightMoves.add(new KnightMovesInSquareBoard());

		knight.moveBehaviour = knightMoves;

		return knight;
	}

	public static Piece createQueenInCircleBoard(IChessboard chessboard, Player player) {
		Piece queen = new Piece(chessboard, player, "Queen"); // call
																// initializer
																// of super
																// type:
		// Piece
		queen.symbol = "Q";
		ArrayList<IMove> queenMoves = new ArrayList<>();
		queenMoves.add(new RookMovesInCircleBoard());
		queenMoves.add(new BishopMovesInCircleBoard());

		queen.moveBehaviour = queenMoves;

		return queen;
	}

	public static Piece createQueenInSquareBoard(IChessboard chessboard, Player player) {
		Piece queen = new Piece(chessboard, player, "Queen"); // call
																// initializer
																// of super
																// type:
		// Piece
		queen.symbol = "Q";
		ArrayList<IMove> queenMoves = new ArrayList<>();
		queenMoves.add(new RookMovesInSquareBoard());
		queenMoves.add(new BishopMovesInSquareBoard());

		queen.moveBehaviour = queenMoves;

		return queen;
	}

	public static Piece createPawnInCircleBoard(IChessboard chessboard, Player player) {
		Piece pawn = new Piece(chessboard, player, "Pawn");
		pawn.symbol = "";
		ArrayList<IMove> pawnMoves = new ArrayList<>();
		pawnMoves.add(new PawnMovesInCircleBoard());

		pawn.moveBehaviour = pawnMoves;

		return pawn;
	}

	public static Piece createPawnInSquareBoard(IChessboard chessboard, Player player) {
		Piece pawn = new Piece(chessboard, player, "Pawn");
		pawn.symbol = "";
		ArrayList<IMove> pawnMoves = new ArrayList<>();
		pawnMoves.add(new PawnMovesInSquareBoard());

		pawn.moveBehaviour = pawnMoves;

		return pawn;
	}

	public static Piece createRookInCircleBoard(IChessboard chessboard, Player player) {
		Piece rook = new Piece(chessboard, player, "Rook");
		rook.symbol = "";
		ArrayList<IMove> rookMoves = new ArrayList<>();
		rookMoves.add(new RookMovesInCircleBoard());

		rook.moveBehaviour = rookMoves;

		return rook;
	}

	public static Piece createRookInSquareBoard(IChessboard chessboard, Player player) {
		Piece rook = new Piece(chessboard, player, "Rook");
		rook.symbol = "";
		ArrayList<IMove> rookMoves = new ArrayList<>();
		rookMoves.add(new RookMovesInSquareBoard());

		rook.moveBehaviour = rookMoves;

		return rook;
	}

	public static Piece releaseTheDragon(IChessboard chessboard, Player player) {

		Piece dragon = new Piece(chessboard, player, "Dragon");
		dragon.symbol = "D";
		ArrayList<IMove> dragonMoves = new ArrayList<>();
		dragonMoves.add(new DragonMovesInCircleBoard());

		dragon.moveBehaviour = dragonMoves;

		return dragon;

	}
}
