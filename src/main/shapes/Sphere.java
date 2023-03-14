package main.shapes;

import java.util.ArrayList;

import main.Intersect;
import main.Intersections;
import main.Point;
import main.Ray;
import main.Tuple;

public class Sphere extends Shape{
    private final Point centre = new Point(0, 0, 0);
    private final int radii = 1;
    ArrayList<Double> intersections = new ArrayList<>();
    
    public Sphere(){}

    public int getRadii(){ return radii;}
    public Point getCentre(){ return centre;}

    public Intersections getIntersections(Ray ray){
        //ray tr transform matrix 
        // Matrix m = new Matrix().createInverseMatrix();
        // ray = ray.transform(m);
        Tuple oc = ray.getOrigin().subtract(centre);
        double a = ray.getDirection().dotProduct(ray.getDirection());
        double b = 2.0 * ray.getDirection().dotProduct(oc);
        double c = oc.dotProduct(oc) - radii*radii;
        double discriminant = b * b - 4.0 * a * c;

        if (discriminant >= 0){
            double t1 = (-b - Math.sqrt(discriminant)) / (2.0 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2.0 * a);

                Intersect i1 = new Intersect(t1, this);
                Intersect i2 = new Intersect(t2, this);
                return new Intersections(i1, i2);

        }else return new Intersections();
    }
}
