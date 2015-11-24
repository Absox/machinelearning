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
     * Gets how this entity should be drawn in a View.
     * @return Graphical representation for this entity.
     */
    public GraphicalRepresentation getGraphicalRepresentation() {
        return new GraphicalRepresentation(this.position, GRAPHICAL_REPRESENTATION_RADIUS, GRAPHICAL_REPRESENTATION_COLOR);
    }
}
