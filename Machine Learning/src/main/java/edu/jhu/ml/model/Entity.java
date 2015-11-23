package edu.jhu.ml.model;

import org.apache.commons.math3.linear.RealVector;

/**
 * An entity with position.
 * Created by Ran on 11/23/2015.
 */
public abstract class Entity {

    private RealVector position;

    /**
     * Accessor for position.
     * @return Position of entity.
     */
    public RealVector getPosition() {
        return this.position;
    }

}
