package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import main.Intersect;
import main.Matrix;
import main.Point;
import main.Ray;
import main.Sphere;
import main.Tuple;
import main.Vector;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RayTest {

    @Test
    public void creatingAndQueryingARayTest(){
        Point origin = new Point(1, 2, 3);
        Vector direction = new Vector(4, 5, 6);

        Ray ray = new Ray(origin, direction);

        assertAll(
            () -> assertEquals(ray.getOrigin().getX(), origin.getX()),
            () -> assertEquals(ray.getOrigin().getY(), origin.getY()),
            () -> assertEquals(ray.getOrigin().getZ(), origin.getZ()),
            () -> assertEquals(ray.getDirection().getX(), direction.getX()),
            () -> assertEquals(ray.getDirection().getY(), direction.getY()),
            () -> assertEquals(ray.getDirection().getZ(), direction.getZ())
        );
    }

    @Test 
    public void computingAPointFromADistanceTest(){
        Point origin = new Point(2, 3, 4);
        Vector direction = new Vector(1, 0, 0);

        Ray ray = new Ray(origin, direction);

        Point p1 = new Point(2, 3, 4);
        Point p2 = new Point(3, 3, 4);
        Point p3 = new Point(1, 3, 4);
        Point p4 = new Point(4.5, 3, 4);

        assertAll(
            () -> assertTrue(p1.compareTuple(ray.position(0))),
            () -> assertTrue(p2.compareTuple(ray.position(1))),
            () -> assertTrue(p3.compareTuple(ray.position(-1))),
            () -> assertTrue(p4.compareTuple(ray.position(2.5)))
        );
    }

    @Test 
    public void aRayIntersectsASphereAtTwoPointsTest(){
        Point origin = new Point(0, 0, -5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        Sphere sphere = new Sphere();
        Intersect xs = new Intersect(sphere, ray);
        double[] intersections = xs.getIntersectionWithSphere();

        assertAll(
            () -> assertEquals(intersections.length, 2),
            () -> assertEquals(4, intersections[0]),
            () -> assertEquals(6, intersections[1])
        );
    }

    @Test 
    public void aRayIntersectsASphereAtATangentTest(){
        Point origin = new Point(0, 1, -5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        Sphere sphere = new Sphere();
        Intersect xs = new Intersect(sphere, ray);
        double[] intersections = xs.getIntersectionWithSphere();

        assertAll(
            () -> assertEquals(intersections.length, 2),
            () -> assertEquals(5, intersections[0]),
            () -> assertEquals(5, intersections[1])
        );
    }

    @Test 
    public void aRayMissesASphereTest(){
        Point origin = new Point(0, 2, -5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        Sphere sphere = new Sphere();
        Intersect xs = new Intersect(sphere, ray);
        double[] intersections = xs.getIntersectionWithSphere();

        assertAll(
            () -> assertEquals(intersections.length, 0)
        );
    }

    @Test 
    public void aRayOriginatesInsideASphereTest(){
        Point origin = new Point(0, 0, 0);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        Sphere sphere = new Sphere();
        Intersect xs = new Intersect(sphere, ray);
        double[] intersections = xs.getIntersectionWithSphere();

        assertAll(
            () -> assertEquals(intersections.length, 2),
            () -> assertEquals(-1, intersections[0]),
            () -> assertEquals(1, intersections[1])
        );
    }

    @Test 
    public void aSphereIsBehindARayTest(){
        Point origin = new Point(0, 0, 5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        Sphere sphere = new Sphere();
        Intersect xs = new Intersect(sphere, ray);
        double[] intersections = xs.getIntersectionWithSphere();

        assertAll(
            () -> assertEquals(intersections.length, 2),
            () -> assertEquals(-6, intersections[0]),
            () -> assertEquals(-4   , intersections[1])
        );
    }
    
}
