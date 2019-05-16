package test.java.boards;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.awt.*;

import main.java.circleBoard.CircleBoard;
import main.java.circleBoard.CircleBoardDisplay;

public class CircleBoardDisplayTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    final public void testTopLeft() {
        CircleBoard board = new CircleBoard();
        assertEquals(board.getDisplay().getTopLeftPoint(), new Point(0, 0));
    }

    @Test
    final public void testGetHeight() {
        CircleBoard board = new CircleBoard();
        assertEquals(board.get_height(), board.getDisplay().getHeight());
    }

    @Test
    final public void testRadius() {
        CircleBoard board = new CircleBoard();
        CircleBoardDisplay display = new CircleBoardDisplay(new Point(0, 0), board);
        assertEquals(board.getRadius(), display.getRadius());
    }

    @Test
    final public void testSquareHeight() {
        CircleBoardDisplay display = new CircleBoardDisplay(new Point(0, 0), new CircleBoard());
        assertEquals(display.getRadius()/9, display.get_square_height());
    }

    @Test
    final public void testResize() {
        CircleBoardDisplay display = new CircleBoardDisplay(new Point(0, 0), new CircleBoard());
        display.resizeChessboard(42);
        assertEquals(42, display.getHeight());
    }

}
