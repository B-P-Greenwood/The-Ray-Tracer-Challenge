package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import main.Matrix;
import main.Tuple;

public class MatrixTest {
    
    @Test 
    public void constructingAndInspectingA4x4MatrixTest(){
        double[][] array = {{1, 2, 3, 4}, {5.5, 6.5, 7.5, 8.5}, {9, 10, 11, 12}, {13.5, 14.5, 15.5, 16.5}};
        Matrix m = new Matrix(4,4,array);

        assertAll(
            () -> assertEquals(1, m.getItem(0, 0)),
            () -> assertEquals(4, m.getItem(0, 3)),
            () -> assertEquals(5.5, m.getItem(1, 0)),
            () -> assertEquals(7.5, m.getItem(1, 2)),
            () -> assertEquals(11, m.getItem(2, 2)),
            () -> assertEquals(13.5, m.getItem(3, 0)),
            () -> assertEquals(15.5, m.getItem(3, 2))
        );
    }

    @Test 
    public void comparingTwoIdenticalMatrixTest(){
        double[][] array1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        double[][] array2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        Matrix m1 = new Matrix(4,4,array1);
        Matrix m2 = new Matrix(4,4,array2);

        assertTrue(m1.compareMatrix(m2.getMatrix()));
    }

    @Test 
    public void comparingTwoDifferentMatrixTest(){
        double[][] array1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        double[][] array2 = {{1, 2, 3, 4}, {2, 3, 7, 8}, {9, 10, 11, 1}, {13, 18, 15, 16}};
        Matrix m1 = new Matrix(4,4,array1);
        Matrix m2 = new Matrix(4,4,array2);

        assertFalse(m1.compareMatrix(m2.getMatrix()));
    }

