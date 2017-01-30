package test.java.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.game.Player;
import main.java.game.Settings;
import main.java.game.Settings.BoardType;
import main.java.game.Settings.GameMode;

public class SettingsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void test() {
		Player p1 = new Player("P1", "black");
		Player p2 = new Player("P2", "blue");
		Player p3 = new Player("P3", "white");
		Player[] players = {p1, p2, p3};
		Settings settings = new Settings(players, BoardType.circleBoard); 

		// Set properties of Settings file
		settings.players = players;
		settings.boardType = BoardType.circleBoard;
		settings.gameMode = GameMode.newGame;
		
		// Next Player
		Player obtained = settings.nextPlayer(p1);
		
		assertEquals(obtained, p2);
		assertEquals("circleBoard",settings.boardType.toString());
		assertEquals("newGame",settings.gameMode.toString());
	}
}
