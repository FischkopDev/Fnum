package de.home_skrobanek.fnum.matrix;

import de.home_skrobanek.fnum.exception.SameDimensionsException;

public class MatrixOperations {

    public static Matrix makeIdentity(Matrix A){
        assert A != null;

        if(A.getColumnCount() != A.getRowCount())
            throw new SameDimensionsException();

        for(int x = 0; x < A.getColumnCount(); x++){
            for(int y = 0; y < A.getRowCount(); y++){
                if(x==y)
                    A.setEntry(x,y, 1);
                else
                    A.setEntry(x, y, 0);
            }
        }
        return A;
    }

    public static Matrix makeIdentity(int n, int m){
        Matrix A = new Matrix(n, m);
        for(int x = 0; x < A.getColumnCount(); x++){
            for(int y = 0; y < A.getRowCount(); y++){
                if(x==y)
                    A.setEntry(x,y, 1);
                else
                    A.setEntry(x, y, 0);
            }
        }
        return A;
    }

    public static Matrix addMatrix(Matrix A, Matrix B){
        assert A.getRowCount() == B.getRowCount() && A.getColumnCount() == B.getColumnCount();

        Matrix solution = new Matrix(A.getColumnCount(), A.getRowCount());
        for(int x = 0; x < A.getColumnCount(); x++){
            for(int y = 0; y < A.getRowCount(); y++){
                solution.setEntry(x, y, A.getEntry(x, y)+B.getEntry(x,y));
            }
        }

        return solution;
    }

    public static Matrix multiplyMatrix(Matrix A, Matrix B){
        if((A.getRowCount() == B.getColumnCount() && A.getColumnCount() == B.getRowCount())!= true)
            throw new SameDimensionsException();

        Matrix solution = new Matrix(B.getColumnCount(), A.getRowCount());

        return null; //TODO
    }

    //TODO LR/LU

    //TODO CHOLESKY

    //TODO Ax=b


}
