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
    //    String output = c.canvasToPPM();
    //    writeOutputFile(output);
    }

    private static Projectile tick(Environment env, Projectile proj) {
        Tuple position =  proj.position.add(proj.velocity);
        Tuple velocity =  proj.velocity.add(env.gravity).add(env.wind);
        
        return new Projectile(position, velocity); 
    }

    private static void writeOutputFile(String output) {
        System.out.println("here");
        try {
            FileWriter fw = new FileWriter("C:/Users/bpgre/Documents/Programming/Java/RayTracerChallenge/projects/images/imageSource/Chapter2Image.ppm");
            System.out.println("file created");
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
/*
Chapter 2 output
let p = new Projectile(point(100, 1, 0), vector(4, 10, 0));
let e = new Environment(vector(0, -0.1, 0), vector(-0.01, 0, 0));
import { canvas, canvasToPPM, writePixel, color } from './canvas.js';
import fs from 'fs';
let ticks = 0;
let c = canvas(900, 550);
let red = color(1, 0, 0);
while (p.position[1] >= 0) {
  writePixel(
    c,
    Math.round(p.position[0]),
    c.length - 1 - Math.round(p.position[1]),
    red
  );
  p = tick(e, p);
  ticks++;
}
const output = canvasToPPM(c);
fs.writeFile('./Created Images/canvasImage.ppm', output, function (err) {
  if (err) throw err;
  console.log('Saved!');
});
/*
import { canvas, canvasToPPM, writePixel, color } from './canvas.js';
let c = canvas(5, 3);
const c1 = color(1.5, 0, 0);
const c2 = color(0, 0.5, 0);
const c3 = color(-0.5, 0, 1);
c = writePixel(c, 0, 0, c1);
c = writePixel(c, 2, 1, c2);
c = writePixel(c, 4, 2, c3);
const actual = canvasToPPM(c);
console.log(actual);
*/