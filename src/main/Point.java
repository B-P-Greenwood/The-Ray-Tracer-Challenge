package main;

public class Point extends Tuple {

    public Point(double x, double y, double z) {
        super(x, y, z, 1);
    }

    public Point(Tuple t) {
        super(t.getX(), t.getY(), t.getZ(), 1.0);
    }
}
