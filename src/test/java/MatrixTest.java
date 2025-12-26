import de.home_skrobanek.fnum.matrix.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixTest {

    @Test
    public void testAddEntry(){
        Matrix A = new Matrix(3, 3);
        A.addEntry(0, 0, 3.14);

        assertEquals(A.getEntry(0, 0), 3.14);
    }

    @Test
    public void testDeterminant(){
        Matrix A = new Matrix(3, 3);

    }
}
