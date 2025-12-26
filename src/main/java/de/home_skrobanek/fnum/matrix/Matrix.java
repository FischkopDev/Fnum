package de.home_skrobanek.fnum.matrix;

/**
 * @class Matrix
 * @brief Represents a dense real-valued matrix and provides basic operations.
 *
 * This class implements a simple matrix abstraction backed by a
 * two-dimensional {@code double} array. It supports element access
 * and computation of the determinant for square matrices.
 *
 * <p><b>Note:</b> The determinant is computed recursively using
 * Laplace expansion, which has factorial complexity and is therefore
 * only suitable for small matrices.</p>
 *
 * <p>All indices are zero-based.</p>
 */
public class Matrix {

    /**
     * Internal storage of matrix entries.
     */
    private double[][] A;

    /**
     * Number of rows.
     */
    private int n;

    /**
     * Number of columns.
     */
    private int m;

    /**
     * Constructs a matrix with the given dimensions.
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
     * Sets the value of a matrix entry.
     *
     * @param x Row index (zero-based)
     * @param y Column index (zero-based)
     * @param value Value to be stored at position (x, y)
     *
     * @pre x >= 0
     * @pre y >= 0
     */
    public void addEntry(int x, int y, double value){
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

    /**
     * Computes the determinant of this matrix.
     *
     * @return Determinant of the matrix
     *
     * @throws IllegalArgumentException if the matrix is not square
     */
    public double getDet(){
        return getDet(A);
    }

    /**
     * Recursively computes the determinant of a square matrix using
     * Laplace expansion along the first row.
     *
     * @param A Square matrix
     * @return Determinant of {@code A}
     *
     * @throws IllegalArgumentException if the matrix is not square
     *
     * @note Time complexity is O(n!), suitable only for small matrices.
     */
    private double getDet(double[][] A){
        if(n != m)
            throw new IllegalArgumentException("Length of matrix has to be equals to height");

        if(n == 1)
            return A[0][0];
        else if(n == 2){
            return A[0][0] * A[1][1] - A[0][1] * A[1][0];
        }

        double determinant = 0.0;

        for (int j = 0; j < n; j++) {
            determinant += Math.pow(-1, j) * A[0][j] * getDet(minor(A, 0, j));
        }

        return determinant;
    }

    /**
     * Computes the minor matrix obtained by removing a specific row
     * and column.
     *
     * @param matrix Original square matrix
     * @param row Row index to remove
     * @param col Column index to remove
     * @return The resulting minor matrix
     */
    private double[][] minor(double[][] matrix, int row, int col) {
        int n = matrix.length;
        double[][] minor = new double[n - 1][n - 1];

        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }

        return minor;
    }
}
