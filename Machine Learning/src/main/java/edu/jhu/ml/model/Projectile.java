package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;

import java.awt.*;

/**
 * Projectile class.
 * Created by Ran on 11/23/2015.
 */
public class Projectile extends MoveableEntity {

    private static final double GRAPHICAL_REPRESENTATION_RADIUS = 5;
    private static final Color GRAPHICAL_REPRESENTATION_COLOR = Color.BLACK;

    /**
     * Gets how this entity should be drawn in a View.
     * @return Graphical representation of this entity.
     */
    public GraphicalRepresentation getGraphicalRepresentation() {
        return new GraphicalRepresentation(this.position, GRAPHICAL_REPRESENTATION_RADIUS, GRAPHICAL_REPRESENTATION_COLOR);
    }
}
