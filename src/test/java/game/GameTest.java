package test.java.game;

import main.java.board.IChessboard;
import main.java.circleBoard.CircleBoard;
import main.java.game.Game;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.squareBoard.SquareBoard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.MouseEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    private Game game;
    private Settings settings;

    @Before
    public void setUp() {
        game = new Game();
        Player[] players = {
                new Player("Daninja", "black"),
                new Player("Makaroni", "white"),
        };

        settings = new Settings(players, Settings.BoardType.circleBoard);
        game.newGame(settings);
    }

    @Test
    public final void testGetSettings() {
        Settings actualSettings = game.getSettings();
        Assert.assertEquals(settings, actualSettings);
    }

    @Test
    public final void testSetSettings() {
        Player[] players = {
                new Player("NemNinja", "black"),
                new Player("NeMakaroni", "white"),
        };

        Settings newSettings = new Settings(players, Settings.BoardType.circleBoard);

        game.setSettings(newSettings);
        Assert.assertEquals(newSettings, game.getSettings());
    }

    @Test
    public void testGetChessboard() {
        IChessboard chessboard = game.getChessboard();

        Assert.assertTrue(chessboard instanceof CircleBoard);
    }

    @Test
    public void testSetChessboard() {
        settings.boardType = Settings.BoardType.squareBoard;
        SquareBoard board = new SquareBoard(settings);

        game.setChessboard(board);

        Assert.assertEquals(board, game.getChessboard());
    }

    @Test
    public void testMousePressed() {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getButton()).thenReturn(MouseEvent.BUTTON1);
        when(event.getX()).thenReturn(1);
        when(event.getY()).thenReturn(1);

        game.mousePressed(event);
    }

    @Test
    public void testNextMove() {
        game.nextMove();
    }

    @Test
    public void testSwitchActivePlayer() {
        game.switchActivePlayer();
    }

    @Test
    public void testEndGame() {
        game.endGame("Csak legyen meg a kettes!");

    }
}
