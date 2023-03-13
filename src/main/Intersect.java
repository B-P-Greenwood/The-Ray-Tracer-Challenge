package main;

import java.util.ArrayList;

public class Intersect {
    private final Sphere sphere;
    private Ray ray;
    ArrayList<Double> intersections = new ArrayList<>();

    public Intersect(Sphere sphere, Ray ray){
        this.sphere = sphere;
        this.ray = ray;
    }

    public double[] getIntersectionWithSphere(){
        //ray tr transform matrix 
        // Matrix m = new Matrix().createInverseMatrix();
        // ray = ray.transform(m);
        Tuple oc = ray.getOrigin().subtract(sphere.getCentre());
        double a = ray.getDirection().dotProduct(ray.getDirection());
        double b = 2.0 * ray.getDirection().dotProduct(oc);
        double c = oc.dotProduct(oc) - sphere.getRadii() * sphere.getRadii();
        double discriminant = b * b - 4.0 * a * c;

        if (discriminant >= 0){
            double t1 = (-b - Math.sqrt(discriminant)) / (2.0 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2.0 * a);

            if(t1 > t2){
                intersections.add(t2); 
                intersections.add(t1);
            }else{
                intersections.add(t1); 
                intersections.add(t2);
            }
        }else return new double[] {};
        return new double[] {intersections.get(0), intersections.get(1)};
    }
    
}
