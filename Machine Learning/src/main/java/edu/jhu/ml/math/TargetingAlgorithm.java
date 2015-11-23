package edu.jhu.ml.math;

import java.util.Observer;

/**
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

}
