package edu.jhu.ml.math;

import edu.jhu.ml.model.Target;
import edu.jhu.ml.model.Turret;

/**
 * Firing solution. A heading of 0 means we fire directly at the current location of the target.
 * Created by Ran on 11/23/2015.
 */
public class FiringSolution {

    private Turret turret;
    private Target target;

    /**
     * The offset in radians from the current position of the target.
     */
    private double offsetRadians;

    /**
     * Constructs a blank firing solution.
     */
    public FiringSolution() {
        this.offsetRadians = 0;
    }

    /**
     * Constructs a firing solution for the specified turret, onto the specified target.
     * @param turret Turret firing from.
     * @param target Target firing at.
     */
    public FiringSolution(Turret turret, Target target) {
        this.turret = turret;
        this.target = target;
        this.offsetRadians = 0;
    }

    /**
     * Mutator for offset for the firing solution.
     * @param offsetRadians New offset for the firing solution.
     */
    public void setOffsetRadians(double offsetRadians) {
        this.offsetRadians = offsetRadians;
    }

    /**
     * Gets the offset for the firing solution.
     * @return Offset for the firing solution.
     */
    public double getOffsetRadians() {
        return this.offsetRadians;
    }

}
