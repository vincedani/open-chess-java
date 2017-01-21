package test.java.movesInCircleBoard;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.circleBoard.CircleBoardInitialization;
import main.java.game.Player;
import main.java.movesInCircleBoard.RookMovesInCircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class QueenMovesTest {

	private Player p1;
	private CircleBoard board;
	private int x;
	private int y;
	Piece queen;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");
		x = 3;
		y = 3;

		board = mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());

		queen = PieceFactory.createQueenInCircleBoard(board, p1);
		board.getSquares()[x][y].setPiece(queen);
	}
	

	// Rook Moves
	@Test
	public final void testGetMovesForwardBeforeCenter() {
/*
		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward
		for (int i = 1; i <= 2; i++) {
			Square expectedSq = board.getSquares()[x][y + i];
			expected.add(expectedSq);
		}

		RookMovesInCircleBoard pm = new RookMovesInCircleBoard();
		ArrayList<Square> obtained = pm.getMoves(queen, true);

		assertTrue(obtained.containsAll(expected));*/

	}
	/*
	@Test
	public final void testGetMovesBackwardsBeforeCenter() {
		ArrayList<Square> expected = new ArrayList<Square>();
		// Backwards
		for (int i = 1; i <= 3; i++) {
			Square expectedSq = board.getSquares()[x][y - i];
			expected.add(expectedSq);
		}

		RookMovesInCircleBoard pm = new RookMovesInCircleBoard();
		ArrayList<Square> obtained = pm.getMoves(queen, true);

		assertTrue(obtained.containsAll(expected));
	}

	@Test
	public final void testGetMovesClockWise() {
		ArrayList<Square> expected = new ArrayList<Square>();
		// Clockwise
		for (int i = 1; i <= 20; i++) {
			Square expectedSq = board.getSquares()[x + i][y];
			expected.add(expectedSq);
		}

		RookMovesInCircleBoard pm = new RookMovesInCircleBoard();
		ArrayList<Square> obtained = pm.getMoves(queen, true);
		
		assertTrue(obtained.containsAll(expected));

	}

	@Test
	public final void testGetMovesCounterClockwise() {
		ArrayList<Square> expected = new ArrayList<Square>();
		// Counterclockwise
		for (int i = 3; i > 0; i--) {
			Square expectedSq = board.getSquares()[x + i][y];
			expected.add(expectedSq);
		}

		RookMovesInCircleBoard pm = new RookMovesInCircleBoard();
		ArrayList<Square> obtained = pm.getMoves(queen, true);
		
		assertTrue(obtained.containsAll(expected));
	}

	// Bishop 
	*/
}

	