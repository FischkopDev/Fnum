package de.home_skrobanek.fnum.matrix;

import de.home_skrobanek.fnum.vector.Vector;
/**
 * @class VectorOperations
 * @brief Provides static utility methods for common vector norm computations.
 *
 * This class contains only static methods and is not intended to be instantiated.
 * It offers commonly used vector norms such as the Euclidean norm,
 * the maximum norm, and the L1 norm.
 *
 * All vectors are assumed to be real-valued and indexed from zero.
 */
public class VectorOperations {

    /**
     * Computes the Euclidean norm (L2 norm) of a vector.
     *
     * The Euclidean norm is defined as:
     * \f[
     * \|v\|_2 = \sqrt{\sum_{i=0}^{n-1} |v_i|^2}
     * \f]
     *
     * @param vec Input vector
     * @return Euclidean norm of the vector
     *
     * @pre vec != null
     */
    public static double euclideanNorm(Vector vec){
        double tmp = 0.0;

        for(int i = 0; i < vec.size(); i++){
            tmp += Math.pow(Math.abs(vec.getValue(i)), 2);
        }

        return Math.sqrt(tmp);
    }

    /**
     * Computes the maximum norm (infinity norm) of a vector.
     *
     * The maximum norm is defined as:
     * \f[
     * \|v\|_{\infty} = \max_{0 \le i < n} |v_i|
     * \f]
     *
     * @param vec Input vector
     * @return Maximum absolute value of the vector entries
     *
     * @pre vec != null
     */
    public static double maxNorm(Vector vec){
        double tmp = 0.0;

        for(int i = 0; i < vec.size(); i++){
            double value = Math.abs(vec.getValue(i));
            if(tmp < value)
                tmp = value;
        }
        return tmp;
    }

    /**
     * Computes the L1 norm (Manhattan norm) of a vector.
     *
     * The L1 norm is defined as:
     * \f[
     * \|v\|_1 = \sum_{i=0}^{n-1} |v_i|
     * \f]
     *
     * @param vec Input vector
     * @return Sum of absolute values of the vector entries
     *
     * @pre vec != null
     */
    public static double l1Norm(Vector vec){
        double tmp = 0.0;

        for(int i = 0; i < vec.size(); i++){
            tmp += Math.abs(vec.getValue(i));
        }

        return tmp;
    }
}
