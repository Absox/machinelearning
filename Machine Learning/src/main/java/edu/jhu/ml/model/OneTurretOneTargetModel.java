package edu.jhu.ml.model;

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
     * Mutator for target position.
     * @param newPosition New position of target.
     */
    public void setTargetPosition(RealVector newPosition) {
        this.target.setPosition(newPosition);
        this.setChanged();
    }

    /**
     * Advances the model one tick.
     */
    public void advance() {
        this.notifyObservers();
    }
}
