package test.java.movesInCircleBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.Constants;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.circleBoard.CircleBoardInitialization;
import main.java.game.Player;
import main.java.movesInCircleBoard.DragonMovesInCircleBoard;
import main.java.movesInCircleBoard.KingMovesInCircleBoard;
import main.java.pieces.ConcretePieceFactory;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class DragonMovesTest {
	CircleBoardInitialization board_squares;
	CircleBoard board;
	Player p1;
	Player p2;

	// Pawn position
	int x1 = 4;
	int y1 = 3;
	// Rook position
	int x2 = 3;
	int y2 = 3;

	@Before
	public void setUp() throws Exception {

		// Initialize board
		board = new CircleBoard();
		board_squares = new CircleBoardInitialization(board);

		p1 = new Player("Player1", "white");
		p2 = new Player("Player1", "black");
		ConcretePieceFactory pieceFac = new ConcretePieceFactory();
		Piece rook = pieceFac.GetPieceForCircleBoard(Constants.Symbols.Rook.toString(),Constants.Pieces.Rook.toString(), board, p1);//PieceFactory.createRookInCircleBoard(board, p1);
		Piece pawn = pieceFac.GetPieceForCircleBoard("",Constants.Pieces.Pawn.toString(), board, p2);//PieceFactory.createPawnInCircleBoard(board, p2);

		board_squares.getSquares()[x1][y1].setPiece(rook);
		board_squares.getSquares()[x2][y2].setPiece(pawn);

		board.initial = board_squares;

		board.move(board_squares.getSquares()[x1][y1], board_squares.getSquares()[x2][y2]);

	}

	@Test
	public final void releaseTheDragon() {
		String dragon = board_squares.getSquares()[x2][y2].piece.getName();
		assertEquals("Dragon", dragon);
	}

	@Test
	public final void theDragonFliesInnerCircle() {
		int x = x2;
		int y = y2;

		ArrayList<Square> expected = new ArrayList<Square>();

		expected.add(board.getSquares()[x	 ][y + 1]);
		expected.add(board.getSquares()[x	 ][y - 1]);
		expected.add(board.getSquares()[x + 1][y + 1]);
		expected.add(board.getSquares()[x + 1][y - 1]);
		expected.add(board.getSquares()[x - 1][y + 1]);
		expected.add(board.getSquares()[x - 1][y - 1]);
		expected.add(board.getSquares()[x + 1][y	]);
		expected.add(board.getSquares()[x - 1][y	]);
		
		DragonMovesInCircleBoard km = new DragonMovesInCircleBoard();
		ArrayList<Square> obtained = km.getMoves(board_squares.getSquares()[x2][y2].piece, true);

		assertTrue(obtained.containsAll(expected));
	}

}
