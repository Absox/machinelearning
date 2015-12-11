package edu.jhu.ml.math;

import java.awt.*;
import java.util.Observable;

/**
 * Direct fire targeting fires directly at the current location of the target.
 * Created by Ran on 11/24/2015.
 */
public class DirectFireTargeting implements TargetingAlgorithm {

    /**
     * Initializes the targeting algorithm.
     * @param arg Parameters with which to initialize.
     */
    public void initialize(Object arg) {
        // Do nothing.
    }

    /**
     * Gets the firing solution.
     * @return Firing solution.
     */
    public FiringSolution fire() {
        // Return a constant 0 for offset.
        return new FiringSolution();
    }

    /**
     * Updates the state of the targeting algorithm.
     * @param o Model which we're watching.
     * @param arg Arguments to update the state with.
     */
    public void update(Observable o, Object arg) {
        // Do nothing.
    }

}
