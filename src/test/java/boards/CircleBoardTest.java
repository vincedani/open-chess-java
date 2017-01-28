package test.java.boards;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;

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
		Piece pawn = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.Pawn);
		pawn.setSquare(board.getSquares()[x][y]);
		
		// Set Mock-Squares and conditions
		Square begin = spy(new Square(x, y, pawn));
		Square end = spy(new Square(x + 8, y, pawn));
		
		// Pawn debe colocarse en begin, no es claro el motivo
		begin.setPiece(pawn);		

		// Call Move Function
		board.move(begin, end, true);
		
		// Does it call getPosY function?
		verify(end, atLeast(2)).getPosY();			
	}
	
	@Test
	public final void moveTestDragonBeginPiece() {
				
		// Initialize Board
		CircleBoard board=  new CircleBoard();
					
		// Set Player and Piece
		int x = 3; 
		int y = 3;
		Player p1 = new Player("Player1", "white");
		Piece dragon = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.Dragon);
		dragon.setSquare(board.getSquares()[x][y]);
		
		// Set Mock-Squares and conditions
		Square begin = spy(new Square(x, y, dragon));
		Square end = spy(new Square(x + 1, y, dragon));
		
		// Pawn debe colocarse en begin, no es claro el motivo
		begin.setPiece(dragon);		

		// Call Move Function
		board.move(begin, end, true);
		
		// Does it call SetPiece function?
		verify(end, atLeast(1)).setPiece(null);		
		
	}
	
	@Test
	public final void moveTestDragonEndPiece() {
				
		// Initialize Board
		CircleBoard board=  new CircleBoard();
					
		// Set Player and Piece
		int x = 3; 
		int y = 3;
		Player p1 = new Player("Player1", "white");
		Player p2 = new Player("Player2", "black");
		Piece dragon = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.Dragon);
		Piece bishop = PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Rook);
		
		bishop.setSquare(board.getSquares()[5][5]);
		dragon.setSquare(board.getSquares()[x][y]);
		
		// Set Mock-Squares and conditions
		Square begin = spy(new Square(5, 5, bishop));
		Square end = spy(new Square(x, y, dragon));
		
		// Pawn debe colocarse en begin, no es claro el motivo
		begin.setPiece(dragon);	
		end.setPiece(dragon);

		// Call Move Function
		board.move(begin, end, true);
		
		// Does it call SetPiece function?
		Piece warrior = begin.getPiece();
		verify(end, atLeast(1)).setPiece(warrior);		
		
	}
	
	@Test
	public final void getSquareFromCoordinatesTest(){
		CircleBoard board = spy(new CircleBoard());
		when(board.get_height()).thenReturn(500);
		when(board.getRadius()).thenReturn(500);
		
		Square obtained = board.getSquareFromCoordinates(20, 0);
		System.out.println(obtained);
	}

}
