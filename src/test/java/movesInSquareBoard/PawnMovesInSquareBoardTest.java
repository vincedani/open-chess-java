package test.java.movesInSquareBoard;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.movesInSquareBoard.PawnMovesInSquareBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;
import main.java.squareBoard.SquareBoard;

public class PawnMovesInSquareBoardTest {
	private Player p1;
	Settings settings;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");
		settings = mock(Settings.class);
	}

	@Test
	public final void testGetMovesBeforeCenter() {
		// Initialize Board
		int x = 3;
		int y = 3;
		SquareBoard board = new SquareBoard(settings);
		
		// Set Piece
		Piece pawn = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.Pawn);
		board.getSquares()[x][y].setPiece(pawn);

		// Expected Moves
		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board.getSquares()[x][y - 1];
		expected.add(expectedSq);

		// Obtained by Algorithm
		PawnMovesInSquareBoard pm = new PawnMovesInSquareBoard();
		ArrayList<Square> obtained = pm.getMoves(board, pawn, true);
		
		/*
		System.out.println("Expected: " + expected.size() + " " + expected);
		for (int i = 0; i < expected.size(); i++){
			System.out.println(expected.get(i).getPosX() + ", " + expected.get(i).getPosY());
		}
		System.out.println("Obtained: " + obtained.size() + " " + obtained);
		for (int i = 0; i < obtained.size(); i++){
			System.out.println(obtained.get(i).getPosX() + ", " + obtained.get(i).getPosY());
		}
		*/
		
		assertTrue(obtained.containsAll(expected));
	}
}
