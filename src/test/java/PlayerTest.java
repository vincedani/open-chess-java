package test.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.game.Player;
import main.java.game.Player.colors;

public class PlayerTest {
	private Player p;

	@Before
	public void setUp() throws Exception {
		p = new Player("Player1", "red");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetName() {
		String expected = "Player1";
		String obtained = p.getName();
		assertEquals(expected, obtained);
	}

	@Test
	public final void testSetName() {
		String name = "Homer";
		p.setName(name);
		assertNotNull(p.getName());
	}

	@Test
	public final void testGetColor() {
		main.java.game.Player.colors expected = colors.valueOf("red");
		main.java.game.Player.colors obtained = p.getColor();

		assertEquals(expected, obtained);
	}

	@Test 
	public final void isGoDown(){
		assertFalse(p.isGoDown());
	}
}
