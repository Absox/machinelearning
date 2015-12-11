package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.ArrayRealVector;

import java.awt.*;

/**
 * Target class.
 * Created by Ran on 11/23/2015.
 */
public class Target extends MoveableEntity {

    private double radius = 30;
    private static final Color GRAPHICAL_REPRESENTATION_COLOR = Color.RED;

    /**
     * Default constructor for target.
     */
    public Target() {
        double[] values = {0, 200};
        this.speed = 0;
        this.bearing = new ArrayRealVector(values);
        this.position = new ArrayRealVector(values);
    }

    /**
     * Accessor for radius.
     * @return Radius of target.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Gets how this entity should be drawn on a View.
     * @return GraphicalRepresentation of this object.
     */
    public GraphicalRepresentation getGraphicalRepresentation() {

        return new GraphicalRepresentation(this.position, radius, GRAPHICAL_REPRESENTATION_COLOR);

    }
}
