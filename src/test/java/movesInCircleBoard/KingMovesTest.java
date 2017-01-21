package test.java.movesInCircleBoard;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.circleBoard.CircleBoard;
import main.java.circleBoard.CircleBoardInitialization;
import main.java.game.Player;
import main.java.pieces.Piece;


public class KingMovesTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	CircleBoard board;
	int x,y;
	Piece king;
	
	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");
		x = 3;
		y = 3;

		board=  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
		
		//king = PieceFactory.createKingInCircleBoard(board, p1);
		//board.getSquares()[x][y].setPiece(king);
	}
	
/* 	 Possible Moves for King
	 * |1|2|3| 
	 * |8| |4| 
	 * |7|6|5|
*/
	
	//#1
	@Test
	public final void testGetMoves_1() {

		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-1][y + 1];
		expected.add(expectedSq);

		KingMoves km = new KingMoves();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	//#2
	@Test
	public final void testGetMovese_2() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x][y + 1];
		expected.add(expectedSq);

		KingMoves km = new KingMoves();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));*/
	}

	//#3
	@Test
	public final void testGetMoves_3() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+1][y + 1];
		expected.add(expectedSq);

		KingMoves km = new KingMoves();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));*/
	}
	
	//#4
	@Test
	public final void testGetMoves_4() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+1][y];
		expected.add(expectedSq);

		KingMoves km = new KingMoves();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));*/
	}
	
	//#5
	@Test
	public final void testGetMoves_5() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x+1][y- 1];
		expected.add(expectedSq);

		KingMoves km = new KingMoves();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));*/

	}
	
	//#6
	@Test
	public final void testGetMoves_6() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x][y - 1];
		expected.add(expectedSq);

		KingMoves km = new KingMoves();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));*/

	}
	
	//#7
	@Test
	public final void testGetMoves_7() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-1][y - 1];
		expected.add(expectedSq);

		KingMoves km = new KingMoves();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));*/

	}

	//#8
	@Test
	public final void testGetMoves_8() {
		/*ArrayList<Square> expected = new ArrayList<Square>();
		
		Square expectedSq = board.getSquares()[x-1][y];
		expected.add(expectedSq);

		KingMoves km = new KingMoves();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));*/
	}
}