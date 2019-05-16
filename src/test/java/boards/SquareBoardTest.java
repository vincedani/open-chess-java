package test.java.boards;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;
import main.java.squareBoard.SquareBoard;

public class SquareBoardTest {

	Settings settings;
	@Before
	public void setUp() throws Exception {
		settings = mock(Settings.class);
	}

	@Test
	public final void moveTestPawn() {	
		// Initialize Board
		SquareBoard board=  new SquareBoard(settings);
					
		// Set Player and Piece
		int x = 3; 
		int y = 5;
		Player p1 = new Player("Player1", "white");
		Piece pawn = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.Pawn);
		pawn.setSquare(board.getSquares()[x][y]);
		
		// Set Mock-Squares and conditions
		Square begin = spy(new Square(x, y, pawn));
		Square end = spy(new Square(x, y -1 , pawn));
		
		// Pawn debe colocarse en begin, no es claro el motivo
		begin.setPiece(pawn);		

		// Call Move Function
		board.move(begin, end, true);
		
		// Does it call getPosY function?
		verify(end, atLeast(2)).getPosY();			
	}	
	@Test
	public final void moveCastlingTest(){
		int xRook = 7; 
		int yRook = 0; 
		
		int xKing = 4; 
		int yKing = 0;
		
		// Set Player and Board
		Player p1 = new Player("Player1", "white");
		SquareBoard board = new SquareBoard(settings); 
		
		// Set King
		Piece king = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.King);
		board.getSquares()[xKing][yKing].setPiece(king);
		king.setSquare(board.getSquares()[xKing][yKing]);
		board.setKing(king);
		
		// Set Rook Right
		Piece rook = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.Rook);
		board.getSquares()[xRook][yRook].setPiece(rook);
		rook.setSquare(board.getSquares()[xRook][yRook]);
		
		board.move(board.getSquares()[xKing][yKing], board.getSquares()[6][0], false);		
		assertTrue(board.getSquares()[6][0].getPiece()==king && board.getSquares()[5][0].getPiece()==rook);
	}

	@Test
	public final void testSetPieces() {
		Player[] players = {
				new Player("me", "white"),
				new Player("you", "black")
		};
		SquareBoard board = new SquareBoard(settings);
		board.setPieces(players);
	}
	
}
