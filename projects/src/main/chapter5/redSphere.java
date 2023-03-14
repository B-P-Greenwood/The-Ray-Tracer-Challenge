package src.main.chapter5;

import main.*;
import main.shapes.Sphere;

public class redSphere {

    static int wallZ = 10;
    static double wallSize = 7.0;
    static int canvasPixels = 100; 
    static double pixelSize = wallSize / (double) canvasPixels; 
    static double half = wallSize / 2;
    static Canvas canvas = new Canvas(canvasPixels, canvasPixels); 
    static Colour red = new Colour(1, 0, 0);
    static Sphere s = new Sphere();

    static Point origin = new Point(0, 0, -5);

    public static void main(String[] args) {

        for(int y = 0; y < canvasPixels - 1; y++){
            
            double worldY = half - pixelSize * y;
            
            for (int x = 0; x < canvasPixels - 1; x++){
                
                double worldX = -half + pixelSize * x;

                Point position = new Point(worldX, worldY, wallZ);
                Ray ray = new Ray(origin, position.subtract(origin).normalize());
                Intersections xs = s.getIntersections(ray);

                if (xs.hit()!= null) canvas.writePixel(x, y, red);
            }
        }

        String output = canvas.canvasToPPM();
        String filePath = "C:/Users/bpgre/Documents/Programming/Java/RayTracerChallenge/projects/images/imageSource/Chapter5Image.ppm";
        canvas.writeOutputFile(output, filePath);
    }

}
