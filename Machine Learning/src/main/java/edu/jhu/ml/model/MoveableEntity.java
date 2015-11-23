package edu.jhu.ml.model;

import org.apache.commons.math3.linear.RealVector;

/**
 * An entity with position and velocity.
 * Created by Ran on 11/23/2015.
 */
public abstract class MoveableEntity extends Entity {

    /**
     * Velocity of the entity.
     */
    private RealVector velocity;

    /**
     * Accessor for velocity.
     * @return
     */
    public RealVector getVelocity() {
        return this.velocity;
    }

}
