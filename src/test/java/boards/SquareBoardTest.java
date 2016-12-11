package test.java.boards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.game.MovesTable;
import main.java.game.Settings;
import main.java.squareBoard.SquareBoard;

import static org.mockito.Mockito.mock;

public class SquareBoardTest {
	Settings settings; 
	MovesTable movesTable;
	SquareBoard board;
	
	@Before
	public void setUp() throws Exception {
		settings = mock(Settings.class);
		movesTable = mock(MovesTable.class);
		board = new SquareBoard(settings, movesTable);
	}

	@Test
	public final void testGetSquare() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetActiveSquare() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetKing() {
		fail("Not yet implemented"); // TODO
	}


}
