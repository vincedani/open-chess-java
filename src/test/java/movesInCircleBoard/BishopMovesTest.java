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
import main.java.movesInCircleBoard.BishopMoves;
import main.java.pieces.Bishop;

public class BishopMovesTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	private Bishop bishop;
	CircleBoard board;
	int x, y;

	@Before
	public void setUp() throws Exception {
		/*p1 = new Player("Player1", "white");
		x = 3;
		y = 3;
		board = new CircleBoard(mock(Settings.class), mock(MovesTable.class));
		bishop = new Bishop(board, p1, moveBehaviour);
		board.getSquares()[x][y].setPiece(bishop);*/
	}

	// ForwardRightMoves
	@Test
	public final void testGetMoves_forwardRightMoves() {

		/*ArrayList<Square> expected = new ArrayList<Square>();
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) {
			Square expectedSq = board.getSquares()[x + h][y + i];
			expected.add(expectedSq);
		}

		BishopMoves bm = new BishopMoves();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	// BackwardLeftMoves
	@Test
	public final void testGetMoves_backwardleftMoves() {

		/*ArrayList<Square> expected = new ArrayList<Square>();
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) {
			Square expectedSq = board.getSquares()[x - h][y - i];
			expected.add(expectedSq);
		}

		BishopMoves bm = new BishopMoves();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	// ForwardLeftMoves
	@Test
	public final void testGetMoves_forwardleftMoves() {
		/*
		ArrayList<Square> expected = new ArrayList<Square>();
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) {
			Square expectedSq = board.getSquares()[x - h][y + i];
			expected.add(expectedSq);
		}

		BishopMoves bm = new BishopMoves();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	// BackwardRightMoves
	@Test
	public final void testGetMoves_backwardRightMoves() {

		/*ArrayList<Square> expected = new ArrayList<Square>();
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) {
			Square expectedSq = board.getSquares()[x + h][y - i];
			expected.add(expectedSq);
		}

		BishopMoves bm = new BishopMoves();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);

		assertTrue(obtained.containsAll(expected));*/
	}

}