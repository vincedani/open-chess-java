package test.java.boards;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class CircleBoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void moveTestPawn() {
				
		// Initialize Board
		CircleBoard board=  new CircleBoard();
					
		// Set Player and Piece
		int x = 3; 
		int y = 5;
		Player p1 = new Player("Player1", "white");
		Piece pawn = PieceFactory.createPawnInCircleBoard(board, p1);
		pawn.setSquare(board.getSquare(x, y));
		
		// Set Mock-Squares and conditions
		Square begin = spy(new Square(x, y, pawn));
		Square end = spy(new Square(x + 8, y, pawn));
		
		// Pawn debe colocarse en begin, no es claro el motivo
		begin.setPiece(pawn);		

		// Call Move Function
		board.move(begin, end);
		
		verify(end, atLeast(2)).getPozY();			
	}

}
