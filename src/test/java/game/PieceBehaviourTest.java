package test.java.game;

import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceBehaviour;
import main.java.squareBoard.SquareBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PieceBehaviourTest {

  private CircleBoard board;
  private Square square;
  private PieceBehaviour pieceBehaviour;

  @Before
  public void setUp() {
    board = mock(CircleBoard.class);

    SquareBoard squareBoard = mock(SquareBoard.class);
    square = new Square(1, 2, mock(Piece.class));
    pieceBehaviour = new PieceBehaviour(squareBoard);
    when(
        pieceBehaviour.getSquare(any(Integer.class), any(Integer.class))
    ).thenReturn(square);
  }

  @Test
  public final void testIsOut_0() {
    // Branch 0: The piece is OUT, CircleBoard
    PieceBehaviour pieceBehaviour = new PieceBehaviour(board);
    int x = 24;
    int y = 5;
    boolean expected = true; // The piece is OUT
    boolean obtained = pieceBehaviour.isout(x, y);
    assertEquals(expected, obtained);
  }

  @Test
  public final void testIsOut_1() {
    // Branch 1: The piece is IN, CircleBoard
    PieceBehaviour pieceBehaviour = new PieceBehaviour(board);
    int x = 23;
    int y = 2;
    boolean expected = false; // The piece is NOT out
    boolean obtained = pieceBehaviour.isout(x, y);
    assertEquals(expected, obtained);
  }

  @Test
  public final void testIsOut_2() {
    // Branch 2: The piece is OUT, SquareBoard
    SquareBoard boardSquare = mock(SquareBoard.class);

    PieceBehaviour pieceBehaviour = new PieceBehaviour(boardSquare);
    int x = 8;
    int y = 0;
    boolean expected = true; // The piece is OUT
    boolean obtained = pieceBehaviour.isout(x, y);
    assertEquals(expected, obtained);
  }

  @Test
  public final void testIsOut_3() {
    // Branch 3: The piece is IN, SquareBoard
    SquareBoard boardSquare = mock(SquareBoard.class);
    PieceBehaviour pieceBehaviour = new PieceBehaviour(boardSquare);
    int x = 6;
    int y = 3;
    boolean expected = false; // The piece is NOT out
    boolean obtained = pieceBehaviour.isout(x, y);
    assertEquals(expected, obtained);
  }

  @Test
  public void testGetSquare() {
    Square actualSquare = pieceBehaviour.getSquare(1, 2);
    assertEquals(square, actualSquare);
  }

  @Test
  public void testGetKing() {
    // TODO (vinced) faszsag.
    assertEquals(true, true);
  }
}
