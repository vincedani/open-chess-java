package test.java.movesInCircleBoard;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.circleBoard.CircleBoardDisplay;
import main.java.circleBoard.CircleBoardInitialization;
import main.java.game.Game;
import main.java.game.MovesTable;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.movesInCircleBoard.PawnMoves;
import main.java.pieces.Pawn;
import main.java.pieces.Piece;
import main.java.pieces.PieceBehaviour;

public class PawnMovesTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");

	}

	// Case 1: Before Center
	@Test
	public final void testGetMoves_1() {
		/*int x = 3;
		int y = 3;

		CircleBoard board = new CircleBoard(mock(Settings.class), mock(MovesTable.class));
		Pawn pawn = new Pawn(board, p1, moveBehaviour);
		board.getSquares()[x][y].setPiece(pawn);
		PieceBehaviour pieceBehaviour = mock(PieceBehaviour.class);
		when(pieceBehaviour.isout(x, y+1)).thenReturn(false);

		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board.getSquares()[x][y+1];
		expected.add(expectedSq);

		PawnMoves pm = new PawnMoves();
		ArrayList<Square> obtained = pm.getMoves(pawn, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	// Case 2: After Center
	@Test
	public final void testGetMoves_2() {
		/*int x = 3;
		int y = 5;

		CircleBoard board = new CircleBoard(mock(Settings.class), mock(MovesTable.class));
		Pawn pawn = new Pawn(board, p1, moveBehaviour);
		board.getSquares()[x][y].setPiece(pawn);
		PieceBehaviour pieceBehaviour = mock(PieceBehaviour.class);
		when(pieceBehaviour.isout(x + 8, y)).thenReturn(false);

		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board.getSquares()[x + 8][y];
		expected.add(expectedSq);

		PawnMoves pm = new PawnMoves();
		ArrayList<Square> obtained = pm.getMoves(pawn, true);

		assertTrue(obtained.containsAll(expected));*/

	}

}
