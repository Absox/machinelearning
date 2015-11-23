package edu.jhu.ml.model;

import org.apache.commons.math3.linear.RealVector;

/**
 * An entity with position and velocity.
 * Created by Ran on 11/23/2015.
 */
public abstract class MoveableEntity extends Entity {

    /**
     * Bearing of the entity.
     */
    protected RealVector bearing;

    /**
     * Speed at which the entity is moving (in direction of the bearing).
     */
    protected double speed;

    /**
     * Accessor for bearing.
     * @return Bearing of the entity.
     */
    public RealVector getBearing() {
        return this.bearing;
    }

    /**
     * Speed at which the entity is moving.
     * @return Speed of the entity.
     */
    public double getSpeed() {
        return this.speed;
    }

}
