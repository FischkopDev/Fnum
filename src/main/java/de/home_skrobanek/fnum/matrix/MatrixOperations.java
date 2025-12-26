package de.home_skrobanek.fnum.matrix;

import de.home_skrobanek.fnum.exception.SameDimensionsException;

public class MatrixOperations {

    public static Matrix makeIdentity(Matrix A){
        assert A != null;

        if(A.getWidth() != A.getHeight())
            throw new SameDimensionsException();

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
        assert A.getHeight() == B.getHeight() && A.getWidth() == B.getWidth();

        Matrix solution = new Matrix(A.getWidth(), A.getHeight());
        for(int x = 0; x < A.getWidth(); x++){
            for(int y = 0; y < A.getHeight(); y++){
                solution.addEntry(x, y, A.getEntry(x, y)+B.getEntry(x,y));
            }
        }

        return solution;
    }

    public static Matrix multiplyMatrix(Matrix A, Matrix B){
        return null; //TODO
    }

}
