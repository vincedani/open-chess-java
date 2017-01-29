package test.java.boards;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;

public class CircleBoardInitializationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void setPiecesTest() {
				
		// Initialize Board
		CircleBoard board = new CircleBoard();
		
		// Initialize Players
		Player p1 = new Player("P1", "white");
		Player p2 = new Player("P2", "blue");
		Player p3 = new Player("P3", "black");
		
		// Set Players and Pieces
		Player[] players = {p1, p2, p3};
		board.getInitial().setPieces(players); 
				
		// Rooks
		assertEquals(PieceType.Rook, board.getSquares()[0][0].getPiece().getType());
		assertEquals(PieceType.Rook, board.getSquares()[7][0].getPiece().getType());
		// Knights
		assertEquals(PieceType.Knight, board.getSquares()[1][0].getPiece().getType());
		assertEquals(PieceType.Knight, board.getSquares()[6][0].getPiece().getType());
		// Bishops
		assertEquals(PieceType.Bishop, board.getSquares()[2][0].getPiece().getType());
		assertEquals(PieceType.Bishop, board.getSquares()[5][0].getPiece().getType());
		// Queen 
		assertEquals(PieceType.Queen, board.getSquares()[4][0].getPiece().getType());
		// King
		assertEquals(PieceType.King, board.getSquares()[3][0].getPiece().getType());
	}
	
	@Test
	public final void setKingTest() {
				
		// Initialize Board
		CircleBoard board = new CircleBoard();
		
		// Initialize Players
		Player p1 = new Player("P1", "white");
		Player p2 = new Player("P2", "blue");
		Player p3 = new Player("P3", "black");
		
		// Set Players and Pieces
		Player[] players = {p1, p2, p3};
		board.getInitial().setPieces(players); 
				
		// Create Kings
		Piece king1 = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.King);
		Piece king2 = PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.King);
		Piece king3 = PieceFactory.createSpecificPieceForCircleBoard(board, p3, PieceType.King);
		
		// Set Kings
		board.setKing(king1);
		board.setKing(king2);
		board.setKing(king3);

		assertEquals(board.getInitial().kingWhite, king1); 
		assertEquals(board.getInitial().kingBlue,  king2); 
		assertEquals(board.getInitial().kingBlack, king3); 

	}
}
