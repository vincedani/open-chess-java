package test.java.gui;

import main.java.gui.PawnPromotionWindow;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static org.mockito.Mockito.mock;

public class PawnPromotionWindowTest {

    private PawnPromotionWindow promotionWindow;
    @Before
    public void setUp() throws Exception {
        Frame mainFrame = new Frame();
        promotionWindow = new PawnPromotionWindow(mainFrame, "Black");
    }

    @Test
    public void setColor() {
        promotionWindow.setColor("Black");
    }

    @Test
    public void actionPerformed() {
        promotionWindow.actionPerformed(mock(ActionEvent.class));
    }
}