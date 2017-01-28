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
import main.java.movesInCircleBoard.KnightMovesInCircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class KnightMovesInCircleBoardTest {
	
	@Before
	public void setUp() throws Exception {
	}

	// Moves for Knight
	@Test
	public final void testGetMovesKnight() {
		// Initialize Board
		CircleBoard board =  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
						
		// Set Player and Piece
		int x = 3;
		int y = 3;
		Player p1 = new Player("Player1", "white");
		Piece knight = PieceFactory.createKnightInCircleBoard(board, p1);
		board.getSquares()[x][y].setPiece(knight);
		
		// Expected Moves
		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward 
		expected.add(board.getSquares()[x - 2][y + 1]);
		expected.add(board.getSquares()[x - 1][y + 2]);
		expected.add(board.getSquares()[x + 1][y + 2]);
		expected.add(board.getSquares()[x + 2][y + 1]);
		// Backwards
		expected.add(board.getSquares()[x + 2][y - 1]);
		expected.add(board.getSquares()[x + 1][y - 2]);
		expected.add(board.getSquares()[x - 1][y - 2]);
		expected.add(board.getSquares()[x - 2][y - 1]);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertEquals(obtained.size(), expected.size());
		assertTrue(obtained.containsAll(expected));
	}
	
	// Moves for Knight in border
	@Test
	public final void testGetMovesKnightInBorder() {
		// Initialize Board
		CircleBoard board =  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
						
		// Set Player and Piece
		int x = 0;
		int y = 3;
		Player p1 = new Player("Player1", "white");
		Piece knight = PieceFactory.createKnightInCircleBoard(board, p1);
		board.getSquares()[x][y].setPiece(knight);
		
		// Expected Moves
		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward 
		expected.add(board.getSquares()[22][4]);
		expected.add(board.getSquares()[23][5]);
		expected.add(board.getSquares()[1 ][5]);
		expected.add(board.getSquares()[2 ][4]);
		// Backwards
		expected.add(board.getSquares()[2 ][2]);
		expected.add(board.getSquares()[1 ][1]);
		expected.add(board.getSquares()[23][1]);
		expected.add(board.getSquares()[22][2]);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertEquals(obtained.size(), expected.size());
		assertTrue(obtained.containsAll(expected));
	}

}