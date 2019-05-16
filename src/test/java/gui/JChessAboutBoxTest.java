package test.java.gui;

import main.java.gui.JChessAboutBox;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class JChessAboutBoxTest {

    private Frame mainFrame;
    private JChessAboutBox aboutBox;
    @Before
    public void setUp() throws Exception {
        mainFrame = new Frame();
        aboutBox = new JChessAboutBox(mainFrame);
    }

    @Test
    public void closeAboutBox() {
        aboutBox.closeAboutBox();
    }
}