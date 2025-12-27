import static org.junit.jupiter.api.Assertions.*;

import de.home_skrobanek.fnum.matrix.LUDecomposition;
import de.home_skrobanek.fnum.matrix.Matrix;
import de.home_skrobanek.fnum.vector.Vector;
import org.junit.jupiter.api.Test;

public class LUDecompositionTest {

    private static final double TOL = 1e-9;

    // --------------------------------------------------
    // Konstruktor-Tests
    // --------------------------------------------------

    @Test
    void constructorRejectsNonSquareMatrix() {
        Matrix A = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        assertThrows(IllegalArgumentException.class,
                () -> new LUDecomposition(A));
    }

    // --------------------------------------------------
    // Zugriff vor execute()
    // --------------------------------------------------

    @Test
    void getLBeforeExecuteThrows() {
        Matrix A = new Matrix(new double[][]{
                {1, 0},
                {0, 1}
        });

        LUDecomposition lu = new LUDecomposition(A);

        assertThrows(IllegalStateException.class, lu::getL);
    }

    @Test
    void getRBeforeExecuteThrows() {
        Matrix A = new Matrix(new double[][]{
                {1, 0},
                {0, 1}
        });

        LUDecomposition lu = new LUDecomposition(A);

        assertThrows(IllegalStateException.class, lu::getR);
    }

    @Test
    void solveBeforeExecuteThrows() {
        Matrix A = new Matrix(new double[][]{
                {1, 0},
                {0, 1}
        });

        Vector b = new Vector(new double[]{1, 1});

        LUDecomposition lu = new LUDecomposition(A);

        assertThrows(IllegalStateException.class, () -> lu.solve(b));
    }

    // --------------------------------------------------
    // Korrektheit der Zerlegung
    // --------------------------------------------------

    @Test
    void simpleLUDecompositionWorks() {
        Matrix A = new Matrix(new double[][]{
                {4, 3},
                {6, 3}
        });

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute();

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

        //TODO
       // assertTrue(L.equals(expectedL, TOL));
        //assertTrue(R.equals(expectedR, TOL));
    }

    @Test
    void reconstructionMatchesOriginalMatrix() {
        Matrix A = new Matrix(new double[][]{
                {2, 1, 1},
                {4, -6, 0},
                {-2, 7, 2}
        });

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute();

        Matrix reconstructed = lu.getL().multiply(lu.getR());

        //TODO
        //assertTrue(A.equals(reconstructed, TOL));
    }

    // --------------------------------------------------
    // solve(b)
    // --------------------------------------------------

    @Test
    void solveLinearSystem() {
        Matrix A = new Matrix(new double[][]{
                {3, 1},
                {1, 2}
        });

        Vector b = new Vector(new double[]{9, 8});

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute();

        Vector x = lu.solve(b);

        Vector expected = new Vector(new double[]{2, 3});

        assertTrue(x.equals(expected, TOL));
    }

    @Test
    void solveRejectsWrongDimensionVector() {
        Matrix A = new Matrix(new double[][]{
                {1, 2},
                {3, 4}
        });

        Vector b = new Vector(new double[]{1, 2, 3});

        LUDecomposition lu = new LUDecomposition(A);
        lu.execute();

        assertThrows(IllegalArgumentException.class, () -> lu.solve(b));
    }
}
