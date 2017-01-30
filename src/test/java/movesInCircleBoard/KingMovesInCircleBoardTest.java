package test.java.movesInCircleBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IKing.KingState;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.movesInCircleBoard.KingMovesInCircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;

public class KingMovesInCircleBoardTest {
	ArrayList<IMove> moveBehaviour;
	Player p1;
	CircleBoard board;

	@Before
	public void setUp() throws Exception {
		// Set Player
		p1 = new Player("Player1", "white");
	}

	// #1
	@Test
	public final void testBoundariesCircleBoard() {
		int x = 0;
		int y = 0;
		
		// Initialize board
		CircleBoard board = new CircleBoard(); 
				
		// Set Piece
		Piece king = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.King);
		board.getSquares()[x][y].setPiece(king);
				
		// Expected move in border by King
		ArrayList<Square> expected = new ArrayList<Square>();
		Square expectedSq = board.getSquares()[1][0];
		expected.add(expectedSq);
		
		// Obtained by Algorithm
		KingMovesInCircleBoard km = new KingMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(board, king, true);

		assertTrue(obtained.containsAll(expected));
	}

	// #2
	@Test
	public final void testMovesInCircleBoard() {
		int x = 3;
		int y = 3;
		
		// Initialize board
		CircleBoard board = new CircleBoard(); 
				
		// Set Piece
		Piece king = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.King);
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
		IMove km = new KingMovesInCircleBoard();
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
		CircleBoard board = new CircleBoard(); 
				
		// Set Piece
		Piece king = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.King);
		board.getSquares()[x][y].setPiece(king);
		king.setSquare(board.getSquares()[x][y]);
		board.setKing(king);
		// Set Pawns
		Player p2 = new Player("Player2", "black");

		// Set Bishops around King
		board.getSquares()[x    ][y + a].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x    ][y - a].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x + a][y + a].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x + a][y - a].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x - a][y + a].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x - a][y - a].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x + a][y    ].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Bishop));
		board.getSquares()[x - a][y    ].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Bishop));

		KingMovesInCircleBoard km = new KingMovesInCircleBoard();
		assertFalse(km.isSafe(board, king, king.getSquare()));
		assertEquals(km.isCheckmatedOrStalemated(board, king), KingState.checkmate);
	}

	// Checkmate
	@Test
	public final void testChecked() {
		int x = 3;
		int y = 3;
		int a = 1;
		
		// Initialize board
		CircleBoard board = new CircleBoard();
				
		// Set Piece
		Piece king = PieceFactory.createSpecificPieceForCircleBoard(board, p1, PieceType.King);
		board.setKing(king);
		// Set Pawns
		Player p2 = new Player("Player2", "black");
		
		// Set King
		board.getSquares()[x][y].setPiece(king);
		king.setSquare(board.getSquares()[x][y]);

		// Set Bishop
		p2 = new Player("Player2", "black");
		board.getSquares()[x + a][y - 1].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, p2, PieceType.Bishop));

		KingMovesInCircleBoard km = (KingMovesInCircleBoard) king.getMoveBehaviour();
		assertTrue(!km.isSafe(board, king, king.getSquare()) && km.isCheckmatedOrStalemated(board, king) == KingState.safe);
	}
}