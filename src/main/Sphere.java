package main;

public class Sphere extends Shape{
    private final Point centre = new Point(0, 0, 0);
    private final int radii = 1;
    public Sphere(){}

    public int getRadii(){ return radii;}
    public Point getCentre(){ return centre;}
}
