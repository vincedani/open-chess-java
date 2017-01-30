package test.java.movesInSquareBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.movesInSquareBoard.KnightMovesInSquareBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;
import main.java.squareBoard.SquareBoard;

public class KnightMovesInSquareBoardTest {
	Settings settings; 
	@Before
	public void setUp() throws Exception {
		settings = mock(Settings.class);
	}

	// Moves for Knight
	@Test
	public final void testGetMovesKnight() {
		// Initialize Board
		SquareBoard board = new SquareBoard(settings);

		// Set Player and Piece
		int x = 3;
		int y = 3;
		Player p1 = new Player("Player1", "white");
		Piece knight = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.Knight);
		board.getSquares()[x][y].setPiece(knight);

		// Expected Moves
		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward
		expected.add(board.getSquares()[x - 2][y + 1]);
		expected.add(board.getSquares()[x - 1][y + 2]);
		expected.add(board.getSquares()[x + 1][y + 2]);
		expected.add(board.getSquares()[x + 2][y + 1]);
		// Backwards
		expected.add(board.getSquares()[x + 2][y - 1]);
		expected.add(board.getSquares()[x + 1][y - 2]);
		expected.add(board.getSquares()[x - 1][y - 2]);
		expected.add(board.getSquares()[x - 2][y - 1]);

		KnightMovesInSquareBoard km = new KnightMovesInSquareBoard();
		ArrayList<Square> obtained = km.getMoves(board, knight, true);

		assertEquals(obtained.size(), expected.size());
		assertTrue(obtained.containsAll(expected));
	}

	// Moves for Knight in border
	@Test
	public final void testGetMovesKnightInBorder() {
		// Initialize Board
		SquareBoard board = new SquareBoard(settings);

		// Set Player and Piece
		int x = 0;
		int y = 3;
		Player p1 = new Player("Player1", "white");
		Piece knight = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.Knight);
		board.getSquares()[x][y].setPiece(knight);

		// Expected Moves
		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward
		expected.add(board.getSquares()[1][5]);
		expected.add(board.getSquares()[2][4]);
		// Backwards
		expected.add(board.getSquares()[2][2]);
		expected.add(board.getSquares()[1][1]);

		// Obtained Moves by Algorithm
		KnightMovesInSquareBoard km = new KnightMovesInSquareBoard();
		ArrayList<Square> obtained = km.getMoves(board, knight, true);

		assertEquals(obtained.size(), expected.size());
		assertTrue(obtained.containsAll(expected));
	}

}