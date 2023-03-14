package main;

import main.shapes.Shape;

public class Intersect {
    private final Shape shape;
    private final double value;

    public Intersect(double value, Shape shape){
        this.shape = shape;
        this.value = value;
    }

    public Shape getShape(){ return shape; }

    public double getValue(){ return value; }

}
