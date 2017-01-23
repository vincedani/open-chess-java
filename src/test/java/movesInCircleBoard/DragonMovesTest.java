package test.java.movesInCircleBoard;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.java.circleBoard.CircleBoard;
import main.java.circleBoard.CircleBoardInitialization;
import main.java.game.Player;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class DragonMovesTest {
	CircleBoardInitialization board_squares;
	CircleBoard board;
	Player p1; 
	
	// Pawn position
	int x1 = 4;
	int y1 = 3;
	// Rook position
	int x2 = 3; 
	int y2 = 3; 
	
	@Before
	public void setUp() throws Exception {
		
		// Initialize board
		board =  new CircleBoard();
		board_squares = new CircleBoardInitialization(board);
		
		p1 = new Player("Player1", "white");
		Piece pawn = PieceFactory.createPawnInCircleBoard(board, p1);
		Piece rook = PieceFactory.createRookInCircleBoard(board, p1);
		
		board_squares.getSquares()[x1][y1].setPiece(pawn);
		board_squares.getSquares()[x2][y2].setPiece(rook);
		board.initial = board_squares;

		board.move(board_squares.getSquares()[x2][y2], board_squares.getSquares()[x1][y1]);

	}

	@Test
	public final void releaseTheDragon() {
		String dragon = board_squares.getSquares()[x2][y2].piece.getName();
		assertEquals("Dragon", dragon);
	}
	
	@Test
	public final void theDragonFliesInnerCircle() {
		String dragon = board_squares.getSquares()[x2][y2].piece.getName();
		assertEquals("Dragon", dragon);
	}

}
