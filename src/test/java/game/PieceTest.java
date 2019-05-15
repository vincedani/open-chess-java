package test.java.game;

import main.java.board.IKing;
import main.java.board.Square;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.squareBoard.SquareBoard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PieceTest {
    Piece piece;
    Piece king;

    @Before
    public void setUp() {
        Player[] players = { new Player("Daninja", "black") };

        Settings settings = new Settings(players, Settings.BoardType.squareBoard);
        SquareBoard board = new SquareBoard(settings);

        king = mock(Piece.class);
        when(king.getSymbol()).thenReturn("Da-ninja");
        when(king.getPlayer()).thenReturn(players[0]);
        when(king.getMoveBehaviour()).thenReturn((board1, piece, ignoreKing) -> {
            ArrayList<Square> squares = new ArrayList<>();
            squares.add(new Square(3, 3, mock(Piece.class)));

            return squares;
        });
        board.setKing(king);

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
        // cannot be cast to class main.java.board.IKing from main.java.board.IMove
        IKing king = piece.myKing();
        assertEquals(this.king, king);
    }
}
