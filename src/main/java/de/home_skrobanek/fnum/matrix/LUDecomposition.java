package de.home_skrobanek.fnum.matrix;

import de.home_skrobanek.fnum.vector.Vector;

public class LUDecomposition {

    private Matrix A;
    public LUDecomposition(Matrix A){
        this.A = A;

        if(!A.isSquare())
            throw new IllegalArgumentException("The matrix has to be square");

    }

    public void execute(){

    }

    public Matrix getL(){
        return null;
    }

    public Matrix getR(){
        return null;
    }

    public Vector solve(Vector b){
        return null;
    }
}
