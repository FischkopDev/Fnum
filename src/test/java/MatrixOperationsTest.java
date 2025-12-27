import static org.junit.jupiter.api.Assertions.*;

import de.home_skrobanek.fnum.exception.SameDimensionsException;
import de.home_skrobanek.fnum.matrix.MatrixOperations;
import de.home_skrobanek.fnum.matrix.Matrix;
import org.junit.jupiter.api.Test;

public class MatrixOperationsTest {

    @Test
    void testMakeIdentitySquareMatrix() {
        Matrix m = new Matrix(3, 3);
        MatrixOperations.makeIdentity(m);

        // Check identity properties
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    assertEquals(1.0, m.getEntry(i, j), "Diagonal should be 1");
                } else {
                    assertEquals(0.0, m.getEntry(i, j), "Off-diagonal should be 0");
                }
            }
        }
    }

    @Test
    void testMakeIdentityNonSquareMatrix() {
        Matrix m = new Matrix(2, 3);
        assertThrowsExactly(SameDimensionsException.class, () -> MatrixOperations.makeIdentity(m));
    }


    @Test
    void testAddMatrix() {
        Matrix A = new Matrix(2, 2);
        A.setEntry(0, 0, 1);
        A.setEntry(0, 1, 2);
        A.setEntry(1, 0, 3);
        A.setEntry(1, 1, 4);

        Matrix B = new Matrix(2, 2);
        B.setEntry(0, 0, 5);
        B.setEntry(0, 1, 6);
        B.setEntry(1, 0, 7);
        B.setEntry(1, 1, 8);

        Matrix solution = MatrixOperations.addMatrix(A, B);

        assertNotNull(solution, "Solution should not be null");
        assertEquals(6, solution.getEntry(0, 0));
        assertEquals(8, solution.getEntry(0, 1));
        assertEquals(10, solution.getEntry(1, 0));
        assertEquals(12, solution.getEntry(1, 1));
    }

    @Test
    void testAddMatrixDifferentDimensionsShouldFail() {
        Matrix A = new Matrix(2, 2);
        Matrix B = new Matrix(3, 2);

        // Expect an assertion error due to dimension mismatch
        assertThrows(AssertionError.class, () -> MatrixOperations.addMatrix(A, B));
    }

    @Test
    void testMultiplyMatrixPlaceholder() {
        Matrix A = new Matrix(2, 3);
        Matrix B = new Matrix(3, 2);

        // Since multiplyMatrix is not implemented, it should return null
        Matrix result = MatrixOperations.multiplyMatrix(A, B);
        assertNull(result, "multiplyMatrix currently returns null");
    }
}
