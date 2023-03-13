package src.main.chapter4;

import main.Canvas;
import main.Colour;
import main.Matrix;
import main.Point;
import main.Tuple;

public class clock {
    public static void main(String[] args) {

        Canvas c = new Canvas(500, 500);
        Colour red = new Colour(1, 0, 0);
        //centre 255, 255
        Point twelve = new Point(0,1,0);
        double radius = (double)3/8;
        double x, y;

        for(int hour=1; hour<13; hour++){
            Matrix rotation = new Matrix().rotationZ(hour*(Math.PI/6));
            Tuple mark = rotation.multiplyTuple(twelve); 
            x = mark.getX()* (radius*500)+250;
            y = mark.getY()* (radius*500)+250;

            c.writePixel((int)x, (int)y, red);
            c.writePixel((int)x+1, (int)y+1, red);
            c.writePixel((int)x+1, (int)y, red);
            c.writePixel((int)x, (int)y+1, red);
        }

        String output = c.canvasToPPM();
        String filePath = "C:/Users/bpgre/Documents/Programming/Java/RayTracerChallenge/projects/images/imageSource/Chapter4Image.ppm";
        c.writeOutputFile(output, filePath);
    }

}