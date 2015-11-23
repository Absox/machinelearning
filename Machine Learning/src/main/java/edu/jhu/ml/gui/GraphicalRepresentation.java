package edu.jhu.ml.gui;

import org.apache.commons.math3.linear.RealVector;

import java.awt.*;

/**
 * A graphical representation of an entity on the field.
 * Created by Ran on 11/23/2015.
 */
public class GraphicalRepresentation {

    /**
     * Location (in field coordinates).
     */
    private RealVector position;

    /**
     * Radius of representation.
     */
    private double radius;

    /**
     * Color of representation.
     */
    private Color color;

    /**
     * Accessor for position.
     * @return Position of representation.
     */
    public RealVector getPosition() {
        return this.position;
    }

    /**
     * Accessor for radius.
     * @return Radius of representation.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Accessor for color.
     * @return Color of representation.
     */
    public Color getColor() {
        return this.color;
    }

}
