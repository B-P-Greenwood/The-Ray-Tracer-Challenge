package main;

public class Matrix {
    private final int width, height;
    private double[][] matrix;
    private final double margin = 0.0000001;

    public Matrix(int width, int height, double[][] values){
        this.width = width;
        this.height = height; 
        this.matrix = values;
    }

    public Matrix(int width, int height){
        this.width = width; 
        this.height = height;
        this.matrix = new double[width][height];    
    } 

    public Matrix(){
        this.width = 4; 
        this.height = 4;
        this.matrix = identityMatrix();    
    } 

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public double getItem(int x, int y){
        return matrix[x][y];
    }

    public void setItem(int x, int y, double item){
        matrix[x][y] = item;
    }

    public void setColumn(int column, double[] values){
        matrix[column] = values;
    }

    public double[][] getMatrix(){
        return matrix;
    }

    private static double[][] identityMatrix(){
        return new double[][] {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
    }

    public boolean compareMatrix(double[][] b){ 
        if(b.length == width){
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++){ 
                    if ((matrix[i][j] + b[i][j]) /2 > (matrix[i][j]+margin)) return false;
                }
            }
        }
        return true;
    }

    public Matrix multiplyMatrix(double [][] b){

        Matrix result = new Matrix(width, height);

        for (int i = 0; i < width; i++) {
          double[] inner = new double[height];
          for (int j = 0; j < height; j++) {
            inner[j] = (matrix[i][0] * b[0][j] +
                matrix[i][1] * b[1][j] +
                matrix[i][2] * b[2][j] +
                matrix[i][3] * b[3][j]);
          }
          result.setColumn(i, inner);
        }
        return result;
    }

    private double tuplePart(Tuple tuple, int index){

        return matrix[index][0] * tuple.getX() +
        matrix[index][1] * tuple.getY() +
        matrix[index][2] * tuple.getZ() +
        matrix[index][3] * tuple.getW();
    }

    public Tuple multiplyTuple(Tuple tuple){

        double x = tuplePart(tuple, 0);
        double y = tuplePart(tuple, 1);
        double z = tuplePart(tuple, 2);
        double w = tuplePart(tuple, 3);

        return new Tuple(x, y, z, w);
    }

    public Matrix transposeMatrix(){

        Matrix result = new Matrix();
        for (int i = 0; i < width; i++) {
            double[] inner = new double[height];
          for (int j = 0; j < height; j++) {
            inner[j]= matrix[j][i];
          }
          result.setColumn(i, inner);
        }
        return result;
    }

    public double determinant(){
        double result =0;
        if(width == 2){
            result = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }else {
            for (int i = 0; i < width; i++) {
                result += matrix[0][i] * cofactor(0, i);
              }
        }
        return result;
    }

    public Matrix subMatrix(int row, int column){
        int size = width - 1;
        Matrix result = new Matrix(size, size);
        int columnCount = 0;
        for (int i = 0; i < width; i++) {
            if(i != row){
                double[]inner = new double[size];
                int rowCount = 0;
                for(int j = 0; j < height; j++) {
                    if(j != column){
                        inner[rowCount] = matrix[i][j];
                        rowCount++;
                    }
                }
                result.setColumn(columnCount, inner);
                columnCount++;
            }            
        }
        return result;
    }

    public double minor(int row, int column){
        return subMatrix(row, column).determinant();
    }

    public double cofactor(int row, int column){
        double comparison = minor(row, column);
        if ((row + column) % 2 == 0) return comparison;
        else return -comparison;
    }

    public boolean invertible(){
        return determinant() != 0 ? true : false;
    }

    public Matrix createInverseMatrix(){
        Matrix result = new Matrix(width, height);
        double det = determinant();
        for (int i = 0; i < width; i++) {
          for (int j = 0; j < height; j++) {
            double item = this.cofactor(i, j);
            result.setItem(j,i, (item / det)); 
          }
        }
        return result;
    }
}