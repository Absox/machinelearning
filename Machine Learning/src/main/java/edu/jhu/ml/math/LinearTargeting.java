package edu.jhu.ml.math;

import edu.jhu.ml.model.OneTurretOneTargetModel;
import edu.jhu.ml.model.Target;
import edu.jhu.ml.model.Turret;

import java.util.Observable;

/**
 * Linear targeting algorithm.
 * Created by Ran on 11/23/2015.
 */
public class LinearTargeting implements TargetingAlgorithm {

    private Target target;
    private Turret turret;

    /**
     * Initializes the targeting algorithm.
     * @param arg Parameters with which to initialize.
     */
    public void initialize(Object arg) {
        // Initialize target and turret.
        if (arg instanceof OneTurretOneTargetModel) {
            OneTurretOneTargetModel model = (OneTurretOneTargetModel)arg;
            this.target = model.getTarget();
            this.turret = model.getTurret();
        }
    }

    /**
     * Generates a firing solution.
     * @return
     */
    public FiringSolution fire() {
        return null; // TODO
    }

    /**
     * Updates the state of the targeting algorithm.
     * @param o FieldModel that we're observing.
     * @param arg Arguments passed to notify();
     */
    public void update(Observable o, Object arg) {
        if (o instanceof OneTurretOneTargetModel) {
            OneTurretOneTargetModel model = (OneTurretOneTargetModel)o;
            this.target = model.getTarget();
            this.turret = model.getTurret();
        }
    }
}
