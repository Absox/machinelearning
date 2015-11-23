package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Field model abstract class.
 * Created by Ran on 11/23/2015.
 */
public abstract class FieldModel extends Observable {

    private List<Turret> turrets;
    private List<Target> targets;

    /**
     * Constructor base. Initializes lists.
     */
    protected FieldModel() {
        this.turrets = new ArrayList<>();
        this.targets = new ArrayList<>();
    }

    /**
     * Adds a turret to the model.
     * @param t Turret to add to the model.
     */
    public void addTurret(Turret t) {
        this.turrets.add(t);
    }

    /**
     * Adds a target to the model.
     * @param t Target to add to the model.
     */
    public void addTarget(Target t) {
        this.targets.add(t);
    }

    /**
     * A field model.
     * @return
     */
    public List<GraphicalRepresentation> getDrawables() {

        List<GraphicalRepresentation> result = new ArrayList<>();

        for (Turret t : turrets) {
            result.add(t.getGraphicalRepresentation());
        }
        for (Target t : targets) {
            result.add(t.getGraphicalRepresentation());
        }
        return result;
    }


    /**
     * Accessor for turret positions.
     * @return Turret positions.
     */
    public List<RealVector> getTurretPositions() {
        List<RealVector> result = new ArrayList<>();

        for (Turret t : turrets) {
            result.add(t.getPosition());
        }

        return result;
    }

    /**
     * Accessor for target positions.
     * @return Target positions.
     */
    public List<RealVector> getTargetPositions() {
        List<RealVector> result = new ArrayList<>();
        for (Target t : targets) {
            result.add(t.getPosition());
        }
        return result;
    }

}
