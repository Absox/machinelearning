package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.RealVector;

import java.util.List;

/**
 * A field model with one turret and one target.
 * Created by Ran on 11/23/2015.
 */
public class OneTurretOneTargetModel extends FieldModel {

    /**
     * Gets a list of all objects that should be visualized within the model.
     * @return List of all objects that should be visualized.
     */
    public List<GraphicalRepresentation> getDrawables() {
        return null; // TODO
    }

    /**
     * Accessor for turret positions.
     * @return Turret positions.
     */
    public List<RealVector> getTurretPositions() {
        return null; // TODO
    }

    /**
     * Accessor for target positions.
     * @return Target positions.
     */
    public List<RealVector> getTargetPositions() {
        return null;
    }
}
