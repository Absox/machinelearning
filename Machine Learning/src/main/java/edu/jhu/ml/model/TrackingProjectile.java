package edu.jhu.ml.model;

import org.apache.commons.math3.linear.RealVector;

/**
 * Created by Ran on 12/10/2015.
 */
public class TrackingProjectile extends Projectile {

    private double closestApproachDistance;
    private Target target;

    public TrackingProjectile(RealVector position, RealVector velocity, Target target) {
        super(position, velocity);
        this.target = target;
        this.closestApproachDistance = this.position.getDistance(target.getPosition());
    }

    /**
     * Creates a tracking projectile from a regular projectile.
     * @param p Projectile that we're using to construct our tracking projectile.
     * @param target Target that we're tracking.
     */
    public TrackingProjectile(Projectile p, Target target) {
        super(p.getPosition(), p.getVelocity());
        this.target = target;
        this.closestApproachDistance = this.position.getDistance(target.getPosition());
    }

    /**
     * Advances the state of this entity one tick. Overrides implementation in MoveableEntity.
     */
    public void advance() {
        super.advance();
        double currentApproachDistance = this.position.getDistance(this.target.getPosition());
        if (currentApproachDistance < closestApproachDistance) {
            closestApproachDistance = currentApproachDistance;
        }
    }

    /**
     * Gets our current closest approach distance.
     * @return Current closest approach distance.
     */
    public double getClosestApproachDistance() {
        return this.closestApproachDistance;
    }

}
