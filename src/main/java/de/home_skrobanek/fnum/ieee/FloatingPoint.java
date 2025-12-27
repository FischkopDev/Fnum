package de.home_skrobanek.fnum.ieee;
/**
 * @class FloatingPoint
 * @brief Represents a custom floating-point number in a positional numeral system.
 *
 * This class models a floating-point number of the form:
 *
 * <pre>
 *   x = sign · m · β^e
 * </pre>
 *
 * where:
 * - {@code β} is the base (radix),
 * - {@code m} is the mantissa represented as an array of digits,
 * - {@code e} is the exponent represented as an array of digits,
 * - {@code sign} and {@code signExp} define the signs of mantissa and exponent.
 *
 * The representation is intended for educational and numerical analysis purposes,
 * such as studying floating-point arithmetic and rounding behavior.
 */
public class FloatingPoint {

    /**
     * Base (radix) of the number system.
     */
    private int beta;

    /**
     * Length of the mantissa.
     */
    private int r;

    /**
     * Length of the exponent (excluding sign).
     */
    private int s;

    /**
     * Sign of the mantissa (+1 or -1).
     */
    private short sign;

    /**
     * Sign of the exponent (+1 or -1).
     */
    private short signExp;

    /**
     * Mantissa digits.
     *
     * The first digit must be non-zero to ensure a normalized representation.
     */
    private double[] m;

    /**
     * Exponent digits.
     */
    private double[] exp;

    /**
     * Constructs a custom floating-point number.
     *
     * @param sign Sign of the mantissa (+1 or -1)
     * @param m Mantissa digits (normalized, m[0] != 0)
     * @param beta Base (radix), must be >= 2
     * @param signExp Sign of the exponent (+1 or -1)
     * @param exp Exponent digits
     *
     * @pre beta >= 2
     * @pre m.length > 0
     * @pre exp.length > 0
     * @pre m[0] != 0
     * @pre sign == 1 || sign == -1
     * @pre signExp == 1 || signExp == -1
     */
    public FloatingPoint(short sign, double[] m, int beta, short signExp, double[] exp) {
        assert beta >= 2 && m.length > 0 && exp.length > 0;
        assert sign == 1 || sign == -1;
        assert signExp == 1 || signExp == -1;
        assert m[0] != 0;

        this.m = m;
        this.exp = exp;
        this.sign = sign;
        this.beta = beta;
        this.signExp = signExp;

        // Set representation limits
        this.r = m.length;
        this.s = exp.length - 1;
    }

    /**
     * Computes the complete mantissa value.
     *
     * The mantissa is evaluated as:
     *
     * <pre>
     *   sign · Σ(m_i · β^{-i}),  i = 1 ... r-1
     * </pre>
     *
     * @return The evaluated mantissa including its sign
     */
    public double getCompleteM() {
        double m_delta = 0.0;

        for (int i = 1; i < r; i++) {
            m_delta += m[i] * Math.pow(beta, -i);
        }

        return m_delta * sign;
    }

    /**
     * Computes the complete exponent value.
     *
     * The exponent is evaluated as:
     *
     * <pre>
     *   signExp · Σ(exp_i · β^{i}),  i = 0 ... s
     * </pre>
     *
     * @return The evaluated exponent including its sign
     */
    public double getCompleteExp() {
        double exp_delta = 0.0;

        for (int i = 0; i <= s; i++) {
            exp_delta += exp[i] * Math.pow(beta, i);
        }

        return signExp * exp_delta;
    }

    /**
     * Computes the real value represented by this floating-point number.
     *
     * @return Floating-point value as a {@code double}
     */
    public double getFloatValue() {
        return getCompleteM() * Math.pow(beta, getCompleteExp());
    }
}
