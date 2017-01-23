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
import main.java.movesInCircleBoard.KingMovesInCircleBoard;
import main.java.pieces.King;
import main.java.pieces.Piece;

public class KingMovesTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	CircleBoard board;
	Piece king;
	int x = 0;
	int y = 0;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");

		board = mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());

		ArrayList<IMove> kingMoves = new ArrayList<>();
		kingMoves.add(new KingMovesInCircleBoard());
		king = new King(board, p1, kingMoves);
		board.getSquares()[x][y].setPiece(king);
	}

	// #1
	@Test
	public final void testBoundariesCircleBoard() {

		ArrayList<Square> expected = new ArrayList<Square>();

		Square expectedSq = board.getSquares()[1][0];
		expected.add(expectedSq);

		IMove km = new KingMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));

	}

	// #2
	@Test
	public final void testMovesInCircleBoard() {
		int x = 3;
		int y = 3;

		ArrayList<Square> expected = new ArrayList<Square>();
		board.getSquares()[x][y].setPiece(king);

		expected.add(board.getSquares()[x	 ][y + 1]);
		expected.add(board.getSquares()[x	 ][y - 1]);
		expected.add(board.getSquares()[x + 1][y + 1]);
		expected.add(board.getSquares()[x + 1][y - 1]);
		expected.add(board.getSquares()[x - 1][y + 1]);
		expected.add(board.getSquares()[x - 1][y - 1]);
		expected.add(board.getSquares()[x + 1][y	]);
		expected.add(board.getSquares()[x - 1][y	]);

		IMove km = new KingMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));
	}

}