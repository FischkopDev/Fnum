package de.home_skrobanek.fnum.exception;
/**
 * @class IllegalMathOperationException
 * @brief Exception thrown when an illegal or unsupported mathematical operation is attempted.
 *
 * This exception is part of the JavaNumerics library and is used to indicate
 * that a requested mathematical operation is not allowed or not implemented
 * for the given inputs.
 *
 * Example usage:
 * @code
 * if (matrix.isSingular()) {
 *     throw new IllegalMathOperationException();
 * }
 * @endcode
 */
public class IllegalMathOperationException extends RuntimeException {

    /**
     * Constructs a new IllegalMathOperationException with a default error message.
     *
     * The default message is:
     * "This operation is illegal or not supported by this library"
     */
    public IllegalMathOperationException() {
        super("This operation is illegal or not supported by this library");
    }
}
