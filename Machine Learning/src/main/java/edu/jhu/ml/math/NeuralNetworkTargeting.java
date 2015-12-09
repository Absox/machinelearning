package edu.jhu.ml.math;

import edu.jhu.ml.model.Target;
import edu.jhu.ml.model.Turret;

import java.util.Observable;

/**
 * Neural network learner.
 * Created by Ran on 11/23/2015.
 */
public class NeuralNetworkTargeting implements TargetingAlgorithm {

    private SingleHiddenLayerNeuralNetwork neuralNetwork;

    /**
     * Current turret location.
     */
    private Turret turret;
    /**
     * Current target location.
     */
    private Target target;

    public void initialize(Object arg) {
        // TODO
    }

    /**
     * Generates a firing solution based on the current state.
     * @return A firing solution.
     */
    public FiringSolution fire() {
        return null;
        // TODO
    }

    /**
     * Updates when the field model updates.
     * @param o Model we're observing.
     * @param arg Arguments passed from model.
     */
    public void update(Observable o, Object arg) {
        // TODO
    }
}
