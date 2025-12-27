import static org.junit.jupiter.api.Assertions.*;

import de.home_skrobanek.fnum.matrix.Matrix;
import org.junit.jupiter.api.Test;

public class MatrixTest {

    @Test
    void testConstructorAndGetters() {
        Matrix m = new Matrix(2, 3);

        assertEquals(3, m.getColumnCount());
        assertEquals(2, m.getRowCount());
    }

    @Test
    void testAddAndGetEntry() {
        Matrix m = new Matrix(2, 2);
        m.setEntry(0, 0, 1.5);
        m.setEntry(1, 1, -2.0);

        assertEquals(1.5, m.getEntry(0, 0));
        assertEquals(-2.0, m.getEntry(1, 1));
    }

    @Test
    void testDeterminant1x1() {
        Matrix m = new Matrix(1, 1);
        m.setEntry(0, 0, 5.0);

        assertEquals(5.0, m.getDet());
    }

    @Test
    void testDeterminant2x2() {
        Matrix m = new Matrix(2, 2);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 2);
        m.setEntry(1, 0, 3);
        m.setEntry(1, 1, 4);

        assertEquals(-2.0, m.getDet());
    }

    @Test
    void testDeterminant3x3() {
        Matrix m = new Matrix(3, 3);
        m.setEntry(0, 0, 6);
        m.setEntry(0, 1, 1);
        m.setEntry(0, 2, 1);
        m.setEntry(1, 0, 4);
        m.setEntry(1, 1, -2);
        m.setEntry(1, 2, 5);
        m.setEntry(2, 0, 2);
        m.setEntry(2, 1, 8);
        m.setEntry(2, 2, 7);

        assertEquals(-306.0, m.getDet());
    }

    @Test
    void testDeterminantNonSquareMatrixThrowsException() {
        Matrix m = new Matrix(2, 3);
        assertThrows(IllegalArgumentException.class, m::getDet);
    }

    @Test
    void testScale() {
        Matrix m = new Matrix(2, 2);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 2);
        m.setEntry(1, 0, 3);
        m.setEntry(1, 1, 4);

        m.scale(2);

        assertEquals(2, m.getEntry(0, 0));
        assertEquals(4, m.getEntry(0, 1));
        assertEquals(6, m.getEntry(1, 0));
        assertEquals(8, m.getEntry(1, 1));
    }

    @Test
    void testTransposeSquareMatrix() {
        Matrix m = new Matrix(2, 2);
        m.setEntry(0, 1, 3);
        m.setEntry(1, 0, 4);

        m.transpose();

        assertEquals(3, m.getEntry(1, 0));
        assertEquals(4, m.getEntry(0, 1));
    }

    @Test
    void testInverseSingularMatrixThrowsException() {
        Matrix m = new Matrix(2, 2);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 2);
        m.setEntry(1, 0, 2);
        m.setEntry(1, 1, 4);

        assertThrows(ArithmeticException.class, m::inverse);
    }

    @Test
    void testInverseNonSquareMatrixThrowsException() {
        Matrix m = new Matrix(2, 3);
        assertThrows(IllegalArgumentException.class, m::inverse);
    }
}