    @Test 
    public void multiplyingTwoMatrixTest(){
        double[][] array1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 8, 7, 6}, {5, 4, 3, 2}};
        double[][] array2 = {{-2, 1, 2, 3}, {3, 2, 1, -1}, {4, 3, 6, 5}, {1, 2, 7, 8}};
        double[][] result = {{20, 22, 50, 48}, {44, 54, 114, 108}, {40, 58, 110, 102},{16, 26, 46, 42}};
        Matrix m1 = new Matrix(4,4,array1);
        Matrix m2 = new Matrix(4,4,array2);
        Matrix actual = m1.multiplyMatrix(m2.getMatrix());

        assertTrue(actual.compareMatrix(result));
    }

    @Test 
    public void multiplyingATupleAndAMatrixTest(){
        double[][] array1 = {{1, 2, 3, 4}, {2, 4, 4, 2}, {8, 6, 4, 1}, {0, 0, 0, 1}};
        Tuple t = new Tuple(1, 2, 3, 1);
        Matrix m1 = new Matrix(4,4,array1);
        Tuple actual = m1.multiplyTuple(t);

        assertAll(
            () -> assertEquals(actual.getX(), 18),
            () -> assertEquals(actual.getY(), 24),
            () -> assertEquals(actual.getZ(), 33),
            () -> assertEquals(actual.getW(), 1)
        );
    }

    @Test 
    public void multiplyingAMatrixByTheIdentityMatrixTest(){
        double[][] array1 = {{0, 1, 2, 4}, {1, 2, 4, 8}, {2, 4, 8, 16}, {4, 8, 16, 32}};
        Matrix m1 = new Matrix(4,4,array1);
        Matrix actual = m1.multiplyMatrix(new Matrix().getMatrix());

        assertAll(
            () -> assertTrue(m1.compareMatrix(actual.getMatrix()))
        );
    }

    @Test 
    public void multiplyingATupleByTheIdentityMatrixTest(){
        Tuple t = new Tuple(1, 2, 3, 4);
        Matrix m1 = new Matrix();
        Tuple actual = m1.multiplyTuple(t);

        assertAll(
            () -> assertEquals(actual.getX(), 1),
            () -> assertEquals(actual.getY(), 2),
            () -> assertEquals(actual.getZ(), 3),
            () -> assertEquals(actual.getW(), 4)
        );
    }

    @Test 
    public void transposingAMatrixTest(){
        double[][] array1 = {{0, 9, 3, 0}, {9, 8, 0, 8}, {1, 8, 5, 3}, {0, 0, 5, 8}};
        double[][] actual = {{0, 9, 1, 0}, {9, 8, 8, 0}, {3, 0, 5, 5}, {0, 8, 3, 8}};
        Matrix m = new Matrix(4, 4, array1);

        assertAll(
            () -> assertTrue(m.transposeMatrix().compareMatrix(actual))
        );
    }

    @Test 
    public void transposingTheIdentityMatrixTest(){
        Matrix identity = new Matrix();


        assertAll(
            () -> assertTrue(identity.transposeMatrix().compareMatrix(identity.getMatrix()))
        );
    }

    @Test 
    public void calculatingTheDeterminantOfA2x2MatrixTest(){
        Matrix m = new Matrix(2, 2, new double[][] {{1, 5}, {-3, 2}});
        
        assertAll(
            () -> assertEquals(17, (int)m.determinant())
        );
    }

    @Test 
    public void aSubMatrixOfA3x3MatrixTest(){
        double[][] array1 = new double[][] {{1, 5, 0}, {-3, 2, 7}, {0, 6, -3}};
        Matrix m = new Matrix(3, 3, array1);
        Matrix sub = m.subMatrix(0, 2);
        
        assertAll(
            () -> assertEquals(-3,sub.getItem(0, 0)), 
            () -> assertEquals(2, sub.getItem(0, 1)), 
            () -> assertEquals(0, sub.getItem(1, 0)), 
            () -> assertEquals(6, sub.getItem(1, 1))  
        );
    }

    @Test 
    public void aSubMatrixOfAA4x4MatrixTest(){

        double [][] array = new double[][] {{-6, 1, 1, 6},{-8, 5, 8, 6}, {-1, 0, 8, 2}, {-7, 1, -1, 1}};
        double [][] array2 = new double[][] {{-6, 1, 6}, {-8, 8, 6}, {-7, -1, 1}};
        Matrix m1 = new Matrix(4, 4, array);
        
        assertAll(
            () -> assertTrue(m1.subMatrix(2, 1).compareMatrix(array2))
        );
    }

    @Test 
    public void calculatingAMinorOfA3x3MatrixTest(){

        double [][] array = new double[][] {{3, 5, 0}, {2, -1, -7}, {6, -1, 5}};
        Matrix m1 = new Matrix(3, 3, array);
        Matrix sub = m1.subMatrix(1, 0);

        
        assertAll(
            () -> assertEquals(25, (int) sub.determinant()),
            () -> assertEquals(25, (int) m1.minor(1,0))
        );
    }

    @Test 
    public void calculatingACofactorOfA3x3MAtrixTest(){

        double [][] array = new double[][] {{3, 5, 0}, {2, -1, -7}, {6, -1, 5}};
        Matrix m1 = new Matrix(3, 3, array);
        
        assertAll(
            () -> assertEquals(-12, (int) m1.minor(0,0)),
            () -> assertEquals(-12, m1.cofactor(0,0)),
            () -> assertEquals(25, (int) m1.minor(1,0)),
            () -> assertEquals(-25, m1.cofactor(1,0))
        );
    }

    @Test 
    public void calculatingTheDeterminantOfA3x3MAtrixTest(){

        double [][] array = new double[][] {{1, 2, 6}, {-5, 8, -4}, {2, 6, 4}};
        Matrix m1 = new Matrix(3, 3, array);
        
        assertAll(
            () -> assertEquals(56, (int) m1.cofactor(0,0)),
            () -> assertEquals(12, m1.cofactor(0,1)),
            () -> assertEquals(-46, (int) m1.cofactor(0,2)),
            () -> assertEquals(-196, m1.determinant())
        );
    }

    @Test 
    public void calculatingTheDeterminantOfA4x4MAtrixTest(){

        double [][] array = new double[][] {{-2, -8, 3, 5}, {-3, 1, 7, 3}, {1, 2, -9, 6}, {-6, 7, 7, -9}};
        Matrix m1 = new Matrix(4, 4, array);
        
        assertAll(
            () -> assertEquals(690, (int) m1.cofactor(0,0)),
            () -> assertEquals(447, m1.cofactor(0,1)),
            () -> assertEquals(210, (int) m1.cofactor(0,2)),
            () -> assertEquals(51, (int) m1.cofactor(0,3)),
            () -> assertEquals(-4071, m1.determinant())
        );
    }

    @Test 
    public void testingAnInvertibleMatrixTest(){

        double [][] array = new double[][] {{6, 4, 4, 4}, {5, 5, 7, 6}, {4, -9, 3, -7}, {9, 1, 7, -6}};
        Matrix m1 = new Matrix(4, 4, array);
        
        assertAll(
            () -> assertTrue(m1.invertible()),
            () -> assertEquals(-2120, m1.determinant())
        );
    }

    @Test 
    public void testingANonInvertibleMatrixTest(){

        double [][] array = new double[][] {{-4, 2, -2, -3}, {9, 6, 2, 6}, {0, -5, 1, -5}, {0, 0, 0, 0}};
        Matrix m1 = new Matrix(4, 4, array);
        
        assertAll(
            () -> assertFalse(m1.invertible())
        );
    }

    @Test 
    public void calculatingTheInverseOfAMatrixTest(){

        double [][] array = new double[][] {{-5, 2, 6, -8}, {1, -5, 1, 8}, {7, 7, -6, -7}, {1, -3, 7, 4}};
        Matrix m1 = new Matrix(4, 4, array);
        Matrix m2 = m1.createInverseMatrix();
        double a1 = (double) -160/532;
        double a2 = (double) 105/532;
        
        assertAll(
            () -> assertEquals(532, (int) m1.determinant()),
            () -> assertEquals(-160, m1.cofactor(2,3)),
            () -> assertEquals(a1, m2.getItem(3,2)),
            () -> assertEquals(105, (int) m1.cofactor(3,2)),
            () -> assertEquals(a2,  m2.getItem(2,3))
        );
    }

    @Test 
    public void multiplyingAProductByItsInverse(){

        double [][] array1 = new double[][] {{3, -9, 7, 3}, {3, -8, 2, -9}, {-4, 4, 4, 1}, {-6, 5, -1, 1}};
        double [][] array2 = new double[][] {{8, 2, 2, 2}, {3, -1, 7, 0}, {7, 0, 5, 4}, {6, -2, 0, 5}};
       
        Matrix a = new Matrix(4, 4, array1);
        Matrix b = new Matrix(4, 4, array2);
        Matrix c = a.multiplyMatrix(b.getMatrix());
        Matrix result =  c.multiplyMatrix(b.createInverseMatrix().getMatrix());

        
        assertAll(
            () -> assertEquals(3,result.getItem(0,0)),
            () -> assertEquals(-9,result.getItem(0,1)),
            () -> assertEquals(7,result.getItem(0,2)),
            () -> assertTrue(result.compareMatrix(a.getMatrix()))
             );
    }
}
