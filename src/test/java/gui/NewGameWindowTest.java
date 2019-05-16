package test.java.gui;

import main.java.gui.NewGameWindow;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewGameWindowTest {

    NewGameWindow ngw;

    @Before
    public void before() {
        ngw = new NewGameWindow();
    }

    @Test
    public void testConstructor() {
        assertNotNull(ngw);
    }
}