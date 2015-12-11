package edu.jhu.ml.math;

import edu.jhu.ml.model.FieldModel;
import edu.jhu.ml.model.OneTurretOneTargetModel;
import edu.jhu.ml.model.Target;
import edu.jhu.ml.model.Turret;
import org.apache.commons.math3.linear.RealVector;

import java.util.Observable;

/**
 * Linear targeting algorithm.
 * Created by Ran on 11/23/2015.
 */
public class LinearTargeting implements TargetingAlgorithm {

    private Target target;
    private Turret turret;
    private FieldModel model;

    /**
     * Default constructor.
     */
    public LinearTargeting() {
        // Nothing needs doing.
    }

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
            this.model = model;
        }
    }

    /**
     * Generates a firing solution.
     * @return Firing solution.
     */
    public FiringSolution fire() {
        FiringSolution result = new FiringSolution(turret, target);

        RealVector relativePosition = turret.getRelativePosition(target);
        RealVector relativeVelocity = turret.getRelativeVelocity(target);

        double a = Math.pow(relativeVelocity.getNorm(), 2) - Math.pow(model.getProjectileSpeed(), 2);
        double b = 2 * relativePosition.getEntry(0) * relativeVelocity.getEntry(0) + 2 * relativePosition.getEntry(1) * relativeVelocity.getEntry(1);
        double c = Math.pow(relativePosition.getNorm(), 2);

        double t1 = (-b + Math.sqrt(b * b - 4 * a * c))/(2 * a);
        double t2 = (-b - Math.sqrt(b * b - 4 * a * c))/(2 * a);

        double interceptTime = 0;
        if (t2 > 0) {
            interceptTime = t2;
        } else {
            interceptTime = t1;
        }
        RealVector s = relativePosition.mapDivide(interceptTime).add(relativeVelocity);
        double offset = Math.atan2(s.getEntry(1), s.getEntry(0));
        result.setOffsetRadians(offset);

        return result;
    }

    /**
     * Updates the state of the targeting algorithm.
     * @param o FieldModel that we're observing.
     * @param arg Arguments passed to notify();
     */
    public void update(Observable o, Object arg) {
       // Nothing needs doing.
    }
}
