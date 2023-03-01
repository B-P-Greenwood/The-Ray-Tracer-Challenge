package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import main.Colour;

public class ColourTest {
    
    @DisplayName("Colours are (red, gree, blue) tuples")
    @Test
    public void coloursHaveThreePartsTest() {
        Colour c = new Colour(-0.5, 0.4, 1.7);
        assertAll(
            () -> assertEquals(c.getRed(), -0.5),
            () -> assertEquals(c.getGreen(), 0.4),
            () -> assertEquals(c.getBlue(), 1.7)
        );
    }

    @Test
    public void addingColoursTest() {
        Colour c1 = new Colour(0.9, 0.6, 0.75);
        Colour c2 = new Colour(0.7, 0.1, 0.25);
        Colour outcome = c1.add(c2);

        assertAll(
            () -> assertEquals(outcome.getRed(), 1.6),
            () -> assertEquals(outcome.getGreen(), 0.7),
            () -> assertEquals(outcome.getBlue(), 1.0)
        );
    }

    @Test
    public void subtractingColoursTest() {
        Colour c1 = new Colour(0.9, 0.6, 0.75);
        Colour c2 = new Colour(0.7, 0.1, 0.25);
        Colour outcome = c1.subtract(c2);
        Colour result = new Colour(0.2, 0.5, 0.5);

        assertTrue(outcome.equals(result));
    }

    @Test
    public void scalarColoursTest() {
        Colour c1 = new Colour(0.2, 0.3, 0.4);
        Colour outcome = c1.scalar(2);

        assertAll(
            () -> assertEquals(outcome.getRed(), 0.4),
            () -> assertEquals(outcome.getGreen(), 0.6),
            () -> assertEquals(outcome.getBlue(), 0.8)
        );
    }

    @Test
    public void multiplyColoursTest() {
        Colour c1 = new Colour(1, 0.2, 0.4);
        Colour c2 = new Colour(0.9, 1, 0.1);
        Colour outcome = c1.hadamardProduct(c2);
        Colour actual = new Colour(0.9, 0.2, 0.04);

        assertTrue(outcome.equals(actual));
    }
}
