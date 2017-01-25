package test.java.movesInCircleBoard;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.circleBoard.CircleBoardDisplay;
import main.java.circleBoard.CircleBoardInitialization;
import main.java.game.Player;
import main.java.movesInCircleBoard.DragonMovesInCircleBoard;
import main.java.movesInCircleBoard.KingMovesInCircleBoard;
import main.java.pieces.King;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

public class DragonMovesTest {
	CircleBoardInitialization board_squares;
	CircleBoard board;
	DragonMovesInCircleBoard dm;
	
	Piece pawn, rook;
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
		board= new CircleBoard();
		board_squares = new CircleBoardInitialization(board);
		
		p1 = new Player("Player1", "white");
		p2 = new Player("Player1", "black");

		rook = PieceFactory.createRookInCircleBoard(board, p1);
		pawn = PieceFactory.createPawnInCircleBoard(board, p2);

		board_squares.getSquares()[x1][y1].setPiece(rook);
		board_squares.getSquares()[x2][y2].setPiece(pawn);

		board.initial = board_squares;

		board.move(board_squares.getSquares()[x1][y1], board_squares.getSquares()[x2][y2]);
		dm = new DragonMovesInCircleBoard();
	}

	@Test
	public final void releaseTheDragon() {
		String dragon = board_squares.getSquares()[x2][y2].piece.getName();
		assertEquals("Dragon", dragon);
	}

	@Test
	public final void theDragonFireBlasterInnerCircle() {
		int x = x2;
		int y = y2;
		
		// Set pieces around the Dragon
		addPiecesAround(pawn, x, y, 1);

		// Expected set of moves
		ArrayList<Square> expected = addToExpectedAround(x, y, 1);
		
		// Obtained set of moves 
		ArrayList<Square> obtained = dm.getMoves(board_squares.getSquares()[x2][y2].piece, true);
		
		assertTrue(obtained.containsAll(expected));
	}
	
	@Test
	public final void testIncreaseFireBlasterMiddleCircle() {
		// Blast fire
		dm.increaseFireLoader();
		int x = x2;
		int y = y2;
		
		// Set pieces around the Dragon
		addPiecesAround(pawn, x, y, 1);
		addPiecesAround(pawn, x, y, 2);

		// Expected set of moves
		ArrayList<Square> expected = addToExpectedAround(x, y, 1);
		expected.addAll(addToExpectedAround(x, y, 2));
		
		// Obtained set of moves ;
		ArrayList<Square> obtained = dm.getMoves(board_squares.getSquares()[x][y].piece, true);

		assertTrue(obtained.containsAll(expected));
	}
	
	@Test
	public final void testKingAroundDragon() {
		// Blast fire
		int x = y2;
		int y = x2;
		
		// Set King around the Dragon
		ArrayList<IMove> kingMoves = new ArrayList<>();
		kingMoves.add(new KingMovesInCircleBoard());
		Piece king = new King(board, p2, kingMoves);
		board_squares.getSquares()[x+1][y].setPiece(king);
		board_squares.getSquares()[x-1][y].setPiece(pawn);

		// Expected set of moves
		ArrayList<Square> expected = new ArrayList<Square>();
		expected.add(board.getSquares()[x+1][y]);
		expected.add(board.getSquares()[x-1][y]);

		// Obtained set of moves
		ArrayList<Square> obtained = dm.getMoves(board_squares.getSquares()[x][y].piece, true);
		
		// Debugging Stuff
		/*System.out.println("Obtained \n");
		for (int i=0; i<obtained.size();i++){
			System.out.println(obtained.get(i).getPozX() + ", " + obtained.get(i).getPozY());
		}		
		System.out.println("expected");
		for (int i=0; i<expected.size();i++){
			System.out.println(expected.get(i).getPozX() + ", " + expected.get(i).getPozY());
		}*/

		assertFalse(obtained.contains(board.getSquares()[x+1][y])); // FALSE, that it CAN move where the KING IS.
		
	}
	
	void addPiecesAround(Piece piece, int x, int y, int a) {
		board_squares.getSquares()[x	][y + a ].setPiece(piece);
		board_squares.getSquares()[x	][y - a ].setPiece(piece);
		board_squares.getSquares()[x + a][y + a ].setPiece(piece);
		board_squares.getSquares()[x + a][y - a ].setPiece(piece);
		board_squares.getSquares()[x - a][y + a ].setPiece(piece);
		board_squares.getSquares()[x - a][y - a ].setPiece(piece);
		board_squares.getSquares()[x + a][y		].setPiece(piece);
		board_squares.getSquares()[x - a][y		].setPiece(piece);
	}
	
	ArrayList<Square> addToExpectedAround(int x, int y, int a) {
		ArrayList<Square> expected = new ArrayList<Square>();
		expected.add(board.getSquares()[x	 ][y + a]);
		expected.add(board.getSquares()[x	 ][y - a]);
		expected.add(board.getSquares()[x + a][y + a]);
		expected.add(board.getSquares()[x + a][y - a]);
		expected.add(board.getSquares()[x - a][y + a]);
		expected.add(board.getSquares()[x - a][y - a]);
		expected.add(board.getSquares()[x + a][y	]);
		expected.add(board.getSquares()[x - a][y	]);
		return expected;
	}

}
