package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import edu.jhu.ml.math.FiringSolution;
import edu.jhu.ml.math.TargetingAlgorithm;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.awt.*;

/**
 * Turret class.
 * Created by Ran on 11/23/2015.
 */
public class Turret extends Entity {

    private static final double GRAPHICAL_REPRESENTATION_RADIUS = 10;
    private static final Color GRAPHICAL_REPRESENTATION_COLOR = Color.GREEN;

    private TargetingAlgorithm targetingAlgorithm;

    public Turret() {
        double[] values = {0, 0};
        this.position = new ArrayRealVector(values);
    }

    /**
     * Constructs a Turret at the specified position.
     * @param position Position of turret.
     */
    public Turret(RealVector position) {
        this.position = position;
    }

    /**
     * Calculates the relative position of the target.
     * @param t Target for which to compute relative position.
     * @return Relative position of the target.
     */
    public RealVector getRelativePosition(Target t) {
        double distance = this.getPosition().getDistance(t.getPosition());
        double[] values = {this.getPosition().getEntry(0) + distance, this.getPosition().getEntry(1) + 0};
        return new ArrayRealVector(values);
    }

    /**
     * Gets the target's velocity relative to the turret (pointing directly at the target).
     * @param t Target for which to compute relative velocity.
     * @return Relative velocity of the target.
     */
    public RealVector getRelativeVelocity(Target t) {
        RealVector targetPosition = t.getPosition();
        double angle = Math.atan2(targetPosition.getEntry(1), targetPosition.getEntry(0));
        RealMatrix rotationMatrix = TargetingAlgorithm.rotationMatrix(-angle);
        return rotationMatrix.operate(t.getVelocity());
    }

    /**
     * Gets how this entity should be drawn in a View.
     * @return Graphical representation for this entity.
     */
    public GraphicalRepresentation getGraphicalRepresentation() {
        return new GraphicalRepresentation(this.position, GRAPHICAL_REPRESENTATION_RADIUS, GRAPHICAL_REPRESENTATION_COLOR);
    }

    /**
     * Mutator for targeting algorithm.
     * @param a Targeting algorithm.
     */
    public void setTargetingAlgorithm(TargetingAlgorithm a) {
        this.targetingAlgorithm = a;
    }

    /**
     * Accessor for turret's targeting algorithm.
     * @return Targeting algorithm.
     */
    public TargetingAlgorithm getTargetingAlgorithm() {
        return this.targetingAlgorithm;
    }

    /**
     * Fires the turret.
     * @param target Target to fire at.
     * @param speed Speed of projectile.
     * @param firingSolution Firing solution.
     * @return Projectile.
     */
    public Projectile fire(Target target, double speed, FiringSolution firingSolution) {
        RealMatrix rotationMatrix = TargetingAlgorithm.rotationMatrix(firingSolution.getOffsetRadians());
        RealVector displacement = rotationMatrix.operate(target.getPosition().subtract(this.position));
        RealVector velocity = displacement.mapDivide(displacement.getNorm()).mapMultiply(speed);
        Projectile result = new Projectile(this.position, velocity);
        return result;
    }


}
