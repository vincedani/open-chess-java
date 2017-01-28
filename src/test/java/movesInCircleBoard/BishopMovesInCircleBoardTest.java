package test.java.movesInCircleBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.circleBoard.CircleBoardInitialization;
import main.java.game.Player;
import main.java.movesInCircleBoard.BishopMovesInCircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class BishopMovesInCircleBoardTest {
	
	@Before
	public void setUp() throws Exception {
	}

	// Moves for Bishop
	@Test
	public final void testGetMovesBishop() {
		// Initialize Board
		CircleBoard board=  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
				
		// Set Player and Piece
		int x = 3;
		int y = 3;
		Player p1 = new Player("Player1", "white");
		Piece bishop = PieceFactory.createBishopInCircleBoard(board, p1);
		board.getSquares()[x][y].setPiece(bishop);
		
		// Expected Moves
		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward Right
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) { expected.add(board.getSquares()[x + h][y + i]);}	
		// Backward Left
		for (int h = 1, i = 1; h < 4 && i < 4; h++, i++) { expected.add(board.getSquares()[x - h][y - i]);}
		// Forward Left
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) { expected.add(board.getSquares()[x - h][y + i]);}
		// Backward Right
		for (int h = 1, i = 1; h < 4 && i < 4; h++, i++) { expected.add(board.getSquares()[x + h][y - i]);}
		
		// Obtained Moves by Algorithm
		BishopMovesInCircleBoard bm = new BishopMovesInCircleBoard();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);
		
		assertEquals(obtained.size(), expected.size());
		assertTrue(obtained.containsAll(expected));
	}
	
	// Moves for Bishop in Border
	@Test
	public final void testGetMovesBishopInBorder() {
		// Initialize Board
		CircleBoard board=  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
		
		// Set Player and Piece
		int x = 23;
		int y = 3;
		Player p1 = new Player("Player1", "white");
		Piece bishop = PieceFactory.createBishopInCircleBoard(board, p1);
		board.getSquares()[x][y].setPiece(bishop);
	
		// Expected Moves
		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward Right
		expected.add(board.getSquares()[0][4]);
		expected.add(board.getSquares()[1][5]);
		// Backwards Right
		expected.add(board.getSquares()[0][2]);
		expected.add(board.getSquares()[1][1]);
		expected.add(board.getSquares()[2][0]);
		// Forward Left 
		expected.add(board.getSquares()[22][4]);
		expected.add(board.getSquares()[21][5]);
		// Backward Left
		expected.add(board.getSquares()[22][2]);
		expected.add(board.getSquares()[21][1]);
		expected.add(board.getSquares()[20][0]);
		
		// Obtained Moves By Algorithm
		BishopMovesInCircleBoard bm = new BishopMovesInCircleBoard();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);
				
		assertEquals(obtained.size(), expected.size());
		assertTrue(obtained.containsAll(expected));
	}
	

}