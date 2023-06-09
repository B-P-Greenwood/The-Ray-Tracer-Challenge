package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import main.Intersect;
import main.Intersections;
import main.Matrix;
import main.Point;
import main.Ray;
import main.Vector;
import main.shapes.Sphere;

public class RaySphereAndIntersectsTest {

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
        Intersections intersections = sphere.getIntersections(ray);

        assertAll(
            () -> assertEquals(intersections.getCount(), 2),
            () -> assertEquals(4, intersections.getItem(0).getValue()),
            () -> assertEquals(6, intersections.getItem(1).getValue())
        );
    }

    @Test 
    public void aRayIntersectsASphereAtATangentTest(){
        Point origin = new Point(0, 1, -5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        Sphere sphere = new Sphere();
        Intersections intersections = sphere.getIntersections(ray);

        assertAll(
            () -> assertEquals(intersections.getCount(), 2),
            () -> assertEquals(5, intersections.getItem(0).getValue()),
            () -> assertEquals(5, intersections.getItem(1).getValue())
        );
    }

    @Test 
    public void aRayMissesASphereTest(){
        Point origin = new Point(0, 2, -5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        Sphere sphere = new Sphere();
        Intersections intersections = sphere.getIntersections(ray);

        assertAll(
            () -> assertEquals(0, intersections.getCount())
        );
    }

    @Test 
    public void aRayOriginatesInsideASphereTest(){
        Point origin = new Point(0, 0, 0);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        Sphere sphere = new Sphere();
        Intersections intersections = sphere.getIntersections(ray);

        assertAll(
            () -> assertEquals(intersections.getCount(), 2),
            () -> assertEquals(-1, intersections.getItem(0).getValue()),
            () -> assertEquals(1, intersections.getItem(1).getValue())
        );
    }

    @Test 
    public void aSphereIsBehindARayTest(){
        Point origin = new Point(0, 0, 5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        Sphere sphere = new Sphere();
        Intersections intersections = sphere.getIntersections(ray);

        assertAll(
            () -> assertEquals(intersections.getCount(), 2),
            () -> assertEquals(-6, intersections.getItem(0).getValue()),
            () -> assertEquals(-4, intersections.getItem(1).getValue())
        );
    }

    @Test 
    public void anIntersectionEncapsulatesTAndObjectTest(){
        Sphere sphere = new Sphere();

        Intersect i = new Intersect(3.5, sphere);

        assertAll(
            () -> assertEquals(i.getValue(), 3.5),
            () -> assertEquals(i.getShape(), sphere)
        );
    }

    @Test 
    public void aggregatingIntersectionsTest(){
        Sphere sphere = new Sphere();

        Intersect i1 = new Intersect(1, sphere);
        Intersect i2 = new Intersect(2, sphere);
        Intersections xs = new Intersections(i1, i2);

        assertAll(
            () -> assertEquals(2, xs.getCount()),
            () -> assertEquals(1, xs.getItem(0).getValue()),
            () -> assertEquals(2, xs.getItem(1).getValue())
        );
    }

    @Test 
    public void intersectSetsTheObjectOnTheIntersectionTest(){
        Sphere sphere = new Sphere();

        Point origin = new Point(0, 0, -5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);
        Intersections xs = sphere.getIntersections(ray);

        assertAll(
            () -> assertEquals(2, xs.getCount()),
            () -> assertEquals(sphere, xs.getItem(0).getShape()),
            () -> assertEquals(sphere, xs.getItem(1).getShape())
        );
    }

    @Test 
    public void theSortingOfIntersectionsTest(){
        Sphere sphere = new Sphere();
        Intersect i1 = new Intersect(5, sphere);
        Intersect i2 = new Intersect(2, sphere);
        Intersections xs = new Intersections(i1, i2);

        assertAll(
            () -> assertEquals(2, xs.getCount()),
            () -> assertEquals(2, xs.getItem(0).getValue()),
            () -> assertEquals(5, xs.getItem(1).getValue())
        );
    }

    @Test 
    public void theHitWhenAllInterSectionsHavePositiveTTest(){
        Sphere sphere = new Sphere();
        Intersect i1 = new Intersect(1, sphere);
        Intersect i2 = new Intersect(2, sphere);
        Intersections xs = new Intersections(i2, i1);

        Intersect hit = xs.hit();

        assertAll(
            () -> assertEquals(i1, hit)
        );
    }

    @Test 
    public void theHitWhenSomeInterSectionsHaveNegativeTTest(){
        Sphere sphere = new Sphere();
        Intersect i1 = new Intersect(-1, sphere);
        Intersect i2 = new Intersect(1, sphere);
        Intersections xs = new Intersections(i2, i1);

        Intersect hit = xs.hit();

        assertAll(
            () -> assertEquals(i2, hit)
        );
    }

    @Test 
    public void theHitWhenAllInterSectionsHaveNegativeTTest(){
        Sphere sphere = new Sphere();
        Intersect i1 = new Intersect(-2, sphere);
        Intersect i2 = new Intersect(-1, sphere);
        Intersections xs = new Intersections(i2, i1);

        Intersect hit = xs.hit();

        assertAll(
            () -> assertEquals(null, hit)
        );
    }

    @Test 
    public void theIsAlwaysTheLowestNonNegativeIntersectionTest(){
        Sphere sphere = new Sphere();
        Intersect i1 = new Intersect(5, sphere);
        Intersect i2 = new Intersect(7, sphere);
        Intersect i3 = new Intersect(-3, sphere);
        Intersect i4 = new Intersect(2, sphere);
        Intersections xs = new Intersections(i2, i1, i3, i4);

        Intersect hit = xs.hit();

        assertAll(
            () -> assertEquals(i4, hit)
        );
    }

    @Test 
    public void translatingARayTest(){
        Point origin = new Point(1, 2, 3);
        Vector direction = new Vector(0, 1, 0);

        Ray ray = new Ray(origin, direction);
        Matrix translation = new Matrix().translation(3, 4, 5);
        Ray ray2 = ray.transform(translation);

        Point p = new Point(4, 6, 8);
        Vector v = new Vector(0, 1, 0);

        assertAll(
            () -> assertTrue(ray2.getOrigin().compareTuple(p)),
            () -> assertTrue(ray2.getDirection().compareTuple(v))
        );
    }

    @Test 
    public void scalingARayTest(){
        Point origin = new Point(1, 2, 3);
        Vector direction = new Vector(0, 1, 0);

        Ray ray = new Ray(origin, direction);
        Matrix s = new Matrix().scaling(2, 3, 4);
        Ray ray2 = ray.transform(s);

        Point p = new Point(2, 6, 12);
        Vector v = new Vector(0, 3, 0);

        assertAll(
            () -> assertTrue(ray2.getOrigin().compareTuple(p)),
            () -> assertTrue(ray2.getDirection().compareTuple(v))
        );
    }

    @Test 
    public void aSpheresDefaultTransformationTest(){
        Sphere s = new Sphere();
        Matrix m = s.getTransformMatrix();

        Matrix identity = new Matrix();

        assertAll(
            () -> assertTrue(identity.compareMatrix(m.getMatrix()))
        );
    }

    @Test 
    public void changingASpheresTransformationTest(){
        Sphere s = new Sphere();
        Matrix trans = new Matrix().translation(2, 3, 4);

        s.setTransformMatrix(trans);

        assertAll(
            () -> assertTrue(trans.compareMatrix(s.getTransformMatrix().getMatrix()))
        );
    }

    @Test 
    public void intersectingAScaledSphereWithARayTest(){
        Sphere s = new Sphere();
        Point origin = new Point(0, 0, -5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        s.setTransformMatrix(new Matrix().scaling(2, 2, 2));
        Intersections xs = s.getIntersections(ray);

        assertAll(
            () -> assertEquals(2, xs.getCount()),
            () -> assertEquals(3, xs.getItem(0).getValue()),
            () -> assertEquals(7, xs.getItem(1).getValue())
        );
    }

    @Test 
    public void intersectingATranslatedSphereWithARayTest(){
        Sphere s = new Sphere();
        Point origin = new Point(0, 0, -5);
        Vector direction = new Vector(0, 0, 1);

        Ray ray = new Ray(origin, direction);

        s.setTransformMatrix(new Matrix().translation(5, 0, 0));
        Intersections xs = s.getIntersections(ray);

        assertAll(
            () -> assertEquals(0, xs.getCount())
        );
    }
}
