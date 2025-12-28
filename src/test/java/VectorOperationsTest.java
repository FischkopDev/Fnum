import static org.junit.jupiter.api.Assertions.*;

import de.home_skrobanek.fnum.matrix.VectorOperations;
import de.home_skrobanek.fnum.vector.Vector;
import org.junit.jupiter.api.Test;

public class VectorOperationsTest {

    private static final double TOL = 1e-9;

    // --------------------------------------------------
    // Euclidean norm (L2)
    // --------------------------------------------------

    @Test
    void euclideanNormOfZeroVectorIsZero() {
        Vector v = new Vector(new double[]{0, 0, 0});
        assertEquals(0.0, VectorOperations.euclideanNorm(v), TOL);
    }

    @Test
    void euclideanNormOfUnitVector() {
        Vector v = new Vector(new double[]{1, 0, 0});
        assertEquals(1.0, VectorOperations.euclideanNorm(v), TOL);
    }

    @Test
    void euclideanNormSimpleVector() {
        Vector v = new Vector(new double[]{3, 4});
        assertEquals(5.0, VectorOperations.euclideanNorm(v), TOL);
    }

    @Test
    void euclideanNormWithNegativeEntries() {
        Vector v = new Vector(new double[]{-3, -4});
        assertEquals(5.0, VectorOperations.euclideanNorm(v), TOL);
    }

    // --------------------------------------------------
    // Maximum norm (infinity norm)
    // --------------------------------------------------

    @Test
    void maxNormOfZeroVectorIsZero() {
        Vector v = new Vector(new double[]{0, 0, 0});
        assertEquals(0.0, VectorOperations.maxNorm(v), TOL);
    }

    @Test
    void maxNormSimpleVector() {
        Vector v = new Vector(new double[]{1, -5, 3});
        assertEquals(5.0, VectorOperations.maxNorm(v), TOL);
    }

    @Test
    void maxNormSingleElement() {
        Vector v = new Vector(new double[]{-7});
        assertEquals(7.0, VectorOperations.maxNorm(v), TOL);
    }

    // --------------------------------------------------
    // L1 norm (Manhattan norm)
    // --------------------------------------------------

    @Test
    void l1NormOfZeroVectorIsZero() {
        Vector v = new Vector(new double[]{0, 0, 0});
        assertEquals(0.0, VectorOperations.l1Norm(v), TOL);
    }

    @Test
    void l1NormSimpleVector() {
        Vector v = new Vector(new double[]{1, -2, 3});
        assertEquals(6.0, VectorOperations.l1Norm(v), TOL);
    }

    @Test
    void l1NormSingleElement() {
        Vector v = new Vector(new double[]{-4});
        assertEquals(4.0, VectorOperations.l1Norm(v), TOL);
    }

    // --------------------------------------------------
    // Norm inequalities
    // --------------------------------------------------

    @Test
    void normInequalityHolds() {
        Vector v = new Vector(new double[]{1, -2, 3});

        double l1 = VectorOperations.l1Norm(v);
        double l2 = VectorOperations.euclideanNorm(v);
        double linf = VectorOperations.maxNorm(v);

        assertTrue(linf <= l2 + TOL);
        assertTrue(l2 <= l1 + TOL);
    }
}
