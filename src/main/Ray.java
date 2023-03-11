package main;

public class Ray {
    private final Point origin; 
    private final Vector direction;

    public Ray(Point origin, Vector direction){
        this.origin = origin;
        this.direction = direction; 
    }

    public Point getOrigin(){
        return origin;
    }

    public Vector getDirection(){
        return direction;
    }

    public Point position(double t){
       double pX = origin.getX() + direction.getX()*t;
       double pY = origin.getY() + direction.getY()*t;
       double pZ = origin.getZ() + direction.getZ()*t;
       return new Point(pX, pY, pZ);
    }
}
