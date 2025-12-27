package de.home_skrobanek.fnum.matrix;

import de.home_skrobanek.fnum.vector.Vector;
/**
 * @class LUDecomposition
 * @brief Computes the LU (or LR) decomposition of a square matrix.
 *
 * This class decomposes a square matrix A into a product of a lower
 * triangular matrix L with unit diagonal and an upper triangular matrix R
 * such that A = L * R.
 *
 * <p>It also provides a method to solve linear systems of equations
 * using the computed decomposition.</p>
 *
 * <p>Note: This implementation does not include pivoting. For matrices
 * that are nearly singular, small pivot thresholds must be provided.</p>
 */
public class LUDecomposition {

    /** Original matrix to decompose */
    private Matrix A;

    /** Lower triangular matrix with unit diagonal */
    private Matrix L;

    /** Upper triangular matrix */
    private Matrix R;

    /**
     * Constructs the LUDecomposition object for a given square matrix.
     *
     * Initializes L as the identity matrix and R as a zero matrix.
     *
     * @param A Square matrix to decompose
     *
     * @throws IllegalArgumentException if the matrix is not square
     */
    public LUDecomposition(Matrix A){
        this.A = A;

        if(!A.isSquare())
            throw new IllegalArgumentException("The matrix has to be square");

        L = MatrixOperations.makeIdentity(A.getColumnCount(), A.getRowCount());
        R = new Matrix(A.getRowCount(), A.getRowCount(), 0);
    }

    /**
     * Executes the LU decomposition algorithm.
     *
     * After execution, {@link #getL()} and {@link #getR()} return
     * the decomposition matrices such that A = L * R.
     *
     * @param abs Minimum allowed pivot value. If any diagonal entry of R
     *            is smaller in absolute value than this threshold,
     *            the matrix is considered singular.
     *
     * @throws ArithmeticException if a pivot element is below the threshold
     */
    public void execute(double abs) {
        int n = A.getRowCount();

        for (int k = 0; k < n; k++) {

            // Compute k-th row of R
            for (int j = k; j < n; j++) {
                double sum = 0.0;
                for (int s = 0; s < k; s++) {
                    sum += L.getEntry(k, s) * R.getEntry(s, j);
                }
                R.setEntry(k, j, A.getEntry(k, j) - sum);
            }

            if (Math.abs(R.getEntry(k, k)) < abs)
                throw new ArithmeticException("Matrix is singular");

            // Compute k-th column of L
            for (int i = k + 1; i < n; i++) {
                double sum = 0.0;
                for (int s = 0; s < k; s++) {
                    sum += L.getEntry(i, s) * R.getEntry(s, k);
                }
                L.setEntry(i, k, (A.getEntry(i, k) - sum) / R.getEntry(k, k));
            }
        }
    }

    /**
     * Returns the lower triangular matrix L from the decomposition.
     *
     * L has unit diagonal and lower-triangular entries below the diagonal.
     *
     * @return Lower triangular matrix L
     */
    public Matrix getL(){
        return L;
    }

    /**
     * Returns the upper triangular matrix R from the decomposition.
     *
     * @return Upper triangular matrix R
     */
    public Matrix getR(){
        return R;
    }

    /**
     *
     * @return determinant of R by multiplying the elements of the diagonal
     */
    public double getDeterminantR(){
        double tmp = 0;

        for(int i = 0; i < R.getRowCount(); i++)
            tmp *= R.getEntry(i,i);

        return tmp;
    }

    /**
     * Solves the linear system A * x = b using the computed decomposition.
     *
     * Performs forward and backward substitution:
     *   1. Solve L * y = b (forward substitution)
     *   2. Solve R * x = y (backward substitution)
     *
     * @param b Right-hand side vector
     * @return Solution vector x
     *
     * @throws IllegalStateException if decomposition has not been executed
     * @throws IllegalArgumentException if the dimension of b does not match A
     */
    public Vector solve(Vector b){
        return null; // TODO: implement forward/backward substitution
    }
}
