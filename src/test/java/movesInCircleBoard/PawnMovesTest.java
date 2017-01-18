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
import main.java.movesInCircleBoard.PawnMovesInCircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class PawnMovesTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");

	}

	// Case 1: Before Center
	@Test
	public final void testGetMovesBeforeCenter() {
		int x = 3;
		int y = 3;
		CircleBoard board=  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
		
		Piece pawn = PieceFactory.createPawnInCircleBoard(board, p1);
		//Pawn pawn = new Pawn(board, p1, moveBehaviour);
		board_squares.getSquares()[x][y].setPiece(pawn);
		board.initial = board_squares;
		//PieceBehaviour pieceBehaviour = mock(PieceBehaviour.class);
		//when(pieceBehaviour.isout(x, y+1)).thenReturn(false);

		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board_squares.getSquares()[x][y+1];
		expected.add(expectedSq);

		PawnMovesInCircleBoard pm = new PawnMovesInCircleBoard();
		ArrayList<Square> obtained = pm.getMoves(pawn, true);

		assertTrue(obtained.containsAll(expected));

	}

	// Case 2: Circle Center
	@Test
	public final void testGetMovesOnCircleCenter() {
		int x = 3;
		int y = 5;

		CircleBoard board=  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
		
		Piece pawn = PieceFactory.createPawnInCircleBoard(board, p1);
		board.getSquares()[x][y].setPiece(pawn);
		//PieceBehaviour pieceBehaviour = mock(PieceBehaviour.class);
		//when(pieceBehaviour.isout(x + 8, y)).thenReturn(false);

		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board.getSquares()[x + 8][y];
		expected.add(expectedSq);

		PawnMovesInCircleBoard pm = new PawnMovesInCircleBoard();
		pm.passCenter();
		ArrayList<Square> obtained = pm.getMoves(pawn, true);

		assertTrue(obtained.containsAll(expected));

	}

	// Case 3: After Center
	@Test
	public final void testGetMovesAfterCenter() {
		int x = 3;
		int y = 3;
		CircleBoard board=  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
		
		Piece pawn = PieceFactory.createPawnInCircleBoard(board, p1);
		//Pawn pawn = new Pawn(board, p1, moveBehaviour);
		board_squares.getSquares()[x][y].setPiece(pawn);
		board.initial = board_squares;
		//PieceBehaviour pieceBehaviour = mock(PieceBehaviour.class);
		//when(pieceBehaviour.isout(x, y+1)).thenReturn(false);

		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board_squares.getSquares()[x][y-1];
		expected.add(expectedSq);

		PawnMovesInCircleBoard pm = new PawnMovesInCircleBoard();
		pm.passCenter();
		ArrayList<Square> obtained = pm.getMoves(pawn, true);

		assertTrue(obtained.containsAll(expected));

	}
}
