package test.java.movesInSquareBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.movesInSquareBoard.BishopMovesInSquareBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;
import main.java.squareBoard.SquareBoard;

public class BishopMovesInSquareBoardTest {
	
	Settings settings; 
	@Before
	public void setUp() throws Exception {
		settings = mock(Settings.class);
	}

	// Moves for Bishop
	@Test
	public final void testGetMovesBishop() {
		// Initialize Board
		SquareBoard board =  new SquareBoard(settings);
				
		// Set Player and Piece
		int x = 3;
		int y = 3;
		Player p1 = new Player("Player1", "white");
		Piece bishop = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.Bishop);
		board.getSquares()[x][y].setPiece(bishop);
		
		// Expected Moves
		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward Right
		for (int h = 1, i = 1; h <= 4 && i <= 4; h++, i++) { expected.add(board.getSquares()[x + h][y + i]);}	
		// Backward Left
		for (int h = 1, i = 1; h <= 4 && i < 4; h++, i++) { expected.add(board.getSquares()[x - h][y - i]);}
		// Forward Left
		for (int h = 1, i = 1; h <= 3 && i <= 3; h++, i++) { expected.add(board.getSquares()[x - h][y + i]);}
		// Backward Right
		for (int h = 1, i = 1; h <= 4 && i < 4; h++, i++) { expected.add(board.getSquares()[x + h][y - i]);}
		
		// Obtained Moves by Algorithm
		BishopMovesInSquareBoard bm = new BishopMovesInSquareBoard();
		ArrayList<Square> obtained = bm.getMoves(board,bishop, true);
		
		/*
		for (int i = 0; i < expected.size(); i++){
			System.out.println(expected.get(i).getPosX() + ", " + expected.get(i).getPosY());
		}
		System.out.println("Obtained");
		for (int i = 0; i < obtained.size(); i++){
			System.out.println(obtained.get(i).getPosX() + ", " + obtained.get(i).getPosY());
		}
		*/
		
		assertEquals(expected.size(),obtained.size());
		assertTrue(obtained.containsAll(expected));
	}
}