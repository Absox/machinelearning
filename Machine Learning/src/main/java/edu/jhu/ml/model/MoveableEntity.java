package edu.jhu.ml.model;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

/**
 * An entity with position and velocity.
 * Created by Ran on 11/23/2015.
 */
public abstract class MoveableEntity extends Entity {

    /**
     * Bearing of the entity.
     */
    protected RealVector bearing;

    /**
     * Speed at which the entity is moving (in direction of the bearing).
     */
    protected double speed;

    /**
     * Target position, if any.
     */
    protected RealVector targetPosition = null;

    /**
     * Accessor for bearing.
     * @return Bearing of the entity.
     */
    public RealVector getBearing() {
        return this.bearing;
    }

    /**
     * Speed at which the entity is moving.
     * @return Speed of the entity.
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Mutator for bearing.
     * @param v New bearing of the entity.
     */
    public void setBearing(RealVector v) {
        this.bearing = v;
    }

    /**
     * Mutator for speed.
     * @param d New speed of the entity.
     */
    public void setSpeed(double d) {
        this.speed = d;
    }

    /**
     * Mutator for velocity.
     * @param v New velocity of the entity.
     */
    public void setVelocity(RealVector v) {
        if (v.getNorm() == 0) {
            this.speed = 0;
            this.bearing = v;
        } else {
            this.bearing = v.mapDivide(v.getNorm());
            this.speed = v.getNorm();
        }

    }

    /**
     * Accessor for velocity.
     * @return Velocity of the entity.
     */
    public RealVector getVelocity() {
        return this.bearing.mapMultiply(this.speed);
    }

    /**
     * Sets the velocity of this object such that we move towards the specified point at the specified speed.
     * @param v Target position.
     * @param speed Speed at which to move.
     */
    public void moveTowards(RealVector v, double speed) {
        this.targetPosition = v;
        RealVector displacement = v.subtract(this.position);
        if (displacement.getNorm() != 0) {
            RealVector velocity = displacement.mapDivide(displacement.getNorm()).mapMultiply(speed);
            this.setVelocity(velocity);
        }
    }

    /**
     * Advances the state of this entity one tick.
     */
    public void advance() {
        if (this.targetPosition != null) {
            if (this.position.getDistance(this.targetPosition) < this.speed) {
                this.setPosition(this.targetPosition);
                this.setVelocity(new ArrayRealVector(2)); // Nullify velocity.
            } else {
                this.setPosition(this.position.add(this.getVelocity()));
            }
        } else {

            this.setPosition(this.position.add(this.getVelocity()));
            //this.setVelocity(new ArrayRealVector(2)); // Nullify velocity.
        }
    }

    public boolean isOutOfFieldBounds(FieldModel model) {
        if (Math.abs(this.position.getEntry(0)) > model.width/2 || Math.abs(this.position.getEntry(1)) > model.height/2) {
            return true;
        }
        return false;
    }

}
