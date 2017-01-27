package main.java.pieces;

import java.util.ArrayList;

import main.java.LogToFile;
import main.java.board.IChessboard;
import main.java.board.IMove;
import main.java.game.Player;
import main.java.movesInCircleBoard.BishopMovesInCircleBoard;
import main.java.movesInCircleBoard.DragonMovesInCircleBoard;
import main.java.movesInCircleBoard.KingInCircleBoard;
import main.java.movesInCircleBoard.KnightMovesInCircleBoard;
import main.java.movesInCircleBoard.PawnMovesInCircleBoard;
import main.java.movesInCircleBoard.QueenMovesInCircleBoard;
import main.java.movesInCircleBoard.RookMovesInCircleBoard;
import main.java.movesInSquareBoard.BishopMovesInSquareBoard;
import main.java.movesInSquareBoard.KingMovesInSquareBoard;
import main.java.movesInSquareBoard.KnightMovesInSquareBoard;
import main.java.movesInSquareBoard.PawnMovesInSquareBoard;
import main.java.movesInSquareBoard.RookMovesInSquareBoard;

public class PieceFactory {
	
	public enum PieceType {Pawn, Rook, Bishop, Knight, Queen, King, Dragon};
	

	public static Piece createSpecificPieceForCircleBoard(IChessboard chessboard, Player player, PieceType pieceName)
	{
		Piece piece = new Piece(chessboard, player, pieceName);
		IMove pieceMove = null;
		
		 switch (pieceName)
         {		   
             case Bishop:
            	 pieceMove = new BishopMovesInCircleBoard();   
            	 break;
             case Knight:
            	 pieceMove = new KnightMovesInCircleBoard(); 
            	 break;
             case Queen:
            	 pieceMove = new QueenMovesInCircleBoard(); 
             break;
             case Rook:
            	 pieceMove = new RookMovesInCircleBoard(); 
            	 break;
             case Pawn:
            	 pieceMove = new PawnMovesInCircleBoard(); 
            	 break;
             case King:
            	 pieceMove = new KingInCircleBoard(); 
            	 break;
             case Dragon:
            	 pieceMove = new DragonMovesInCircleBoard(); 
            	 break;
             default:
            	LogToFile.log(null,"Info", "Piece "+pieceName.toString()+" cannot be created" );
         }
    	
		 piece.setMoveBehaviour(pieceMove);  
		return piece;
	}
	
	public static Piece createSpecificPieceForSquareBoard(IChessboard chessboard, Player player,String symbol,PieceType pieceName)
	{
		Piece piece = new Piece(chessboard, player, pieceName);
		piece.symbol = symbol;
		ArrayList<IMove> pieceMoves = new ArrayList<>();
		
		 switch (pieceName)
         {		   
             case Bishop:
            	 pieceMoves.add(new BishopMovesInSquareBoard());    
            	 break;
             case Knight:
            	 pieceMoves.add(new KnightMovesInSquareBoard());
            	 break;
             case Queen:
             
            	 pieceMoves.add(new RookMovesInSquareBoard());
            	 pieceMoves.add(new BishopMovesInSquareBoard());
            	 break;
             case Rook:
            	 pieceMoves.add(new RookMovesInSquareBoard());
            	 break;
             case Pawn :
            	 pieceMoves.add(new PawnMovesInSquareBoard());
            	 break;
             case King:
            	 pieceMoves.add(new KingMovesInSquareBoard());
            	 break;
            default:
            	 LogToFile.log(null,"Info","Piece "+pieceName.toString()+" cannot be created");
         }
    	
		 //piece.moveBehaviour = pieceMoves;  
		return piece;
	}
	
}
