package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.pieces.PieceBehaviour;
import main.java.squareBoard.SquareBoard;

public class PieceBehaviourTest {

	private Player p1;
	private CircleBoard board;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");
		board = mock(CircleBoard.class);
	}

	@Test 
	public final void testIsout_0() {
		// Branch 0: The piece is OUT, CircleBoard
		PieceBehaviour pieceBehaviour = new PieceBehaviour(board, p1);
		int x = 24; 
		int y = 5; 
		boolean expected = true; // The piece is OUT
		boolean obtained = pieceBehaviour.isout(x, y);
		assertEquals(expected, obtained);
		
	}

	@Test
	public final void testIsout_1() {
		// Branch 1: The piece is IN, CircleBoard
		PieceBehaviour pieceBehaviour = new PieceBehaviour(board, p1);
		int x = 23; 
		int y = 2; 
		boolean expected = false; // The piece is NOT out
		boolean obtained = pieceBehaviour.isout(x, y);
		assertEquals(expected, obtained);
		
	}
	
	@Test
	public final void testIsout_2() {
		// Branch 2: The piece is OUT, SquareBoard
		SquareBoard boardSquare = mock(SquareBoard.class);

		PieceBehaviour pieceBehaviour = new PieceBehaviour(boardSquare, p1);
		int x = 8; 
		int y = 0; 
		boolean expected = true; // The piece is OUT
		boolean obtained = pieceBehaviour.isout(x, y);
		assertEquals(expected, obtained);
		
	}
	
	@Test
	public final void testIsout_3() {
		// Branch 3: The piece is IN, SquareBoard
		SquareBoard boardSquare = mock(SquareBoard.class);
		PieceBehaviour pieceBehaviour = new PieceBehaviour(boardSquare, p1);
		int x = 6; 
		int y = 3; 
		boolean expected = false; // The piece is NOT out
		boolean obtained = pieceBehaviour.isout(x, y);
		assertEquals(expected, obtained);
		
	}
	
	@Test
	public final void testGetPlayer() {
		PieceBehaviour pieceBehaviour = new PieceBehaviour(board, p1);
		Player expected = p1;
		Player obtained = pieceBehaviour.getPlayer();
		assertEquals(expected, obtained);
	}

	@Test
	public final void testSetPlayer() {
		PieceBehaviour pieceBehaviour = new PieceBehaviour(board, p1);
		assertNotNull(pieceBehaviour.getPlayer());
	}

	
}
