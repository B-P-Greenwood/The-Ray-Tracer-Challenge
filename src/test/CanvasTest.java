package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.stream.Stream;

import org.junit.jupiter.api.*;

import main.Canvas;
import main.Colour;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.MethodSource;
public class CanvasTest {
    
    @Test
    public void creatingACanvasTest(){
    Canvas c = new Canvas(10, 20);
    
    assertAll(
        () -> assertEquals(c.getWidth(),10),
        () -> assertEquals(c.getHeight(), 20)
    );
    }

    @Test
    public void writingPixelsToACanvasTest(){
    Canvas c = new Canvas(10, 20);
    Colour red = new Colour(1, 0, 0);
    c.writePixel(2, 3, red);
    
    assertTrue(c.getPixelAt(2,3).equals(red));

    }

    @Test
    public void constructingThePPMHeaderTest(){
    Canvas c = new Canvas(5, 3);
    String result = c.canvasToPPM();
    String[] lines = result.split("\n");
    
    assertAll(
        () -> assertEquals(lines[0], "P3"),
        () -> assertEquals(lines[1], "5 3"),
        () -> assertEquals(lines[2], "255")
        );
    }

    @Test 
    public void constructingThePPMPixelData(){
        Canvas c = new Canvas(5, 3);
        Colour c1 = new Colour(1.5, 0, 0);
        Colour c2 = new Colour(0, 0.5, 0);
        Colour c3 = new Colour(-0.5, 0, 1);
        c.writePixel(0, 0, c1);
        c.writePixel(2, 1, c2);
        c.writePixel(4, 2, c3);
        String result = c.canvasToPPM();
        String[] lines = result.split("\n");
        String l4 = "255 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        String l5 = "0 0 0 0 0 0 0 128 0 0 0 0 0 0 0";
        String l6 = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 255";

        assertAll(
            () -> assertEquals(lines[0], "P3"),
            () -> assertEquals(lines[1], "5 3"),
            () -> assertEquals(lines[2], "255"),
            () -> assertEquals(lines[3], l4),
            () -> assertEquals(lines[4], l5),
            () -> assertEquals(lines[5], l6)
        );
    }

    @Test
    public void splittingLongLineFilesTest(){
        Canvas canvas = new Canvas(10, 2);
        Colour c = new Colour(1, 0.8, 0.6);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                canvas.writePixel(j, i, c);
            }
        }
        String result = canvas.canvasToPPM();
        String[] lines = result.split("\n");
        String l4 = "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204";
        String l5 = "153 255 204 153 255 204 153 255 204 153 255 204 153";
        String l6 = "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204";
        String l7 = "153 255 204 153 255 204 153 255 204 153 255 204 153";

        assertAll(
            () -> assertEquals(lines[3], l4),
            () -> assertEquals(lines[4], l5),
            () -> assertEquals(lines[5], l6),
            () -> assertEquals(lines[6], l7)
        );
    }

    @Test
    public void ppmFilesAreTerminatedWithANewLineTest(){
        Canvas c = new Canvas(5, 3);
        String result = c.canvasToPPM();
    
        assertEquals(result.length()- 1, result.lastIndexOf("\n"));
    }

}
