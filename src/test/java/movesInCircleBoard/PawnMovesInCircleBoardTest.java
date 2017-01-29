package test.java.movesInCircleBoard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.movesInCircleBoard.PawnMovesInCircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;

public class PawnMovesInCircleBoardTest {
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
		CircleBoard board = new CircleBoard();
		
		Piece pawn = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.Pawn);
		board.getSquares()[x][y].setPiece(pawn);

		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board.getSquares()[x][y + 1];
		expected.add(expectedSq);

		PawnMovesInCircleBoard pm = new PawnMovesInCircleBoard();
		ArrayList<Square> obtained = pm.getMoves(board, pawn, true);
		
		assertTrue(obtained.containsAll(expected));
	}

	// Case 2: Circle Center
	@Test
	public final void testGetMovesOnCircleCenter() {
		int x = 3;
		int y = 5;
		
		CircleBoard board = new CircleBoard();
		
		Piece pawn = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.Pawn);
		board.getSquares()[x][y].setPiece(pawn);

		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board.getSquares()[x + 8][y];
		expected.add(expectedSq);

		PawnMovesInCircleBoard pm = new PawnMovesInCircleBoard();
		pm.passCenter();
		ArrayList<Square> obtained = pm.getMoves(board, pawn, true);

		assertTrue(obtained.containsAll(expected));

	}

	// Case 3: After Center
	@Test
	public final void testGetMovesAfterCenter() {
		int x = 3;
		int y = 3;
		
		CircleBoard board = new CircleBoard();
		
		Piece pawn = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.Pawn);
		board.getSquares()[x][y].setPiece(pawn);

		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board.getSquares()[x][y - 1];
		expected.add(expectedSq);

		PawnMovesInCircleBoard pm = new PawnMovesInCircleBoard();
		pm.passCenter();
		ArrayList<Square> obtained = pm.getMoves(board, pawn, true);
		
		assertTrue(obtained.containsAll(expected));

	}
}
