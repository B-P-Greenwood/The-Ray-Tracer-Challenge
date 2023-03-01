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
    
    assertTrue(c.pixelAt(2,3).equals(red));

    }

    @Test
    public void constructingThePPMHeaderTest(){
    Canvas c = new Canvas(5, 3);
    String result = c.canvasToPPM();
    
    assertEquals(result, "P3\n5 3\n255");

    }
}
