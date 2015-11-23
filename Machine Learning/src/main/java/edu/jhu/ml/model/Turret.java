package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.RealVector;

/**
 * Turret class.
 * Created by Ran on 11/23/2015.
 */
public class Turret extends Entity {

    /**
     * Constructs a Turret at the specified position.
     * @param position Position of turret.
     */
    public Turret(RealVector position) {
        this.position = position;
    }

    public GraphicalRepresentation getGraphicalRepresentation() {
        return null; // TODO
    }
}
