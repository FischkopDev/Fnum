package de.home_skrobanek.fnum.math;

import de.home_skrobanek.fnum.matrix.Matrix;

public class MatrixOperations {

    public static Matrix makeIdentity(Matrix A){
        assert A != null;

        for(int x = 0; x < A.getWidth(); x++){
            for(int y = 0; y < A.getHeight(); y++){
                if(x==y)
                    A.addEntry(x,y, 1);
                else
                    A.addEntry(x, y, 0);
            }
        }
        return A;
    }

    public static Matrix addMatrix(Matrix A, Matrix B){
        return null; //TODO
    }

    public static Matrix multiplyMatrix(Matrix A, Matrix B){
        return null; //TODO
    }

}
