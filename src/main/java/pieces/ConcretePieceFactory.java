package main.java.pieces;

import java.util.ArrayList;

import main.java.LogToFile;
import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.game.Player;

public class ConcretePieceFactory extends FactoryPieces {
	
	public ConcretePieceFactory()
	{
		
	}
	
	public Piece GetPieceForCircleBoard(String symbol,String pieceName,IChessboard chessboard, Player player)
	{
		PieceFactory factoryObj = new PieceFactory();
        return factoryObj.CreateSpecificPieceForCircleBoard(chessboard,player,symbol,pieceName);
                 
	}
	
	public Piece GetPieceForSquareBoard(String symbol,String pieceName,IChessboard chessboard, Player player)
	{
		PieceFactory factoryObj = new PieceFactory();
        return factoryObj.CreateSpecificPieceForSquareBoard(chessboard,player,symbol,pieceName);
                 
	}
}
