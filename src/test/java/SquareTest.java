package test.java;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import main.java.board.Square;
import main.java.pieces.Piece;

public class SquareTest
{
	Piece pawn; 
	Square cookie;
	
	@Before
	public void setUp() throws Exception {
		pawn = mock(Piece.class);
		cookie = new Square(1, 1, pawn);
	}

	@Test 
	public void testgetPozX() {
		int expected = 1; 
		int obtained = cookie.getPosX();
		
		assertEquals(expected, obtained);
	}
	
	@Test 
	public void testgetPozY() {
		int expected = 1; 
		int obtained = cookie.getPosY();
		
		assertEquals(expected, obtained);
		
	}
}