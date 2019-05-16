package test.java.gui;

import main.java.gui.JChessTabbedPane;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.MouseEvent;

import static org.mockito.Mockito.mock;

public class JChessTabbedPaneTest {

    private JChessTabbedPane pane;
    @Before
    public void setUp() throws Exception {
        pane = new JChessTabbedPane();
    }

    @Test
    public void addTab() {
        pane.addTab("egy", mock(Component.class));
        pane.addTab("ketto", mock(Component.class), null);
    }

    @Test
    public void mouseEvents() {
        pane.mouseReleased(mock(MouseEvent.class));
        pane.mousePressed(mock(MouseEvent.class));
        pane.mouseClicked(mock(MouseEvent.class));
        pane.mouseEntered(mock(MouseEvent.class));
        pane.mouseExited(mock(MouseEvent.class));
    }

    @Test
    public void imageUpdate() {
        pane.imageUpdate(null, 42, 0, 0, 420, 420);
    }
}