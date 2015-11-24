package edu.jhu.ml.model;

import edu.jhu.ml.math.TargetingAlgorithm;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

/**
 * A field model with one turret and one target.
 * Created by Ran on 11/23/2015.
 */
public class OneTurretOneTargetModel extends FieldModel {

    private Turret turret;
    private Target target;

    /**
     * Initializer for model.
     */
    public OneTurretOneTargetModel() {
        super();
        double[] values = { 0, 0 };
        this.turret = new Turret(new ArrayRealVector(values));
        this.addTurret(this.turret);
        this.target = new Target();
        this.addTarget(this.target);
    }

    /**
     * Accessor for turret.
     * @return Turret of the model.
     */
    public Turret getTurret() {
        return this.turret;
    }

    /**
     * Accessor for target.
     * @return Target of the model.
     */
    public Target getTarget() {
        return this.target;
    }

    /**
     * Mutator for target position.
     * @param newPosition New position of target.
     */
    public void setTargetPosition(RealVector newPosition) {
        this.target.setPosition(newPosition);
    }

    /**
     * Moves target towards target position.
     * @param newPosition Position to move towards.
     */
    public void moveTargetTowards(RealVector newPosition) {
        this.target.moveTowards(newPosition, this.getTargetSpeed());
    }

    /**
     * Binds a targeting algorithm to the turret.
     * @param a Targeting algorithm for the turret.
     */
    public void bindTargetingAlgorithm(TargetingAlgorithm a) {
        this.addObserver(a);
        this.turret.setTargetingAlgorithm(a);
    }

    /**
     * Advances the model one tick.
     */
    public void advance() {
        this.advanceTargets();
        this.advanceProjectiles();

        this.setChanged();
        this.notifyObservers();
    }


}
