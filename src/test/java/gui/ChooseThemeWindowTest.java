package test.java.gui;

import main.java.gui.ChooseThemeWindow;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ChooseThemeWindowTest {

    private Frame mainFrame;
    private ChooseThemeWindow chooseTheme;
    @Before
    public void setUp() throws Exception {
        mainFrame = new Frame();
        chooseTheme = new ChooseThemeWindow(mainFrame);
    }

    @Test
    public void success() {
        assertNotNull(chooseTheme);
    }
}