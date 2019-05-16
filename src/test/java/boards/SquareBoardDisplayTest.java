package test.java.boards;

import static org.mockito.Mockito.mock;

import main.java.squareBoard.SquareBoardDisplay;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import main.java.game.Settings;
import main.java.squareBoard.SquareBoard;

import java.awt.Point;

public class SquareBoardDisplayTest {

    SquareBoardDisplay display;
    SquareBoard board;
    @Before
    public void setUp() throws Exception {
        Settings settings = mock(Settings.class);
        board = new SquareBoard(settings);
        display = new SquareBoardDisplay(null, null,
                new Point(0, 0), board);
    }

    @Test
    public final void testResize() {
        display.resizeChessboard(42);
        assertEquals(42, board.get_height(false));
    }

}
