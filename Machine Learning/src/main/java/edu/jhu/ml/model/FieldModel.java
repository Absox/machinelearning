package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.RealVector;

import java.util.List;
import java.util.Observable;

/**
 * Field model abstract class.
 * Created by Ran on 11/23/2015.
 */
public abstract class FieldModel extends Observable {

    /**
     * A field model.
     * @return
     */
    abstract List<GraphicalRepresentation> getDrawables();

    /**
     * Turret positions.
     * @return
     */
    abstract List<RealVector> getTurretPositions();

    /**
     * Accessor for target positions.
     * @return Target positions.
     */
    abstract List<RealVector> getTargetPositions();

}
