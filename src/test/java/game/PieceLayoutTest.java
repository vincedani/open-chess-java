package test.java.game;

import main.java.pieces.PieceLayout;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class PieceLayoutTest {

    private PieceLayout pieceLayout;

    @Before
    public void setUp() {
        // The images' URL is wrong in the config xml, like:
        // "file:///home/matis/Pobrane/szachy-stare/theme/default/images/King-W.png";
        // So I had to mock the class and avoid calling the constructor.
        pieceLayout = mock(PieceLayout.class);
        Mockito.doCallRealMethod().when(pieceLayout).setImage(any(Image.class));
    }
    @Test
    public final void testSetImage() throws Exception {
        String cuteKitten = "http://en.bcdn.biz/Images/2018/6/12/27565ee3-ffc0-4a4d-af63-ce8731b65f26.jpg";
        Image image = ImageIO.read(new URL(cuteKitten));
        pieceLayout.setImage(image);

        assertEquals(pieceLayout.image, image);
    }
}
