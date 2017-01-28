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
import main.java.movesInCircleBoard.RookMovesInCircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class RookMovesInCircleBoardTest {
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	private CircleBoard board;
	private int x;
	private int y;
	Piece rook;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");
		x = 3;
		y = 3;
		board=  mock(CircleBoard.class);
		CircleBoardInitialization board_squares = new CircleBoardInitialization(board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());
		
		rook = PieceFactory.createPawnInCircleBoard(board, p1);
		board.getSquares()[x][y].setPiece(rook);
	}

	// Case 1: Forward Before Center
	@Test
	public final void testGetMovesForwardRook() {

		ArrayList<Square> expected = new ArrayList<Square>();
		// Forward
		for (int i = 1; i <= 2; i++) {
			expected.add(board.getSquares()[x][y + i]);
		}
		// Backwards
		for (int i = 1; i <= 3; i++) {
			expected.add(board.getSquares()[x][y - i]);
		}
		// Clockwise
		for (int i = 1; i <= 20; i++) {
			expected.add(board.getSquares()[x + i][y]);
		}
		// Counterclockwise
		for (int i = 3; i > 0; i--) {
			expected.add(board.getSquares()[x + i][y]);
		}

		RookMovesInCircleBoard pm = new RookMovesInCircleBoard();
		ArrayList<Square> obtained = pm.getMoves(rook, true);

		/*
		for (int i = 0; i < expected.size(); i++){
			Square esperado = expected.get(i);
			int sum = 0;
			for (int j = 0; j < obtained.size(); j++){
				if (expected.get(i)==obtained.get(j)){
					sum ++;
				}
			}
			if (sum == 0){
				System.out.println("No está: " + esperado.getPozX() + ", " + esperado.getPozY());
			}
			
		}*/
		assertTrue(obtained.containsAll(expected));

	}



}