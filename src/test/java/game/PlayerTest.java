package test.java.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import main.java.game.Player;
import main.java.game.Player.colors;

public class PlayerTest {
    private Player p;

    @Before
    public void setUp() throws Exception {
        p = new Player("Player1", "white");
        p.setType(Player.playerTypes.networkUser);
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
        main.java.game.Player.colors expected = colors.valueOf("white");
        main.java.game.Player.colors obtained = p.getColor();

        assertEquals(expected, obtained);
    }

    @Test
    public final void isGoDown() {
        assertFalse(p.isGoDown());
    }

    @Test
    public void testGetType() {
        Player.playerTypes expectedType = Player.playerTypes.networkUser;
        assertEquals(expectedType, p.playerType);
    }

    @Test
    public void testSetType() {
        p.setType(Player.playerTypes.localUser);
        assertEquals(Player.playerTypes.localUser, p.playerType);
    }
}
