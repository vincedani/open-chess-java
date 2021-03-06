package test.java.movesInSquareBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IKing.KingState;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.movesInSquareBoard.KingMovesInSquareBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;
import main.java.squareBoard.SquareBoard;

public class KingMovesInSquareBoardTest {
	Player p1;
	SquareBoard board;
	Settings settings; 
	@Before
	public void setUp() throws Exception {
		// Set Player
		p1 = new Player("Player1", "white");
	}

	// #1
	@Test
	public final void testBoundariesSquareBoard() {
		int x = 0;
		int y = 0;
		
		// Initialize board
		SquareBoard board = new SquareBoard(settings); 
				
		// Set Piece
		Piece king = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.King);
		board.getSquares()[x][y].setPiece(king);
				
		// Expected move in border by King
		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board.getSquares()[1][0];
		expected.add(expectedSq);
		
		// Obtained by Algorithm
		KingMovesInSquareBoard km = new KingMovesInSquareBoard();
		ArrayList<Square> obtained = km.getMoves(board, king, true);

		assertTrue(obtained.containsAll(expected));
	}

	// #2
	@Test
	public final void testMovesInSquareBoard() {
		int x = 3;
		int y = 3;
		
		// Initialize board
		SquareBoard board = new SquareBoard(settings); 
				
		// Set Piece
		Piece king = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.King);
		board.getSquares()[x][y].setPiece(king);

		// Expected Moves by King
		ArrayList<Square> expected = new ArrayList<Square>();
		expected.add(board.getSquares()[x    ][y + 1]);
		expected.add(board.getSquares()[x    ][y - 1]);
		expected.add(board.getSquares()[x + 1][y + 1]);
		expected.add(board.getSquares()[x + 1][y - 1]);
		expected.add(board.getSquares()[x - 1][y + 1]);
		expected.add(board.getSquares()[x - 1][y - 1]);
		expected.add(board.getSquares()[x + 1][y    ]);
		expected.add(board.getSquares()[x - 1][y    ]);

		// Obtained moves by Algorithm
		IMove km = new KingMovesInSquareBoard();
		ArrayList<Square> obtained = km.getMoves(board, king, true);

		assertTrue(obtained.containsAll(expected));
	}

	// Checkmate
	@Test
	public final void testCheckMate() {
		int x = 3;
		int y = 3;
		int a = 1;
		
		// Initialize board
		SquareBoard board = new SquareBoard(settings); 
				
		// Set Piece
		Piece king = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.King);
		board.getSquares()[x][y].setPiece(king);
		king.setSquare(board.getSquares()[x][y]);
		board.setKing(king);
		// Set Pawns
		Player p2 = new Player("Player2", "black");

		// Set Bishops around King
		board.getSquares()[x    ][y + a].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x    ][y - a].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x + a][y + a].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x + a][y - a].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x - a][y + a].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x - a][y - a].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x + a][y    ].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x - a][y    ].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, p2, PieceType.Bishop));

		KingMovesInSquareBoard km = new KingMovesInSquareBoard();
		assertFalse(km.isSafe(board, king, king.getSquare()));
		assertEquals(km.isCheckmatedOrStalemated(board, king), KingState.checkmate);
	}

	// Checked
	@Test
	public final void testChecked() {
		int x = 3;
		int y = 3;
		int a = 1;
		
		// Initialize board
		SquareBoard board = new SquareBoard(settings);
				
		// Set Piece
		Piece king = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.King);
		board.setKing(king);
		// Set Pawns
		Player p2 = new Player("Player2", "black");
		
		// Set King
		board.getSquares()[x][y].setPiece(king);
		king.setSquare(board.getSquares()[x][y]);

		// Set Bishop
		p2 = new Player("Player2", "black");
		board.getSquares()[x + a][y - 1].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, p2, PieceType.Bishop));

		KingMovesInSquareBoard km = (KingMovesInSquareBoard) king.getMoveBehaviour();
		assertTrue(!km.isSafe(board, king, king.getSquare()) && km.isCheckmatedOrStalemated(board, king) == KingState.safe);
	}
	
	@Test
	public final void castlingTest(){
		int xRook = 7; 
		int xRook2 = 0; 
		int yRook = 0; 
		
		int xKing = 4; 
		int yKing = 0;
		
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
		
		// Set Rook Left
		Piece rook2 = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.Rook);
		board.getSquares()[xRook2][yRook].setPiece(rook2);
		rook2.setSquare(board.getSquares()[xRook2][yRook]);
		
		// Expected
		ArrayList<Square> expected = new ArrayList<Square>(); 
		expected.add(board.getSquares()[6][yRook]);
		expected.add(board.getSquares()[2][yRook]);
		
		// Obtained by Algorithm
		KingMovesInSquareBoard km = new KingMovesInSquareBoard(); 
		ArrayList<Square> obtained = km.getMoves(board, king, true);
		
		assertTrue(obtained.containsAll(expected));
	}
	
	@Test
	public final void castlingFalseTest(){
		int xRook = 7; 
		int xRook2 = 0; 
		int yRook = 0; 
		
		int xKing = 4; 
		int yKing = 0;
		
		SquareBoard board = new SquareBoard(settings); 
		
		// Set King
		Piece king = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.King);
		board.getSquares()[xKing][yKing].setPiece(king);
		king.setSquare(board.getSquares()[xKing][yKing]);
		board.setKing(king);
		
		// Set Pawn to Right
		Piece pawn1 = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.Pawn);
		board.getSquares()[5][0].setPiece(pawn1);
		
		// Set Rook Right
		Piece rook = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.Rook);
		board.getSquares()[xRook][yRook].setPiece(rook);
		rook.setSquare(board.getSquares()[xRook][yRook]);
		
		// Set Pawn to Left
		Piece pawn2 = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.Pawn);
		board.getSquares()[3][0].setPiece(pawn2);
		
		// Set Rook Left
		Piece rook2 = PieceFactory.createSpecificPieceForSquareBoard(board, p1, PieceType.Rook);
		board.getSquares()[xRook2][yRook].setPiece(rook2);
		rook2.setSquare(board.getSquares()[xRook2][yRook]);
		
		// Expected
		ArrayList<Square> expected = new ArrayList<Square>(); 
		expected.add(board.getSquares()[6][yRook]);
		expected.add(board.getSquares()[2][yRook]);
		
		// Obtained by Algorithm
		KingMovesInSquareBoard km = new KingMovesInSquareBoard(); 
		ArrayList<Square> obtained = km.getMoves(board, king, true);
		
		assertFalse(obtained.containsAll(expected)); // Does not contain Castling move
	}
	
	
}
