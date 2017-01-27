package main.java.pieces;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.game.Player;

public abstract class FactoryPieces {
	
	 public abstract Piece GetPieceForCircleBoard(String symbol,String pieceName,IChessboard chessboard, Player player);
	 
	 public abstract Piece GetPieceForSquareBoard(String symbol,String pieceName,IChessboard chessboard, Player player);

}
