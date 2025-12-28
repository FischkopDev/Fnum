package de.home_skrobanek.fnum.matrix;

import de.home_skrobanek.fnum.exception.SameDimensionsException;
/**
 * @class MatrixOperations
 * @brief Provides static utility methods for common matrix operations.
 *
 * This class contains only static methods and is not intended to be instantiated.
 * It offers basic operations such as creating identity matrices, matrix addition,
 * and matrix multiplication.
 *
 * All operations assume zero-based indexing.
 */
public class MatrixOperations {

    /**
     * Converts a given square matrix into an identity matrix in-place.
     *
     * All diagonal entries are set to 1, all off-diagonal entries to 0.
     * The provided matrix is modified directly.
     *
     * @param A Square matrix to be transformed into an identity matrix
     * @return The modified matrix {@code A}
     *
     * @throws SameDimensionsException if the matrix is not square
     *
     * @pre A != null
     * @pre A must be square
     */
    public static Matrix makeIdentity(Matrix A){
        assert A != null;

        if(A.getColumnCount() != A.getRowCount())
            throw new SameDimensionsException();

        for(int x = 0; x < A.getColumnCount(); x++){
            for(int y = 0; y < A.getRowCount(); y++){
                if(x == y)
                    A.setEntry(x, y, 1);
                else
                    A.setEntry(x, y, 0);
            }
        }
        return A;
    }

    /**
     * Creates a new identity matrix with the given dimensions.
     *
     * Only square identity matrices are mathematically meaningful.
     * If {@code n != m}, the diagonal will be set up to {@code min(n, m)}.
     *
     * @param n Number of rows
     * @param m Number of columns
     * @return Newly created identity matrix
     *
     * @pre n > 0
     * @pre m > 0
     */
    public static Matrix makeIdentity(int n, int m){
        Matrix A = new Matrix(n, m);

        for(int x = 0; x < A.getColumnCount(); x++){
            for(int y = 0; y < A.getRowCount(); y++){
                if(x == y)
                    A.setEntry(x, y, 1);
                else
                    A.setEntry(x, y, 0);
            }
        }
        return A;
    }

    /**
     * Computes the sum of two matrices.
     *
     * Both matrices must have the same dimensions.
     * The original matrices are not modified.
     *
     * @param A First matrix
     * @param B Second matrix
     * @return Matrix representing {@code A + B}
     *
     * @throws AssertionError if the dimensions do not match (when assertions are enabled)
     *
     * @pre A and B must have the same number of rows and columns
     */
    public static Matrix addMatrix(Matrix A, Matrix B){
        assert A.getRowCount() == B.getRowCount()
                && A.getColumnCount() == B.getColumnCount();

        Matrix solution = new Matrix(A.getColumnCount(), A.getRowCount());

        for(int x = 0; x < A.getColumnCount(); x++){
            for(int y = 0; y < A.getRowCount(); y++){
                solution.setEntry(x, y,
                        A.getEntry(x, y) + B.getEntry(x, y));
            }
        }
        return solution;
    }

    /**
     * Computes the matrix product {@code A * B}.
     *
     * Matrix multiplication is defined only if the number of columns of {@code A}
     * equals the number of rows of {@code B}.
     *
     * @param A Left matrix
     * @param B Right matrix
     * @return Resulting matrix {@code A * B}
     *
     * @throws SameDimensionsException if the matrices are not compatible for multiplication
     *
     * @pre A.getColumnCount() == B.getRowCount()
     */
    public static Matrix multiplyMatrix(Matrix A, Matrix B){
        if((A.getRowCount() == B.getColumnCount() && A.getColumnCount() == B.getRowCount()) != true)
            throw new SameDimensionsException();

        Matrix solution = new Matrix(B.getColumnCount(), A.getRowCount());

        // TODO: implement matrix multiplication
        return solution;
    }


    //TODO CHOLESKY

    //TODO Ax=b


}
