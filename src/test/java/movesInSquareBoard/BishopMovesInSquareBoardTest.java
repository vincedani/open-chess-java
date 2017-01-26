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
import main.java.movesInSquareBoard.BishopMovesInSquareBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.squareBoard.SquareBoard;
import main.java.squareBoard.SquareBoardInitialization;

public class BishopMovesInSquareBoardTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	SquareBoard board;
	Piece bishop;
	int x, y;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");
		x = 3;
		y = 3;
		
		board=  mock(SquareBoard.class);
		SquareBoardInitialization board_squares = new SquareBoardInitialization(true,board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
		
		bishop = PieceFactory.createBishopInSquareBoard(board, p1);
		board.getSquares()[x][y].setPiece(bishop);
	}

	// ForwardRightMoves
	@Test
	public final void testGetMoves_forwardRightMoves() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) {
			Square expectedSq = board.getSquares()[x + h][y + i];
			expected.add(expectedSq);
		}

		BishopMovesInSquareBoard bm = new BishopMovesInSquareBoard();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);
		
		assertTrue(obtained.containsAll(expected));

	}

	// BackwardLeftMoves
	@Test
	public final void testGetMoves_backwardleftMoves() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) {
			Square expectedSq = board.getSquares()[x - h][y - i];
			expected.add(expectedSq);
		}

		BishopMovesInSquareBoard bm = new BishopMovesInSquareBoard();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);
		
		assertTrue(obtained.containsAll(expected));
	}

	// ForwardLeftMoves
	@Test
	public final void testGetMoves_forwardleftMoves() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) {
			Square expectedSq = board.getSquares()[x - h][y + i];
			expected.add(expectedSq);
		}

		BishopMovesInSquareBoard bm = new BishopMovesInSquareBoard();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);
		
		assertTrue(obtained.containsAll(expected));
	}

	// BackwardRightMoves
	@Test
	public final void testGetMoves_backwardRightMoves() {
		ArrayList<Square> expected = new ArrayList<Square>();
		
		for (int h = 1, i = 1; h < 3 && i < 3; h++, i++) {
			Square expectedSq = board.getSquares()[x + h][y - i];
			expected.add(expectedSq);
		}

		BishopMovesInSquareBoard bm = new BishopMovesInSquareBoard();
		ArrayList<Square> obtained = bm.getMoves(bishop, true);
		
		assertTrue(obtained.containsAll(expected));

	}

}