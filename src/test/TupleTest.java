package test;
import main.Tuple;
import main.Point;
import main.Vector;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TupleTest {
    
    @DisplayName("A tuple with w=1.0 is a point")
    @Test
    public void tupleIsAPointTest() {
        Tuple a = new Tuple(4.3, -4.2, 3.1, 1.0);
        assertAll(
            () -> assertEquals(a.getX(), 4.3),
            () -> assertEquals(a.getY(), -4.2),
            () -> assertEquals(a.getZ(), 3.1),
            () -> assertEquals(a.getW(), 1.0)
        );
    }

    @DisplayName("A tuple with w=0 is a vector")
    @Test
    public void tupleIsAVectorTest() {
        Tuple a = new Tuple(4.3, -4.2, 3.1, 0.0);
        assertAll(
            () -> assertEquals(a.getX(), 4.3),
            () -> assertEquals(a.getY(), -4.2),
            () -> assertEquals(a.getZ(), 3.1),
            () -> assertEquals(a.getW(), 0.0)
        );
    }

    @DisplayName("Point creates tuples with w=1")
    @Test
    public void pointIsATupleTest() {
        Point p = new Point(4, -4, 3);
        Tuple t = new Tuple(4, -4, 3,1);
        assertAll(
            () -> assertEquals(p.getX(), t.getX()),
            () -> assertEquals(p.getY(), t.getY()),
            () -> assertEquals(p.getZ(), t.getZ()),
            () -> assertEquals(p.getW(), t.getW())
        );
    }

    @DisplayName("Point creates tuples with w=0")
    @Test
    public void vectorIsATupleTest() {
        Vector v = new Vector(4, -4, 3);
        Tuple t = new Tuple(4, -4, 3,0);
        assertAll(
            () -> assertEquals(v.getX(), t.getX()),
            () -> assertEquals(v.getY(), t.getY()),
            () -> assertEquals(v.getZ(), t.getZ()),
            () -> assertEquals(v.getW(), t.getW())
        );
    }

    @DisplayName("Adding two tuples")
    @Test
    public void addingTwoTuplesTest() {
        Tuple t1 = new Tuple(3, -2, 5,1);
        Tuple t2 = new Tuple(-2, 3, 1, 0);
        Tuple outcome = t1.add(t2);
        assertAll(
            () -> assertEquals(outcome.getX(), 1),
            () -> assertEquals(outcome.getY(), 1),
            () -> assertEquals(outcome.getZ(), 6),
            () -> assertEquals(outcome.getW(), 1)
        );
    }

    @DisplayName("Subtracting two points")
    @Test
    public void subtractingTwoPointsTest() {
        Point p1 = new Point(3, 2, 1);
        Point p2 = new Point(5, 6, 7);
        Tuple outcome = p1.subtract(p2);
        Vector v = new Vector(-2, -4, -6);
        assertAll(
            () -> assertEquals(outcome.getX(), v.getX()),
            () -> assertEquals(outcome.getY(), v.getY()),
            () -> assertEquals(outcome.getZ(), v.getZ()),
            () -> assertEquals(outcome.getW(), v.getW())
        );
    }

    @DisplayName("Subtracting a vector from a point")
    @Test
    public void subtractingAVectorFromAPointTest() {
        Point p1 = new Point(3, 2, 1);
        Vector p2 = new Vector(5, 6, 7);
        Tuple outcome = p1.subtract(p2);
        Point p = new Point(-2, -4, -6);
        assertAll(
            () -> assertEquals(outcome.getX(), p.getX()),
            () -> assertEquals(outcome.getY(), p.getY()),
            () -> assertEquals(outcome.getZ(), p.getZ()),
            () -> assertEquals(outcome.getW(), p.getW())
        );
    }

    @DisplayName("Subtracting a vector from a vector")
    @Test
    public void subtractingTwoVectorsTest() {
        Vector p1 = new Vector(3, 2, 1);
        Vector p2 = new Vector(5, 6, 7);
        Tuple outcome = p1.subtract(p2);
        Vector p = new Vector(-2, -4, -6);
        assertAll(
            () -> assertEquals(outcome.getX(), p.getX()),
            () -> assertEquals(outcome.getY(), p.getY()),
            () -> assertEquals(outcome.getZ(), p.getZ()),
            () -> assertEquals(outcome.getW(), p.getW())
        );
    }

    @DisplayName("Subtracting a vector from a  zero vector")
    @Test
    public void subtractingAVectorFromAZeroVectorTest() {
        Vector v1 = new Vector(0, 0, 0);
        Vector v2 = new Vector(1, -2, 3);
        Tuple outcome = v1.subtract(v2);
        Vector p = new Vector(-1, 2, -3);
        assertAll(
            () -> assertEquals(outcome.getX(), p.getX()),
            () -> assertEquals(outcome.getY(), p.getY()),
            () -> assertEquals(outcome.getZ(), p.getZ()),
            () -> assertEquals(outcome.getW(), p.getW())
        );
    }

    @DisplayName("Negating a Tuple")
    @Test
    public void negatingATupleTest() {
        Tuple t1 = new Tuple(1, -2, 3, -4);
        Tuple outcome = t1.negate();

        assertAll(
            () -> assertEquals(outcome.getX(), -1),
            () -> assertEquals(outcome.getY(), 2),
            () -> assertEquals(outcome.getZ(), -3),
            () -> assertEquals(outcome.getW(), 4)
        );
    }

    @DisplayName("Multiplying a tuple by a scalar")
    @Test
    public void multiplyingATupleByAScalarTest() {
        Tuple t1 = new Tuple(1, -2, 3, -4);
        Tuple outcome = t1.scalar(3.5);

        assertAll(
            () -> assertEquals(outcome.getX(), 3.5),
            () -> assertEquals(outcome.getY(), -7),
            () -> assertEquals(outcome.getZ(), 10.5),
            () -> assertEquals(outcome.getW(), -14)
        );
    }

    @DisplayName("Multiplying a tuple by a fraction")
    @Test
    public void multiplyingATupleByAFractionTest() {
        Tuple t1 = new Tuple(1, -2, 3, -4);
        Tuple outcome = t1.scalar(0.5);

        assertAll(
            () -> assertEquals(outcome.getX(), 0.5),
            () -> assertEquals(outcome.getY(), -1),
            () -> assertEquals(outcome.getZ(), 1.5),
            () -> assertEquals(outcome.getW(), -2)
        );
    }

    @DisplayName("Dividing a tuple by a scalar")
    @Test
    public void dividingATupleByAScalarTest() {
        Tuple t1 = new Tuple(1, -2, 3, -4);
        Tuple outcome = t1.divide(2);

        assertAll(
            () -> assertEquals(outcome.getX(), 0.5),
            () -> assertEquals(outcome.getY(), -1),
            () -> assertEquals(outcome.getZ(), 1.5),
            () -> assertEquals(outcome.getW(), -2)
        );
    }

    private static Stream<Arguments> magnitudeTestScenarios() {
        return Stream.of(
          Arguments.of(new Vector(1, 0, 0), 1),
          Arguments.of(new Vector(0, 1, 0), 1),
          Arguments.of(new Vector(1, 0, 0), 1 ),
          Arguments.of(new Vector(1, 2, 3), Math.sqrt(14)),
          Arguments.of(new Vector(-1, -2, -3), Math.sqrt(14))
        );
    }

    @DisplayName("Tests checking the magnitude of vectors")
    @ParameterizedTest
    @MethodSource("magnitudeTestScenarios")
    public void testingTheMagnitudeOfVectorsTest(Vector vector, double result) {
        double m = vector.magnitude();
        assertEquals(m, result);
    }

    private static Stream<Arguments> normalizingAVectorTestScenarios() {
        return Stream.of(
          Arguments.of(new Vector(4, 0, 0), new Vector(1, 0, 0)),
          Arguments.of(new Vector(1, 2, 3), new Vector(1/Math.sqrt(14), 2/Math.sqrt(14), 3/Math.sqrt(14))),
          Arguments.of(new Vector(1, 0, 0), new Vector(1, 0, 0) )
        );
    }

    @DisplayName("Tests checking the magnitude of vectors")
    @ParameterizedTest
    @MethodSource("normalizingAVectorTestScenarios")
    public void testingNormalizingOfVectorsTest(Vector vector, Vector result) {
        Tuple m = vector.normalize();
        assertAll(
            () -> assertEquals(m.getX(), result.getX()),
            () -> assertEquals(m.getY(), result.getY()),
            () -> assertEquals(m.getZ(), result.getZ()),
            () -> assertEquals(m.getW(), result.getW())
        );
    }

    @DisplayName("The dot product of two tuples")
    @Test
    public void theDotProductOfTwoTuplesTest() {
        Vector t1 = new Vector(1, 2, 3);
        Vector t2 = new Vector(2, 3, 4);

        assertEquals(t1.dotProduct(t2), 20);
    } 

    private static Stream<Arguments> crossProductScenarios() {
        return Stream.of(
          Arguments.of(new Vector(1, 2, 3), new Vector(2, 3, 4), new Vector(-1, 2, -1)),
          Arguments.of(new Vector(2, 3, 4), new Vector(1, 2, 3), new Vector(1, -2, 1))
        );
    }

    @DisplayName("The cross product of two vectors")
    @ParameterizedTest
    @MethodSource("crossProductScenarios")
    public void theCrossProductOfTwoVectorsTest(Vector v1, Vector v2, Vector result) {

        Tuple outcome = v1.crossProduct(v2);

        assertAll(
            () -> assertEquals(outcome.getX(), result.getX()),
            () -> assertEquals(outcome.getY(), result.getY()),
            () -> assertEquals(outcome.getZ(), result.getZ()),
            () -> assertEquals(outcome.getW(), result.getW())
        );
    }
}
