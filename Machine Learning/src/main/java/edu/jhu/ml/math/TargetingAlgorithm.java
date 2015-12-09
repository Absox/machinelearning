package edu.jhu.ml.math;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Observer;

/**
 * Targeting algorithm interface.
 * Created by Ran on 11/23/2015.
 */
public interface TargetingAlgorithm extends Observer {

    /**
     * Initializes the targeting algorithm.
     * @param arg Parameters with which to initialize.
     */
    void initialize(Object arg);

    /**
     * Returns the current firing solution.
     * @return Current firing solution.
     */
    FiringSolution fire();

    /**
     * Computes the 2-dimensional rotation matrix, given an angle.
     * @param angle Angle in radians.
     * @return Rotation matrix.
     */
    public static RealMatrix rotationMatrix(double angle) {
        double[][] values = {{Math.cos(angle), -Math.sin(angle)}, {Math.sin(angle), Math.cos(angle)}};
        return new Array2DRowRealMatrix(values);
    }

}
