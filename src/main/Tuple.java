package main;

public class Tuple {
    private double x,y,z,w;

    public Tuple(double x, double y, double z, double w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }   

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    public double getW(){
        return w;
    }

    public Tuple add(Tuple b) {
        return new Tuple(x + b.getX(), y + b.getY(), z + b.getZ(), w + b.getW());
    }
    
    public Tuple subtract(Tuple b) {
        return new Tuple(x - b.getX(), y - b.getY(), z - b.getZ(), w - b.getW());
    }

    public Tuple negate() {
        return new Tuple(0 - x, 0 - y, 0 - z, 0 - w);
    }

    public Tuple scalar(double scalar) {
        return new Tuple(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Tuple divide(double divide) {
        return new Tuple(x / divide, y / divide, z / divide, w / divide);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Tuple normalize(){
        double magnitude = magnitude();
        return new Tuple(x / magnitude, y / magnitude, z / magnitude, w / magnitude);
    }

    public double dotProduct(Vector b) {
        return x*b.getX() + y * b.getY() + z * b.getZ() + w + b.getW();
    }

    public Tuple crossProduct(Vector b) {
        return new Vector(y * b.getZ() - z * b.getY(), z * b.getX() - x * b.getZ(), x * b.getY() - y * b.getX());
    }
}