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
     * Constructor for Graphical representation; pass all fields.
     * @param position Position of graphical representation.
     * @param radius Radius of representation.
     * @param color Color of representation.
     */
    public GraphicalRepresentation(RealVector position, double radius, Color color) {
        this.position = position;
        this.radius = radius;
        this.color = color;
    }

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
