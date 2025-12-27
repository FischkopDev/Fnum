import static org.junit.jupiter.api.Assertions.*;

import de.home_skrobanek.fnum.ieee.FloatingPoint;
import org.junit.jupiter.api.Test;

public class FloatingPointTest {

    @Test
    void testConstructorValidInput() {
        double[] m = {1, 2, 3};
        double[] e = {1, 0};

        FloatingPoint fp = new FloatingPoint(
                (short) 1,
                m,
                10,
                (short) 1,
                e
        );

        assertNotNull(fp);
    }

    @Test
    void testGetCompleteMantissaPositive() {
        // m = 1.23 in base 10
        double[] m = {1, 2, 3};
        double[] e = {0};

        FloatingPoint fp = new FloatingPoint(
                (short) 1,
                m,
                10,
                (short) 1,
                e
        );

        double result = fp.getCompleteM();
        assertEquals(0.23, result, 1e-10);
    }

    @Test
    void testGetCompleteMantissaNegative() {
        double[] m = {1, 5};
        double[] e = {0};

        FloatingPoint fp = new FloatingPoint(
                (short) -1,
                m,
                10,
                (short) 1,
                e
        );

        assertEquals(-0.5, fp.getCompleteM(), 1e-10);
    }

    @Test
    void testGetCompleteExponentPositive() {
        // exponent = 2*10^0 + 1*10^1 = 12
        double[] m = {1};
        double[] e = {2, 1};

        FloatingPoint fp = new FloatingPoint(
                (short) 1,
                m,
                10,
                (short) 1,
                e
        );

        assertEquals(12.0, fp.getCompleteExp(), 1e-10);
    }

    @Test
    void testGetCompleteExponentNegative() {
        double[] m = {1};
        double[] e = {3};

        FloatingPoint fp = new FloatingPoint(
                (short) 1,
                m,
                10,
                (short) -1,
                e
        );

        assertEquals(-3.0, fp.getCompleteExp(), 1e-10);
    }

    @Test
    void testGetFloatValueSimple() {
        // Value: 0.5 * 10^1 = 5
        double[] m = {1, 5};
        double[] e = {1};

        FloatingPoint fp = new FloatingPoint(
                (short) 1,
                m,
                10,
                (short) 1,
                e
        );

        assertEquals(5.0, fp.getFloatValue(), 1e-10);
    }

    @Test
    void testGetFloatValueNegative() {
        // Value: -0.25 * 2^2 = -1
        double[] m = {1, 0, 1};
        double[] e = {2};

        FloatingPoint fp = new FloatingPoint(
                (short) -1,
                m,
                2,
                (short) 1,
                e
        );

        assertEquals(-1.0, fp.getFloatValue(), 1e-10);
    }

    @Test
    void testConstructorInvalidBase() {
        double[] m = {1};
        double[] e = {1};

        assertThrows(
                AssertionError.class,
                () -> new FloatingPoint((short) 1, m, 1, (short) 1, e)
        );
    }

    @Test
    void testConstructorInvalidMantissaNormalization() {
        double[] m = {0, 1};
        double[] e = {1};

        assertThrows(
                AssertionError.class,
                () -> new FloatingPoint((short) 1, m, 10, (short) 1, e)
        );
    }

    @Test
    void testConstructorInvalidSign() {
        double[] m = {1};
        double[] e = {1};

        assertThrows(
                AssertionError.class,
                () -> new FloatingPoint((short) 0, m, 10, (short) 1, e)
        );
    }

    @Test
    void testConstructorInvalidExponentSign() {
        double[] m = {1};
        double[] e = {1};

        assertThrows(
                AssertionError.class,
                () -> new FloatingPoint((short) 1, m, 10, (short) 0, e)
        );
    }
}
