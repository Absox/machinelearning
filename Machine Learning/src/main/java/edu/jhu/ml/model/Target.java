package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.ArrayRealVector;

import java.awt.*;

/**
 * Target class.
 * Created by Ran on 11/23/2015.
 */
public class Target extends MoveableEntity {

    private static final double GRAPHICAL_REPRESENTATION_RADIUS = 10;
    private static final Color GRAPHICAL_REPRESENTATION_COLOR = Color.RED;

    /**
     * Default constructor for target.
     */
    public Target() {
        double[] values = {0, 0};
        this.speed = 0;
        this.bearing = new ArrayRealVector(values);
        this.position = new ArrayRealVector(values);
    }

    /**
     * Gets how this entity should be drawn on a View.
     * @return GraphicalRepresentation of this object.
     */
    public GraphicalRepresentation getGraphicalRepresentation() {

        return new GraphicalRepresentation(this.position, GRAPHICAL_REPRESENTATION_RADIUS, GRAPHICAL_REPRESENTATION_COLOR);

    }
}
