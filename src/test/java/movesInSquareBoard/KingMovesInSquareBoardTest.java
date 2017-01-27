package test.java.movesInSquareBoard;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.movesInSquareBoard.KingMovesInSquareBoard;
import main.java.pieces.King;
import main.java.pieces.PieceFactory;
import main.java.squareBoard.SquareBoard;
import main.java.squareBoard.SquareBoardInitialization;

public class KingMovesInSquareBoardTest {
	SquareBoardInitialization board_squares;
	ArrayList<IMove> moveBehaviour;
	private Player p1;
	Player p2;
	SquareBoard board;
	King king;
	int x = 0;
	int y = 0;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("Player1", "white");

		board = mock(SquareBoard.class);
		board_squares = new SquareBoardInitialization(true, board);
		when(board.getSquares()).thenReturn(board_squares.getSquares());

		board.initial = board_squares;

		ArrayList<IMove> kingMoves = new ArrayList<>();
		kingMoves.add(new KingMovesInSquareBoard());
		king = new King(board, p1, kingMoves);
		board_squares.getSquares()[x][y].setPiece(king);
	}

	// #1
	@Test
	public final void testBoundariesSquareBoard() {

		ArrayList<Square> expected = new ArrayList<Square>();

		Square expectedSq = board.getSquares()[1][0];
		expected.add(expectedSq);

		IMove km = new KingMovesInSquareBoard();
		ArrayList<Square> obtained = km.getMoves(king, true);

		board_squares.getSquares()[x][y].piece = null;

		assertTrue(obtained.containsAll(expected));
	}

	// #2
	@Test
	public final void testMovesInSquareBoard() {
		int x = 3;
		int y = 3;

		ArrayList<Square> expected = new ArrayList<Square>();
		board.getSquares()[x][y].setPiece(king);

		expected.add(board_squares.getSquares()[x	 ][y + 1]);
		expected.add(board_squares.getSquares()[x	 ][y - 1]);
		expected.add(board_squares.getSquares()[x + 1][y + 1]);
		expected.add(board_squares.getSquares()[x + 1][y - 1]);
		expected.add(board_squares.getSquares()[x - 1][y + 1]);
		expected.add(board_squares.getSquares()[x - 1][y - 1]);
		expected.add(board_squares.getSquares()[x + 1][y	]);
		expected.add(board_squares.getSquares()[x - 1][y	]);

		IMove km = new KingMovesInSquareBoard();
		ArrayList<Square> obtained = km.getMoves(king, true);

		assertTrue(obtained.containsAll(expected));
	}

	// Checkmate
	@Test
	public final void testCheckMate() {
		int x = 3;
		int y = 3;
		int a = 1;

		Player p1 = new Player("Player1", "white");
		Settings settings = mock(Settings.class);
		SquareBoard board = new SquareBoard(settings);

		ArrayList<IMove> kingMoves = new ArrayList<>();
		kingMoves.add(new KingMovesInSquareBoard());
		King rey = new King(board, p1, kingMoves);
		board.getSquares()[x][y].setPiece(rey);

		// Set King
		// ArrayList<Square> expected = new ArrayList<Square>();
		board.getSquares()[x][y].setPiece(rey);

		// Set Pawns
		p2 = new Player("Player2", "black");

		board.getSquares()[x	][y + a	].setPiece(PieceFactory.createBishopInSquareBoard(board, p2));
		board.getSquares()[x	][y - a	].setPiece(PieceFactory.createBishopInSquareBoard(board, p2));
		board.getSquares()[x + a][y + a	].setPiece(PieceFactory.createBishopInSquareBoard(board, p2));
		board.getSquares()[x + a][y - a	].setPiece(PieceFactory.createBishopInSquareBoard(board, p2));
		board.getSquares()[x - a][y + a	].setPiece(PieceFactory.createBishopInSquareBoard(board, p2));
		board.getSquares()[x - a][y - a	].setPiece(PieceFactory.createBishopInSquareBoard(board, p2));
		board.getSquares()[x + a][y		].setPiece(PieceFactory.createBishopInSquareBoard(board, p2));
		board.getSquares()[x - a][y		].setPiece(PieceFactory.createBishopInSquareBoard(board, p2));

		// Debugging Stuff
		/*
		 * ArrayList<Square> movimientos_rook =
		 * board.getSquares()[2][2].piece.allMoves(true);
		 * System.out.println("Movimientos del Bishop: "); for (int i = 0;
		 * i<movimientos_rook.size();i++){
		 * System.out.println(movimientos_rook.get(i).getPozX() + ", " +
		 * movimientos_rook.get(i).getPozY()); } IMove km = new
		 * KingMovesInSquareBoard(); ArrayList<Square> obtained =
		 * km.getMoves(rey, false); System.out.println("Movimientos del Rey: ");
		 * for (int i = 0; i<obtained.size();i++){
		 * System.out.println(obtained.get(i).getPozX() + ", " +
		 * obtained.get(i).getPozY());
		 * 
		 * }
		 */
		KingMovesInSquareBoard kingMovesTest = new KingMovesInSquareBoard();
		//assertFalse(kingMovesTest.isSafe(board,rey));
		assertFalse(rey.isSafe(rey.getSquare()));
		assertEquals(rey.isCheckmatedOrStalemated(), 1);
	}

	// Checkmate
	@Test
	public final void testChecked() {
		int x = 3;
		int y = 3;
		int a = 1;

		Player p1 = new Player("Player1", "white");
		Settings settings = mock(Settings.class);
		SquareBoard board = new SquareBoard(settings);

		ArrayList<IMove> kingMoves = new ArrayList<>();
		kingMoves.add(new KingMovesInSquareBoard());
		King rey = new King(board, p1, kingMoves);

		// Set King
		board.getSquares()[x][y].setPiece(rey);
		rey.setSquare(board.getSquares()[x][y]);

		// Set Bishop
		p2 = new Player("Player2", "black");
		board.getSquares()[x + a][y - 1].setPiece(PieceFactory.createBishopInSquareBoard(board, p2));

		assertTrue(!rey.isSafe(rey.getSquare()) && rey.isCheckmatedOrStalemated() == 0);
	}
}