package main;

public class Sphere extends Shape{
    private final Point origin = new Point(0, 0, 0);
    private final int radii = 1;
    public Sphere(){}

    public Point getOrigin(){ return origin;}

    public int getRadii(){ return radii;}
}
