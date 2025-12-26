package de.home_skrobanek.fnum.vector;

import de.home_skrobanek.fnum.exception.SameDimensionsException;
/**
 * @class Vector
 * @brief Represents a dense real-valued vector and provides basic vector operations.
 *
 * This class implements a simple vector abstraction backed by a
 * one-dimensional {@code double} array. It supports element access,
 * scalar multiplication, vector addition, and dot product computation.
 *
 * <p>All indices are zero-based.</p>
 */
public class Vector {

    /**
     * Internal storage of vector entries.
     */
    private double[] values;

    /**
     * Constructs a vector initialized with the given values.
     *
     * @param values Variable-length list of initial vector entries
     */
    public Vector(double... values) {
        this.values = values;
    }

    /**
     * Constructs a vector of a given size.
     *
     * All entries are initialized to zero.
     *
     * @param size Size (dimension) of the vector
     *
     * @pre size > 0
     */
    public Vector(int size) {
        values = new double[size];
    }

    /**
     * Sets the value of a vector entry.
     *
     * @param pos Index of the entry (zero-based)
     * @param value Value to store at the given position
     *
     * @pre pos >= 0
     */
    public void addValue(int pos, double value) {
        assert pos >= 0;
        values[pos] = value;
    }

    /**
     * Returns the value of a vector entry.
     *
     * @param pos Index of the entry (zero-based)
     * @return Value stored at position {@code pos}
     *
     * @pre pos >= 0
     */
    public double getValue(int pos) {
        assert pos >= 0;
        return values[pos];
    }

    /**
     * Multiplies this vector by a scalar value in-place.
     *
     * Each entry of the vector is multiplied by {@code scalar}.
     *
     * @param scalar Scalar factor
     */
    public void multiplyScalar(double scalar) {
        for (int i = 0; i < values.length; i++)
            values[i] *= scalar;
    }

    /**
     * Adds another vector to this vector in-place.
     *
     * Both vectors must have the same dimension.
     *
     * @param vec Vector to be added
     * @throws SameDimensionsException if the vectors do not have the same size
     */
    public void addVector(Vector vec) {
        if (this.size() != vec.size()) {
            throw new SameDimensionsException();
        }

        for (int i = 0; i < this.size(); i++)
            addValue(i, this.getValue(i) + vec.getValue(i));
    }

    /**
     * Computes the dot product (scalar product) of this vector and another vector.
     *
     * Both vectors must have the same dimension.
     *
     * @param vec Vector to multiply with
     * @return Dot product of the two vectors
     * @throws SameDimensionsException if the vectors do not have the same size
     */
    public double multiplyVector(Vector vec) {
        if (this.size() != vec.size()) {
            throw new SameDimensionsException();
        }

        double tmp = 0;
        for (int i = 0; i < size(); i++) {
            tmp += getValue(i) * vec.getValue(i);
        }
        return tmp;
    }

    /**
     * Returns the dimension of this vector.
     *
     * @return Number of entries in the vector
     */
    public int size() {
        return values.length;
    }
}
