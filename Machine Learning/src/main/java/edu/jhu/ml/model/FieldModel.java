package edu.jhu.ml.model;

import edu.jhu.ml.gui.GraphicalRepresentation;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Field model abstract class.
 * Created by Ran on 11/23/2015.
 */
public abstract class FieldModel extends Observable {

    protected List<Turret> turrets;
    protected List<Target> targets;
    protected List<Projectile> projectiles;
    protected double targetSpeed = 5;
    protected double projectileSpeed = 20;
    protected int turretCooldown = 30;
    protected int turretCooldownCounter = turretCooldown;
    protected int width = 1067;
    protected int height = 600;

    protected int shotCounter;
    protected int hitCounter;

    /**
     * Constructor base. Initializes lists.
     */
    protected FieldModel() {
        this.turrets = new ArrayList<>();
        this.targets = new ArrayList<>();
        this.projectiles = new LinkedList<>();
    }

    /**
     * Accessor for width of the field.
     * @return Width of the field.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Accessor for height of the field.
     * @return Height of the field.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Accessor for projectile speed.
     * @return How fast a projectile should move (in pixels per frame).
     */
    public double getProjectileSpeed() {
        return this.projectileSpeed;
    }

    /**
     * Accessor for target speed.
     * @return How fast (in pixels per frame) a target should move.
     */
    public double getTargetSpeed() {
        return this.targetSpeed;
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
     * Gets a list of all drawables within the model.
     * @return Drawables contained within this model.
     */
    public List<GraphicalRepresentation> getDrawables() {

        List<GraphicalRepresentation> result = new ArrayList<>();

        for (Turret t : turrets) {
            result.add(t.getGraphicalRepresentation());
        }
        for (Target t : targets) {
            result.add(t.getGraphicalRepresentation());
        }
        for (Projectile p : projectiles) {
            result.add(p.getGraphicalRepresentation());
        }
        return result;
    }

    /**
     * Accessor for # of shots fired.
     * @return # of shots fired.
     */
    public int getShotCounter() {
        return this.shotCounter;
    }

    /**
     * Accessor for # of shots hit.
     * @return # of shots hit.
     */
    public int getHitCounter() {
        return this.hitCounter;
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

    /**
     * Advances the targets one tick.
     */
    protected void advanceTargets() {
        for (Target t : this.targets) {
            t.advance();
        }
    }

    /**
     * Advances projectiles one tick.
     */
    protected void advanceProjectiles() {
        for (Projectile p : this.projectiles) {
            p.advance();
        }
    }

    /**
     * Checks if turret cooldown is on.
     * @return True if we fire.
     */
    protected boolean shouldFireTurrets() {
        if (--this.turretCooldownCounter == 0) {
            this.turretCooldownCounter = this.turretCooldown;
            return true;
        }
        return false;
    }

}
