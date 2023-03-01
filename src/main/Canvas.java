package main;

public class Canvas{
    private final int width, height;
    private Colour[][] canvas;

    public Canvas(int width, int height){
        this.width = width;
        this.height = height;
        this.canvas = new Colour[width][height];
    }

    public int getWidth(){
        return width;
    }    

    public int getHeight(){
        return this.height;
    }

    public void writePixel(int width, int height, Colour c){
        this.canvas[width][height] = c;
    }

    public Colour pixelAt(int width, int height){
        return this.canvas[width][height];
    }

    public String canvasToPPM(){
       String result = "P3\n";
       return result.concat(Integer.toString(this.width) + " " + Integer.toString(this.height) + "\n255"); 

    }
}
