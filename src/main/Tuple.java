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
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getZ(){
        return this.z;
    }

    public double getW(){
        return this.w;
    }

    public Tuple add(Tuple b) {
        return new Tuple(this.x + b.getX(), this.y + b.getY(), this.z + b.getZ(), this.w + b.getW());
    }
    
    public Tuple subtract(Tuple b) {
        return new Tuple(this.x - b.getX(), this.y - b.getY(), this.z - b.getZ(), this.w - b.getW());
    }

    public Tuple negate() {
        return new Tuple(0 - this.x, 0 - this.y, 0 - this.z, 0 - this.w);
    }

    public Tuple scalar(double scalar) {
        return new Tuple(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
    }

    public Tuple divide(double divide) {
        return new Tuple(this.x / divide, this.y / divide, this.z / divide, this.w / divide);
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }

    public Tuple normalize(){
        double magnitude = this.magnitude();
        return new Tuple(this.x / magnitude, this.y / magnitude, this.z / magnitude, this.w / magnitude);
    }

    public double dotProduct(Vector b) {
        return this.x*b.getX() + this.y * b.getY() + this.z * b.getZ() + this.w + b.getW();
    }

    public Tuple crossProduct(Vector b) {
        return new Vector(this.y * b.getZ() - this.z * b.getY(), this.z * b.getX() - this.x * b.getZ(), this.x * b.getY() - this.y * b.getX());
    }
}