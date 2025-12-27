import static org.junit.jupiter.api.Assertions.*;

import de.home_skrobanek.fnum.exception.SameDimensionsException;
import de.home_skrobanek.fnum.vector.Vector;
import org.junit.jupiter.api.Test;

public class VectorTest {

    @Test
    void testConstructorWithValues() {
        Vector v = new Vector(1.0, 2.0, 3.0);

        assertEquals(3, v.size());
        assertEquals(1.0, v.getValue(0));
        assertEquals(2.0, v.getValue(1));
        assertEquals(3.0, v.getValue(2));
    }

    @Test
    void testConstructorWithSize() {
        Vector v = new Vector(4);

        assertEquals(4, v.size());
        for (int i = 0; i < v.size(); i++) {
            assertEquals(0.0, v.getValue(i));
        }
    }

    @Test
    void testAddAndGetValue() {
        Vector v = new Vector(3);
        v.addValue(0, 5.0);
        v.addValue(1, -2.0);
        v.addValue(2, 7.5);

        assertEquals(5.0, v.getValue(0));
        assertEquals(-2.0, v.getValue(1));
        assertEquals(7.5, v.getValue(2));
    }

    @Test
    void testMultiplyScalar() {
        Vector v = new Vector(1.0, 2.0, 3.0);
        v.multiplyScalar(2.0);

        assertEquals(2.0, v.getValue(0));
        assertEquals(4.0, v.getValue(1));
        assertEquals(6.0, v.getValue(2));
    }

    @Test
    void testAddVector() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(4.0, 5.0, 6.0);

        v1.addVector(v2);

        assertEquals(5.0, v1.getValue(0));
        assertEquals(7.0, v1.getValue(1));
        assertEquals(9.0, v1.getValue(2));
    }

    @Test
    void testAddVectorDifferentSizesThrowsException() {
        Vector v1 = new Vector(3);
        Vector v2 = new Vector(4);

        assertThrowsExactly(
                SameDimensionsException.class,
                () -> v1.addVector(v2)
        );
    }

    @Test
    void testMultiplyVectorDotProduct() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(4.0, 5.0, 6.0);

        double result = v1.multiplyVector(v2);

        // 1*4 + 2*5 + 3*6 = 32
        assertEquals(32.0, result);
    }

    @Test
    void testMultiplyVectorDifferentSizesThrowsException() {
        Vector v1 = new Vector(2);
        Vector v2 = new Vector(3);

        assertThrowsExactly(
                SameDimensionsException.class,
                () -> v1.multiplyVector(v2)
        );
    }
}
