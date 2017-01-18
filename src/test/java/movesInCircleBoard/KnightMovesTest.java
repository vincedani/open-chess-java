package test.java.movesInCircleBoard;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.circleBoard.CircleBoard;
import main.java.circleBoard.CircleBoardInitialization;
import main.java.game.Player;

public class KnightMovesTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	CircleBoard board;
	int x,y;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");
		x = 3;
		y = 3;
		

		board=  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
		
	}

	//#1
	@Test
	public final void testGetMoves_LMove_1() {

		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-2][y + 1];
		expected.add(expectedSq);

		KnightMoves km = new KnightMoves();
		ArrayList<Square> obtained = km.getMoves(knight, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	//#2
	@Test
	public final void testGetMoves_LMove_2() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-1][y + 2];
		expected.add(expectedSq);

		KnightMoves km = new KnightMoves();
		ArrayList<Square> obtained = km.getMoves(knight, true);

		assertTrue(obtained.containsAll(expected));*/
	}

	//#3
	@Test
	public final void testGetMoves_LMove_3() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+1][y + 2];
		expected.add(expectedSq);

		KnightMoves km = new KnightMoves();
		ArrayList<Square> obtained = km.getMoves(knight, true);

		assertTrue(obtained.containsAll(expected));*/

	}
	
	//#4
	@Test
	public final void testGetMoves_LMove_4() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+2][y + 1];
		expected.add(expectedSq);

		KnightMoves km = new KnightMoves();
		ArrayList<Square> obtained = km.getMoves(knight, true);

		assertTrue(obtained.containsAll(expected));*/
	}
	
	//#5
	@Test
	public final void testGetMoves_LMove_5() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+2][y -1];
		expected.add(expectedSq);

		KnightMoves km = new KnightMoves();
		ArrayList<Square> obtained = km.getMoves(knight, true);

		assertTrue(obtained.containsAll(expected));*/

	}
	
	//#6
	@Test
	public final void testGetMoves_LMove_6() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+1][y -2];
		expected.add(expectedSq);

		KnightMoves km = new KnightMoves();
		ArrayList<Square> obtained = km.getMoves(knight, true);

		assertTrue(obtained.containsAll(expected));*/

	}
	
	//#7
	@Test
	public final void testGetMoves_LMove_7() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-1][y -2];
		expected.add(expectedSq);

		KnightMoves km = new KnightMoves();
		ArrayList<Square> obtained = km.getMoves(knight, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	//#8
	@Test
	public final void testGetMoves_LMove_8() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-2][y -1];
		expected.add(expectedSq);

		KnightMoves km = new KnightMoves();
		ArrayList<Square> obtained = km.getMoves(knight, true);

		assertTrue(obtained.containsAll(expected));*/

	}
}