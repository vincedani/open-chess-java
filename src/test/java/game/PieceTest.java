package test.java.game;

import main.java.game.Player;
import main.java.game.Settings;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.squareBoard.SquareBoard;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PieceTest {
    Piece piece;

    @Before
    public void setUp() {
        Player[] players = { new Player("Daninja", "black") };

        Settings settings = new Settings(players, Settings.BoardType.squareBoard);
        SquareBoard board = new SquareBoard(settings);

        piece = new Piece(board, players[0], PieceFactory.PieceType.Queen);
    }

    @Test
    public final void testGetSymbol() {
        String symbol = piece.getSymbol();

        // Code from black player and Queen piece.
        // Q: Queen
        // Bk: Black
        assertEquals("Q-Bk", symbol);
    }

    @Test
    public void testDraw() {
        Graphics2D mock = mock(Graphics2D.class);
        piece.draw(mock);
    }

    @Test
    public void testMyKing() {
        // TODO (vinced):
        Piece king = piece.myKingAsPiece();
        assertEquals(null, king);
    }
}
