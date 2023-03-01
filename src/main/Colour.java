package main;

public class Colour {
    private double red,green,blue;


    public Colour(double red, double green, double blue){
       this.red = red;
       this.green = green;
       this.blue = blue; 
    }

    public double getRed(){
        return this.red;
    }

    public double getBlue(){
        return this.blue;
    }

    public double getGreen(){
        return this.green;
    }

    public Colour add(Colour b) {
        return new Colour(this.red + b.getRed(), this.green + b.getGreen(), this.blue + b.getBlue());
    }
    
    public Colour subtract(Colour b) {
        return new Colour(this.red - b.getRed(), this.green - b.getGreen(), this.blue - b.getBlue());
    }

    public Colour scalar(double scalar) {
        return new Colour(this.red * scalar, this.green * scalar, this.blue * scalar);
    }

    public boolean equals(Colour c) {
        double Epsilon = 0.00001;
        return c.getRed() - this.red < Epsilon && c.getBlue() - this.blue < Epsilon && c.getGreen() - this.green < Epsilon;
    }

    public Colour hadamardProduct(Colour b) {
        return new Colour(this.red * b.getRed(), this.green * b.getGreen(), this.blue * b.getBlue() );
    }

}
