package de.home_skrobanek.fnum.matrix;

import de.home_skrobanek.fnum.vector.Vector;

/**
 * @class Matrix
 * @brief Represents a dense real-valued matrix and provides basic operations.
 *
 * This class implements a simple matrix abstraction backed by a
 * two-dimensional {@code double} array. It supports element access,
 * basic operations, and computation of the determinant for square matrices.
 *
 * <p><b>Note:</b> The determinant is computed recursively using
 * Laplace expansion, which has factorial complexity and is therefore
 * only suitable for small matrices.</p>
 *
 * <p>All indices are zero-based.</p>
 */
public class Matrix {

    /** Internal storage of matrix entries. */
    private double[][] A;

    /** Number of rows. */
    private int n;

    /** Number of columns. */
    private int m;

    /**
     * Constructs a matrix with the given number of rows and columns.
     *
     * All entries are initialized to 0.
     *
     * @param n Number of rows (must be positive)
     * @param m Number of columns (must be positive)
     *
     * @pre n > 0
     * @pre m > 0
     */
    public Matrix(int n, int m){
        assert n > 0 && m > 0;

        this.n = n;
        this.m = m;

        A = new double[n][m];
    }

    /**
     * Constructs a matrix with the given number of rows and columns,
     * and initializes all entries to the given value.
     *
     * @param n Number of rows (must be positive)
     * @param m Number of columns (must be positive)
     * @param value Initial value for all entries
     *
     * @pre n > 0
     * @pre m > 0
     */
    public Matrix(int n, int m, double value){
        assert n > 0 && m > 0;

        this.n = n;
        this.m = m;

        A = new double[n][m];

        for(int x = 0; x < n; x++){
            for(int y = 0; y < m; y++){
                setEntry(x, y, value);
            }
        }
    }

    /**
     * Constructs a matrix from a 2D array.
     *
     * The input array is used directly as internal storage.
     * No deep copy is performed.
     *
     * @param values 2D array representing matrix entries
     * @pre values.length > 0
     * @pre values[0].length > 0
     */
    public Matrix(double[][] values){
        A = values;
        n = values.length;
        m = values[0].length;
    }

    /**
     * Default constructor.
     *
     * Throws an exception because matrix dimensions must be specified.
     *
     * @throws RuntimeException Always, since dimensions are undefined
     */
    public Matrix(){
        throw new RuntimeException("You need to define dimensions for this matrix");
    }


    /**
     * Sets the value of a matrix entry.
     *
     * @param x Row index (zero-based)
     * @param y Column index (zero-based)
     * @param value Value to be stored at position (x, y)
     *
     * @pre x >= 0
     * @pre y >= 0
     */
    public void setEntry(int x, int y, double value){
        assert x >= 0 && y >= 0;

        A[x][y] = value;
    }

    /**
     * Returns the value of a matrix entry.
     *
     * @param x Row index (zero-based)
     * @param y Column index (zero-based)
     * @return Value stored at position (x, y)
     *
     * @pre x >= 0
     * @pre y >= 0
     */
    public double getEntry(int x, int y){
        assert x >= 0 && y >= 0;

        return A[x][y];
    }

    public int getRowCount(){
        return n;
    }

    public int getColumnCount(){
        return m;
    }

    public boolean isSquare(){
        return getColumnCount() == getRowCount();
    }

    /**
     * Recursively computes the determinant of a square matrix using
     * Laplace expansion along the first row.
     *
     * @return Determinant of the matrix
     *
     * @throws IllegalArgumentException if the matrix is not square
     *
     * @note Time complexity is O(n!), suitable only for small matrices.
     */
    public double getDet(){
        return getDet(A);
    }

    /**
     * Compares this matrix to another matrix using a given tolerance.
     *
     * @param other The other matrix to compare
     * @param tol   The maximum allowed difference for each element
     * @return true if all corresponding elements differ by no more than tol
     */
    public boolean equals(Matrix other, double tol) {
        if (other == null)
            return false;

        if (this.getRowCount() != other.getRowCount() || this.getColumnCount() != other.getColumnCount())
            return false;

        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (Math.abs(this.getEntry(i, j) - other.getEntry(i, j)) > tol)
                    return false;
            }
        }
        return true;
    }


    private double getDet(double[][] matrix){
        int n_tmp = matrix.length;
        int m_tmp = matrix[0].length;

        if(n_tmp != m_tmp)
            throw new IllegalArgumentException("Matrix must be square");

        if(n_tmp == 1)
            return matrix[0][0];
        else if(n_tmp == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double determinant = 0.0;

        for (int j = 0; j < n_tmp; j++) {
            determinant += Math.pow(-1, j) * matrix[0][j] * getDet(minor(matrix, 0, j));
        }

        return determinant;
    }

    private double[][] minor(double[][] matrix, int row, int col) {
        int n_tmp = matrix.length;
        double[][] minor = new double[n_tmp - 1][n_tmp - 1];

        int r = 0;
        for (int i = 0; i < n_tmp; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n_tmp; j++) {
                if (j == col) continue;
                minor[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }

        return minor;
    }

    // --------------------------------------------------
    // TODO's
    // --------------------------------------------------

    /**
     * Creates a deep copy of this matrix.
     *
     * Each entry of the original matrix is copied into a new matrix object.
     *
     * @return A new Matrix object with identical entries to this matrix
     */
    public Matrix copy() {
        // TODO: implement copy
        return null;
    }

    /**
     * Multiplies this matrix by another matrix.
     *
     * @param x The matrix to multiply with (this * x)
     * @return A new Matrix containing the result
     *
     * @throws IllegalArgumentException if the number of columns of this matrix
     *         does not equal the number of rows of matrix x
     */
    public Matrix multiply(Matrix x) {
        // TODO: implement matrix multiplication
        return null;
    }

    /**
     * Multiplies this matrix by a vector.
     *
     * Computes the matrix-vector product: result = this * x
     *
     * @param x The vector to multiply
     * @return A new Matrix representing the result (as column matrix)
     *
     * @throws IllegalArgumentException if the number of columns of this matrix
     *         does not equal the size of vector x
     */
    public Matrix multiply(Vector x) {
        // TODO: implement matrix-vector multiplication
        return null;
    }

    /**
     * Transposes this matrix in-place.
     *
     * After execution, rows become columns and columns become rows.
     * The internal storage is updated accordingly.
     *
     * @note For non-square matrices, this will create a new array of dimensions
     *       m x n to hold the transposed values.
     */
    public void transpose() {
        // TODO: implement in-place or reallocation transpose
    }

    /**
     * Scales this matrix by a given factor.
     *
     * Each entry a_ij is multiplied by {@code scaling}.
     *
     * @param scaling Scalar factor
     */
    public void scale(int scaling) {
        // TODO: implement properly
    }

    /**
     * Computes the inverse of this matrix in-place.
     *
     * The matrix must be square and have a non-zero determinant.
     * After execution, this matrix will contain its inverse.
     *
     * @throws IllegalArgumentException if the matrix is not square
     * @throws ArithmeticException if the matrix is singular (determinant = 0)
     *
     * @note Implementation is currently a placeholder; recommended: Gauss-Jordan
     */
    public void inverse() {
        // TODO: implement matrix inversion
    }
}
