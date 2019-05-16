package test.java.gui;

import main.java.gui.GUI;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GUITest {

    private GUI gui;

    @Before
    public void before() {
        gui = new GUI();
    }

    @Test
    public void testConstructor() {

        assertNotNull(gui);
    }

    @Test
    public void loadImage() {
        assertNotNull(GUI.loadImage("hunter", "Preview.png"));
    }
}