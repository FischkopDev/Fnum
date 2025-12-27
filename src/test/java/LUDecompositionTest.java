import static org.junit.jupiter.api.Assertions.*;

import de.home_skrobanek.fnum.matrix.LUDecomposition;
import de.home_skrobanek.fnum.matrix.Matrix;
import de.home_skrobanek.fnum.matrix.MatrixOperations;
import org.junit.jupiter.api.Test;

public class LUDecompositionTest {

    private static final double TOL = 1e-9;

    @Test
    void constructorRejectsNonSquareMatrix() {
        Matrix A = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        assertThrows(IllegalArgumentException.class,
                () -> new LUDecomposition(A));
    }

    @Test
    void simpleLUDecomposition2x2() {
        Matrix A = new Matrix(new double[][]{
                {4, 3},
                {6, 3}
        });

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute(TOL);

        Matrix L = lu.getL();
        Matrix R = lu.getR();

        Matrix expectedL = new Matrix(new double[][]{
                {1, 0},
                {1.5, 1}
        });

        Matrix expectedR = new Matrix(new double[][]{
                {4, 3},
                {0, -1.5}
        });

        assertTrue(L.equals(expectedL, TOL));
        assertTrue(R.equals(expectedR, TOL));
    }

    @Test
    void reconstructionMatchesOriginalMatrix() {
        Matrix A = new Matrix(new double[][]{
                {2, 1, 1},
                {4, -6, 0},
                {-2, 7, 2}
        });

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute(TOL);

        Matrix reconstructed = lu.getL().multiply(lu.getR());

        assertTrue(A.equals(reconstructed, TOL));
    }
    @Test
    void identityMatrix() {
        Matrix A = MatrixOperations.makeIdentity(3, 3);

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute(TOL);

        assertTrue(lu.getL().equals(A, TOL));
        assertTrue(lu.getR().equals(A, TOL));
    }

    @Test
    void upperTriangularMatrix() {
        Matrix A = new Matrix(new double[][]{
                {2, -1, 3},
                {0, 4, 5},
                {0, 0, 6}
        });

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute(TOL);

        Matrix expectedL = MatrixOperations.makeIdentity(3, 3);

        assertTrue(lu.getL().equals(expectedL, TOL));
        assertTrue(lu.getR().equals(A, TOL));
    }

    @Test
    void singularMatrixThrowsException() {
        Matrix A = new Matrix(new double[][]{
                {1, 2},
                {2, 4}
        });

        LUDecomposition lu = new LUDecomposition(A);

        assertThrows(ArithmeticException.class,
                () -> lu.execute(TOL));
    }

    @Test
    void nearlySingularMatrixDetectedByThreshold() {
        Matrix A = new Matrix(new double[][]{
                {1e-12, 1},
                {1,     1}
        });

        LUDecomposition lu = new LUDecomposition(A);

        assertThrows(ArithmeticException.class,
                () -> lu.execute(1e-10));
    }

    @Test
    void lHasUnitDiagonal() {
        Matrix A = new Matrix(new double[][]{
                {5, 2, 1},
                {10, 9, 4},
                {15, 3, 8}
        });

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute(TOL);

        Matrix L = lu.getL();

        for (int i = 0; i < 3; i++) {
            assertEquals(1.0, L.getEntry(i, i), TOL);
        }
    }

    @Test
    void rIsUpperTriangular() {
        Matrix A = new Matrix(new double[][]{
                {3, 1, 2},
                {6, 5, 4},
                {9, 8, 7}
        });

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute(TOL);

        Matrix R = lu.getR();

        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < i; j++) {
                assertEquals(0.0, R.getEntry(i, j), TOL);
            }
        }
    }
}
