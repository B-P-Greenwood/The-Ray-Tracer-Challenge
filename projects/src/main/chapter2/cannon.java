package src.main.chapter2;
import main.Vector;
import src.main.chapter1.Environment;
import src.main.chapter1.Projectile;
import main.Canvas;
import main.Point;
import main.Tuple;
import main.Colour;

import java.io.FileWriter;  
import java.io.IOException;  

public class cannon {
    public static void main(String[] args) {
        Projectile proj = new Projectile(new Point(100, 1, 0), new Vector(4, 10, 0));
        Environment env = new Environment(new Vector(0, -0.1, 0), new Vector(-0.01, 0, 0));
        Canvas c = new Canvas(900, 550);
        Colour red = new Colour(1, 0, 0);

        while (proj.position.getY() >= 0) {
            c.writePixel((int)Math.round(proj.position.getX()), (int)(c.getHeight() -1 - Math.round(proj.position.getY())),red);
            proj = tick(env, proj);
        }
        String output = c.canvasToPPM();
        writeOutputFile(output);
    }

    private static Projectile tick(Environment env, Projectile proj) {
        Tuple position =  proj.position.add(proj.velocity);
        Tuple velocity =  proj.velocity.add(env.gravity).add(env.wind);
        
        return new Projectile(position, velocity); 
    }

    private static void writeOutputFile(String output) {
        try {
            FileWriter fw = new FileWriter("C:/Users/bpgre/Documents/Programming/Java/RayTracerChallenge/projects/images/imageSource/Chapter2Image.ppm");
            for (int i = 0; i < output.length(); i++){
                fw.write(output.charAt(i));
            }
            System.out.println("Successfully written");
            fw.close();
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }
}