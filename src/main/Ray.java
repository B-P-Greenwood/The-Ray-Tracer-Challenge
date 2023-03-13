package main;

public class Ray {
    private final Tuple origin; 
    private final Tuple direction;

    public Ray(Tuple origin, Tuple direction){
        this.origin = origin;
        this.direction = direction; 
    }

    public Tuple getOrigin(){
        return origin;
    }

    public Tuple getDirection(){
        return direction;
    }

    public Point position(double t){
       double pX = origin.getX() + direction.getX()*t;
       double pY = origin.getY() + direction.getY()*t;
       double pZ = origin.getZ() + direction.getZ()*t;
       return new Point(pX, pY, pZ);
    }

    public Ray transform(Matrix m){
        Tuple newD = m.multiplyTuple(direction);
        Tuple newO = m.multiplyTuple(origin);
        return new Ray(newO, newD);
    }

}
