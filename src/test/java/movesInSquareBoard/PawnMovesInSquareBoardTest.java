package test.java.movesInSquareBoard;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.movesInSquareBoard.PawnMovesInSquareBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.squareBoard.SquareBoard;
import main.java.squareBoard.SquareBoardInitialization;

public class PawnMovesInSquareBoardTest {
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
		Settings settings = mock(Settings.class);
		SquareBoard board=  new SquareBoard(settings);
		SquareBoardInitialization board_squares = new SquareBoardInitialization(true, board);
		
		Piece pawn = PieceFactory.createPawnInSquareBoard(board, p1);

		board_squares.getSquares()[x][y].setPiece(pawn);
		board.initial = board_squares;

		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board_squares.getSquares()[x][y-1];
		expected.add(expectedSq);

		PawnMovesInSquareBoard pm = new PawnMovesInSquareBoard();
		ArrayList<Square> obtained = pm.getMoves(pawn, true);
		/*
		for (int i = 0; i<obtained.size();i++){
			System.out.println(obtained.get(i).getPozX() + ", " + obtained.get(i).getPozY());
		}
		System.out.println("Esperado");
		for (int i = 0; i<expected.size();i++){
			System.out.println(expected.get(i).getPozX() + ", " + expected.get(i).getPozY());
		}
		*/
		assertTrue(obtained.containsAll(expected));
	}


}
