package test.java.game;

import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.pieces.Piece;
import main.java.pieces.PieceDisplay;
import main.java.pieces.PieceFactory;
import main.java.squareBoard.SquareBoard;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.mockito.Mockito.mock;

public class PieceDisplayTest {
    private Player[] players = new Player[1];

    @Before
    public void setUp() {
        players[0] = new Player("Daninja", "black");
    }

    @Test
    public final void testSquareDrawing() {
        Settings settings = new Settings(players, Settings.BoardType.squareBoard);
        SquareBoard board = new SquareBoard(settings);

        Piece piece = new Piece(board, players[0], PieceFactory.PieceType.Queen);
        piece.setSquare(new Square(1, 2, piece));

        PieceDisplay display = new PieceDisplay(piece);
        Graphics2D graphics2D = mock(Graphics2D.class);
        display.draw(graphics2D);
    }

    @Test
    public void testCircleDrawing() {
        CircleBoard board = new CircleBoard();

        Piece piece = new Piece(board, players[0], PieceFactory.PieceType.Queen);
        piece.setSquare(new Square(1, 2, piece));

        PieceDisplay display = new PieceDisplay(piece);
        Graphics2D graphics2D = mock(Graphics2D.class);
        display.draw(graphics2D);
    }
}
