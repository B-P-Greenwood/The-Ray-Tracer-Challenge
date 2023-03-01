package main;

public class Vector extends Tuple {

    public Vector(double x, double y, double z) {
        super(x, y, z, 0);
    }
    public Vector(Tuple t) {
        super(t.getX(), t.getY(), t.getZ(), 0);
    }
}
