package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.ArrayRealVector;

/**
 * Target class.
 * Created by Ran on 11/23/2015.
 */
public class Target extends MoveableEntity {

    public Target() {
        double[] values = {0, 0};
        this.speed = 0;
        this.bearing = new ArrayRealVector(values);
        this.position = new ArrayRealVector(values);
    }

    public GraphicalRepresentation getGraphicalRepresentation() {
        return null; // TODO
    }
}
