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
import main.java.movesInCircleBoard.RookMovesInCircleBoard;
import main.java.pieces.Rook;

public class RookMovesTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	private CircleBoard board;
	private int x;
	private int y;
	private Rook rook;

	@Before
	public void setUp() throws Exception {
		/*p1 = new Player("Player1", "white");
		x = 3;
		y = 3;
		board = new CircleBoard(mock(Settings.class), mock(MovesTable.class));
		rook = new Rook(board, p1, moveBehaviour);
		board.getSquares()[x][y].setPiece(rook);*/
	}

	// Case 1: Before Center
	@Test
	public final void testGetMoves_1_forward() {

		/*ArrayList<Square> expected = new ArrayList<Square>();
		// Forward
		for (int i = 1; i <= 2; i++) {
			Square expectedSq = board.getSquares()[x][y + i];
			expected.add(expectedSq);
		}

		RookMoves pm = new RookMoves();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	@Test
	public final void testGetMoves_1_backwards() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		// Backwards
		for (int i = 1; i <= 3; i++) {
			Square expectedSq = board.getSquares()[x][y - i];
			expected.add(expectedSq);
		}

		RookMoves pm = new RookMoves();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));*/
	}

	@Test
	public final void testGetMoves_1_clockWise() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		// Clockwise
		for (int i = 1; i <= 20; i++) {
			Square expectedSq = board.getSquares()[x + i][y];
			expected.add(expectedSq);
		}

		RookMoves pm = new RookMoves();
		ArrayList<Square> obtained = pm.getMoves(rook, true);
		assertTrue(obtained.containsAll(expected));*/

	}

	@Test
	public final void testGetMoves_1_counterClockwise() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		// Counterclockwise
		for (int i = 3; i > 0; i--) {
			Square expectedSq = board.getSquares()[x + i][y];
			expected.add(expectedSq);
		}

		RookMoves pm = new RookMoves();
		ArrayList<Square> obtained = pm.getMoves(rook, true);
		assertTrue(obtained.containsAll(expected));*/
	}

	// Case 2: After Center
	@Test
	public final void testGetMoves_2_forward() {
		/*
		int x = 3;
		int y = 5;

		CircleBoard board = new CircleBoard(mock(Settings.class), mock(MovesTable.class));
		Rook rook = new Rook(board, p1, moveBehaviour);
		board.getSquares()[x][y].setPiece(rook);

		ArrayList<Square> expected = new ArrayList<Square>();

		// Before hitting the center // Forward
		for (int i = 1; i < 1; i++) {
			Square expectedSq = board.getSquares()[x][y + 1];
			expected.add(expectedSq);
		}

		RookMoves pm = new RookMoves();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	@Test
	public final void testGetMoves_2_backwards() {
		/*int x = 3;
		int y = 5;

		CircleBoard board = new CircleBoard(mock(Settings.class), mock(MovesTable.class));
		Rook rook = new Rook(board, p1, moveBehaviour);
		board.getSquares()[x][y].setPiece(rook);

		ArrayList<Square> expected = new ArrayList<Square>();

		// Backwards
		for (int i = 1; i <= 5; i++) {
			Square expectedSq = board.getSquares()[x][y - i];
			expected.add(expectedSq);
		}

		RookMoves pm = new RookMoves();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	@Test
	public final void testGetMoves_2_clockWise() {
		/*int x = 3;
		int y = 5;

		CircleBoard board = new CircleBoard(mock(Settings.class), mock(MovesTable.class));
		Rook rook = new Rook(board, p1, moveBehaviour);
		board.getSquares()[x][y].setPiece(rook);

		ArrayList<Square> expected = new ArrayList<Square>();

		// Clockwise
		for (int i = 1; i <= 20; i++) {
			Square expectedSq = board.getSquares()[x + i][y];
			expected.add(expectedSq);
		}

		RookMoves pm = new RookMoves();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));*/
	}

	@Test
	public final void testGetMoves_2_counterClockwise() {
		/*int x = 3;
		int y = 5;

		CircleBoard board = new CircleBoard(mock(Settings.class), mock(MovesTable.class));
		Rook rook = new Rook(board, p1, moveBehaviour);
		board.getSquares()[x][y].setPiece(rook);

		ArrayList<Square> expected = new ArrayList<Square>();

		// Counterclockwise
		for (int i = 3; i > 0; i--) {
			Square expectedSq = board.getSquares()[x + i][y];
			expected.add(expectedSq);
		}

		RookMoves pm = new RookMoves();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));*/

	}
}