package test.java.movesInSquareBoard;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.movesInSquareBoard.RookMovesInSquareBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.squareBoard.SquareBoard;
import main.java.squareBoard.SquareBoardInitialization;

public class RookMovesInSquareBoardTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	private SquareBoard board;;
	private int x;
	private int y;
	Piece rook;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");
		x = 3;
		y = 3;
		board = mock(SquareBoard.class);
		SquareBoardInitialization board_squares = new SquareBoardInitialization(true, board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());

		rook = PieceFactory.createRookInSquareBoard(board, p1);
		board.getSquares()[x][y].setPiece(rook);
	}

	// Case 1: Forward Before Center
	@Test
	public final void testGetMovesForwardBeforeCenter() {

		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward
		for (int i = 1; i <= 2; i++) {
			Square expectedSq = board.getSquares()[x][y + i];
			expected.add(expectedSq);
		}

		RookMovesInSquareBoard pm = new RookMovesInSquareBoard();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));

	}

	@Test
	public final void testGetMovesBackwardsBeforeCenter() {
		ArrayList<Square> expected = new ArrayList<Square>();
		// Backwards
		for (int i = 1; i <= 3; i++) {
			Square expectedSq = board.getSquares()[x][y - i];
			expected.add(expectedSq);
		}

		RookMovesInSquareBoard pm = new RookMovesInSquareBoard();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));
	}

	@Test
	public final void testGetMovesRight() {
		ArrayList<Square> expected = new ArrayList<Square>();
		// Clockwise
		for (int i = 1; i <= 4; i++) {
			Square expectedSq = board.getSquares()[x + i][y];
			expected.add(expectedSq);
		}

		RookMovesInSquareBoard pm = new RookMovesInSquareBoard();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));

	}

	@Test
	public final void testGetMovesLeft() {
		ArrayList<Square> expected = new ArrayList<Square>();
		// Counterclockwise
		for (int i = 3; i > 0; i--) {
			Square expectedSq = board.getSquares()[x + i][y];
			expected.add(expectedSq);
		}

		RookMovesInSquareBoard pm = new RookMovesInSquareBoard();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		assertTrue(obtained.containsAll(expected));
	}

}