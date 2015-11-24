package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.RealVector;

import java.awt.*;

/**
 * Turret class.
 * Created by Ran on 11/23/2015.
 */
public class Turret extends Entity {

    private static final double GRAPHICAL_REPRESENTATION_RADIUS = 10;
    private static final Color GRAPHICAL_REPRESENTATION_COLOR = Color.GREEN;

    /**
     * Constructs a Turret at the specified position.
     * @param position Position of turret.
     */
    public Turret(RealVector position) {
        this.position = position;
    }

    /**
     * Calculates the relative position of the target.
     * @param t Target for which to compute relative position.
     * @return Relative position of the target.
     */
    public RealVector getRelativePosition(Target t) {
        return null;
    }

    /**
     * Gets the target's velocity relative to the turret (pointing directly at the target).
     * @param t Target for which to compute relative velocity.
     * @return Relative velocity of the target.
     */
    public RealVector getRelativeVelocity(Target t) {
        return null; // TODO
    }

    /**
     * Gets how this entity should be drawn in a View.
     * @return Graphical representation for this entity.
     */
    public GraphicalRepresentation getGraphicalRepresentation() {
        return new GraphicalRepresentation(this.position, GRAPHICAL_REPRESENTATION_RADIUS, GRAPHICAL_REPRESENTATION_COLOR);
    }
}
