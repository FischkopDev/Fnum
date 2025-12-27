package de.home_skrobanek.fnum.exception;
/**
 * @class SameDimensionsException
 * @brief Exception thrown when two matrices or vectors do not have the same dimensions.
 *
 * This exception is part of the JavaNumerics library and is used to indicate
 * that an operation requiring matching dimensions cannot be performed.
 *
 * Example usage:
 * @code
 * if (matrixA.getWidth() != matrixB.getWidth() || matrixA.getHeight() != matrixB.getHeight()) {
 *     throw new SameDimensionsException();
 * }
 * @endcode
 */
public class SameDimensionsException extends RuntimeException {

    /**
     * Constructs a new SameDimensionsException with a default error message.
     *
     * The default message is:
     * "The given Matrix or Vector's do not have the same dimension. They can't be used in this operation"
     */
    public SameDimensionsException() {
        super("The given Matrix or Vector's do not have the same dimension. They can't be used in this operation");
    }
}
