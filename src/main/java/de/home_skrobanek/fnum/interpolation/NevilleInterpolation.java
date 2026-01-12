package de.home_skrobanek.fnum.interpolation;

import de.home_skrobanek.fnum.vector.Vector;
/**
 * @class NevilleInterpolation
 * @brief Implements Neville's method for polynomial interpolation.
 *
 * This class allows computing the interpolated values of a function at a given
 * point using Neville's recursive algorithm. It stores the input points and their
 * corresponding function values.
 */
public class NevilleInterpolation {

    /**
     * @brief Vector containing the input points (x-values) for interpolation.
     */
    private Vector functionInput;

    /**
     * @brief Vector containing the function values (y-values) corresponding to the input points.
     */
    private Vector functionOutput;

    /**
     * @brief Constructor that initializes the interpolation points and computes their function values.
     *
     * @param functionInput A vector of input points (x-values) where the function is evaluated.
     * @param func An implementation of the InterpolationFunction interface to compute function values.
     */
    public NevilleInterpolation(Vector functionInput, InterpolationFunction func){
        this.functionInput = functionInput;

        functionOutput = new Vector(functionInput.size());

        for(int i = 0; i < functionInput.size(); i++){
            functionOutput.setValue(i, func.f(functionInput.getValue(i)));
        }
    }

    /**
     * @brief Computes the interpolated value at a specific point using Neville's algorithm.
     *
     * This method implements Neville's recursive scheme to calculate the polynomial
     * that passes through the given points (data_x, data_y) and evaluates it at appX.
     *
     * @param data_x Vector containing x-values of known points.
     * @param data_y Vector containing y-values of known points.
     * @param appX The x-value at which the interpolation is to be computed.
     * @return Vector containing intermediate results of the Neville algorithm. The first element is the interpolated value.
     *
     * @note The formula inside this method may be incomplete and might require correction:
     *       value = ((appX - data_x[i+k])*p[i] + (data_x[i]-appX)*p[i+1]) / (data_x[i] - data_x[i+k]);
     */
    Vector neville(Vector data_x, Vector data_y, double appX){

        Vector p = new Vector(data_x.size());
        assert data_x.size() == data_y.size() && data_y.size() == p.size();

        for(int i = 0; i < p.size(); i++){
            p.setValue(i, data_y.getValue(i));
        }

        for(int k = 1; k < data_x.size(); k++){
            for(int i = 0; i < data_x.size()-k; i++){
                double value = ((appX - data_x.getValue(i+k)) * p.getValue(i) + (data_x.getValue(i) - appX));
                p.setValue(i, value);
            }
        }
        return p;
    }

    /**
     * @brief Returns the input points used for interpolation.
     *
     * @return Vector of input points (x-values).
     */
    public Vector getFunctionInput() {
        return functionInput;
    }

    /**
     * @brief Sets new input points for interpolation.
     *
     * @param functionInput Vector of new input points (x-values).
     */
    public void setFunctionInput(Vector functionInput) {
        this.functionInput = functionInput;
    }

}
