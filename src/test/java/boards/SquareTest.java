package test.java.boards;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import main.java.game.Player;
import org.junit.Before;
import org.junit.Test;

import main.java.board.Square;
import main.java.pieces.Piece;

public class SquareTest
{
  private Piece pawn;
  private Square cookie;
  private Player player;

  @Before
  public void setUp() throws Exception {
    player = new Player("Daninja", "black");

    pawn = mock(Piece.class);
    when(pawn.getPlayer()).thenReturn(player);

    cookie = new Square(1, 1, pawn);
  }

  @Test
  public void testGetPozX() {
    int expected = 1;
    int obtained = cookie.getPosX();

    assertEquals(expected, obtained);
  }

  @Test
  public void testGetPozY() {
    int expected = 1;
    int obtained = cookie.getPosY();

    assertEquals(expected, obtained);
  }

  @Test
  public void testClone() {
    int targetX = 3;
    int targetY = 4;

    Square square = new Square(targetX, targetY, pawn);
    Square clonedSquare = cookie.clone(square);

    assertEquals(targetX, clonedSquare.getPosX());
    assertEquals(targetY, clonedSquare.getPosY());
  }

  @Test
  public void testGetPlayer() {
    Player player = cookie.getPlayer();
    assertEquals(this.player, player);
  }
}
