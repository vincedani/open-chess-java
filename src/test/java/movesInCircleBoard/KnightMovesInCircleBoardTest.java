package test.java.movesInCircleBoard;

import static org.junit.Assert.assertTrue;
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
import main.java.movesInCircleBoard.KnightMovesInCircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class KnightMovesInCircleBoardTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	CircleBoard board;
	Piece knight;
	int x,y;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");
		x = 3;
		y = 3;

		board=  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
		
		knight = PieceFactory.createKnightInCircleBoard(board, p1);
		board.getSquares()[x][y].setPiece(knight);
		
	}

	//#1
	@Test
	public final void testGetMovesForwardMove_1() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-2][y + 1];
		expected.add(expectedSq);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertTrue(obtained.containsAll(expected));

	}

	//#2
	@Test
	public final void testGetMovesForwardMove_2() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-1][y + 2];
		expected.add(expectedSq);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertTrue(obtained.containsAll(expected));
	}

	//#3
	@Test
	public final void testGetMovesForwardMove_3() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+1][y + 2];
		expected.add(expectedSq);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertTrue(obtained.containsAll(expected));
	}
	
	//#4
	@Test
	public final void testGetMovesForwardMove_4() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+2][y + 1];
		expected.add(expectedSq);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertTrue(obtained.containsAll(expected));
	}
	
	//#5
	@Test
	public final void testGetMovesBackwardMove_1() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+2][y -1];
		expected.add(expectedSq);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertTrue(obtained.containsAll(expected));
	}
	
	//#6
	@Test
	public final void testGetMovesBackwardMove_2() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+1][y -2];
		expected.add(expectedSq);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertTrue(obtained.containsAll(expected));

	}
	
	//#7
	@Test
	public final void testGetMovesBackwardMove_3() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-1][y -2];
		expected.add(expectedSq);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertTrue(obtained.containsAll(expected));
	}

	//#8
	@Test
	public final void testGetMoves_LMove_8() {	
		ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-2][y -1];
		expected.add(expectedSq);

		KnightMovesInCircleBoard km = new KnightMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(knight, true);
		
		assertTrue(obtained.containsAll(expected));

	}
}