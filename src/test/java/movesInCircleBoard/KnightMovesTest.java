package test.java.movesInCircleBoard;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.MovesTable;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.movesInCircleBoard.KnightMoves;
import main.java.movesInCircleBoard.RookMoves;
import main.java.pieces.Knight;
import main.java.pieces.Rook;

public class KnightMovesTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	private Knight knight;
	CircleBoard board;
	int x,y;

	@Before
	public void setUp() throws Exception {
		/*p1 = new Player("Player1", "white");
		x = 3;
		y = 3;
		board = new CircleBoard(mock(Settings.class), mock(MovesTable.class));
		knight = new Knight(board, p1, moveBehaviour);
		board.getSquares()[x][y].setPiece(knight);*/
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