package test.java.squareBoard;

import main.java.board.Square;
import main.java.game.Settings;
import main.java.squareBoard.SquareBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SquareBoardTest {

    private Settings settings;
    @Before
    public void setUp() throws Exception {
        settings = mock(Settings.class);
    }

    @Test
    public void getSquareFromCoordinates() {
        SquareBoard board =  new SquareBoard(settings);
        assertNull(board.getSquareFromCoordinates(10000, 10000));
    }

    @Test
    public void getWidth() {
        SquareBoard board =  new SquareBoard(settings);
        assertEquals(0, board.get_widht(false));
    }

    @Test
    public void selectActive(){
        SquareBoard board = new SquareBoard(settings);
        Square s = board.getSquareFromCoordinates(0,0);
        board.select(s);
        assertEquals(s, board.getActiveSquare());
    }

    @Test
    public void setActive(){
        SquareBoard board = new SquareBoard(settings);
        Square s = board.getSquareFromCoordinates(0,0);
        board.setActiveSquare(s);
        assertEquals(s, board.getActiveSquare());
    }
}