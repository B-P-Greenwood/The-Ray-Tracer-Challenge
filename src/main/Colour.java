package main;

public class Colour {
    private final double red,green,blue;

    public Colour(double red, double green, double blue){
       this.red = red;
       this.green = green;
       this.blue = blue; 
    }

    public double getRed(){
        return red;
    }

    public double getBlue(){
        return blue;
    }

    public double getGreen(){
        return green;
    }

    public Colour add(Colour b) {
        return new Colour(red + b.getRed(), green + b.getGreen(), blue + b.getBlue());
    }
    
    public Colour subtract(Colour b) {
        return new Colour(red - b.getRed(), green - b.getGreen(), blue - b.getBlue());
    }

    public Colour scalar(double scalar) {
        return new Colour(red * scalar, green * scalar, blue * scalar);
    }

    public boolean equals(Colour c) {
        double Epsilon = 0.00001;
        return c.getRed() - red < Epsilon && c.getBlue() - blue < Epsilon && c.getGreen() - green < Epsilon;
    }

    public Colour hadamardProduct(Colour b) {
        return new Colour(red * b.getRed(), green * b.getGreen(), blue * b.getBlue() );
    }

}
