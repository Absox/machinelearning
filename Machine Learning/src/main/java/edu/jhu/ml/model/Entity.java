package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.RealVector;

/**
 * An entity with position.
 * Created by Ran on 11/23/2015.
 */
public abstract class Entity {

    /**
     * Position of this entity.
     */
    private RealVector position;

    /**
     * Accessor for position.
     * @return Position of entity.
     */
    public RealVector getPosition() {
        return this.position;
    }

    /**
     * Gets the graphical representation for this entity.
     * @return The graphical representation for this entity.
     */
    abstract GraphicalRepresentation getGraphicalRepresentation();

}
