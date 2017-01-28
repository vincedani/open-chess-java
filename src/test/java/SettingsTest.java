package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.game.Player;
import main.java.game.Settings;

public class SettingsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void test() {
		Settings settings = new Settings(); 
		Player Player[] = {settings.playerBlack, settings.playerBlue, settings.playerWhite};
		settings.players = Player;
		settings.boardType = settings.boardType.circleBoard;
		settings.gameMode = settings.gameMode.newGame;
		settings.gameType = settings.gameType.local;
		
		//settings.lang("This is a Key");
		Player obtained = settings.nextPlayer(settings.playerBlack);
		
		assertEquals(obtained, settings.playerBlue);
		assertEquals("circleBoard",settings.boardType.toString());
		assertEquals("newGame",settings.gameMode.toString());
		assertEquals("local",settings.gameType.toString());


	}

}
