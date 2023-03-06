package main;

public class Canvas{
    private final int width, height;
    private Colour[][] canvas;

    public Canvas(int width, int height){
        this.width = width;
        this.height = height;
        this.canvas = new Colour[width][height];
        createBlankCanvas();
    }

    private void createBlankCanvas(){

        for (int i = 0; i < width; i++) {
            Colour c = new Colour(0, 0, 0);
            for (int j = 0; j < height; j++){ 
                canvas[i][j] = c;
            }
        }
    }

    public int getWidth(){
        return width;
    }    

    public int getHeight(){
        return height;
    }

    public void writePixel(int x, int y, Colour c){
        if (x >= canvas.length || y >= canvas[0].length || x < 0 || y < 0) {
            System.out.println("error");
        }else{
        canvas[x][y] = c;
        }
    }

    public Colour getPixelAt(int x, int y){
        return canvas[x][y];
    }

    private static int normalizeColourValue(double value){
        if (value <= 0) return 0;
        else if (value >= 1) return 255;
        else return (int) Math.round(value * 255);
    }

    public String canvasToPPM(){
        StringBuilder result = new StringBuilder("P3\n");
        String s = String.format("%d %d\n255",width,height);
        result.append(s);

        for (int i = 0; i < height; i++) {
            result.append("\n");
            int widthCount = 0; 
            for (int j = 0; j < width; j++) {

                Colour c = canvas[j][i];
                int r,g,b;

                r = normalizeColourValue(c.getRed());
                g = normalizeColourValue(c.getGreen());
                b = normalizeColourValue(c.getBlue());

                result.append(r);
                widthCount += 4;
                if (widthCount >= 67) {
                    result.append("\n");
                    widthCount = 0;
                }else{
                    result.append(" ");
                }
                result.append(g);
                widthCount += 4;
                if (widthCount >= 67) {
                    result.append("\n");
                    widthCount = 0;
                }else{
                    result.append(" ");
                }
                result.append(b);
                widthCount += 4;
                if (j < (width -1)) result.append(" ");
            }
        }
        result.append("\n");
        return result.toString();
    }
}
