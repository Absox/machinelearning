package edu.jhu.ml.math;

import edu.jhu.ml.model.*;
import org.apache.commons.math3.linear.RealVector;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Targeting algorithm that uses a neural network to determine firing solutions.
 * Created by Ran on 11/23/2015.
 */
public class NeuralNetworkTargeting implements TargetingAlgorithm {

    private SingleHiddenLayerNeuralNetwork neuralNetwork;

    /**
     * Current turret location.
     */
    private Turret turret;
    /**
     * Current target location.
     */
    private Target target;

    private OneTurretOneTargetModel model;

    /**
     * Maximum width of firing arc.
     */
    private double maxEscapeAngle;

    /**
     * Increment by which the firing angle changes.
     */
    private double increment;

    /**
     * Number of classes.
     */
    private int numOutputs;

    /**
     * List of instances that we learn from.
     */
    private List<Instance> instances;

    /**
     * Constructor for a neural network targeting algorithm.
     * @param numOutputs Number of outputs.
     * @param numHiddenLayerNodes Number of hidden layer nodes.
     * @param learningRate Learning rate.
     */
    public NeuralNetworkTargeting(int numOutputs, int numHiddenLayerNodes, double learningRate) {
        this.neuralNetwork = new SingleHiddenLayerNeuralNetwork(3, numHiddenLayerNodes, numOutputs, learningRate);
        this.numOutputs = numOutputs;
        this.instances = new LinkedList<>();
    }

    /**
     * Initializes the targeting algorithm.
     * @param arg Parameters with which to initialize.
     */
    public void initialize(Object arg) {
        this.model = (OneTurretOneTargetModel)arg;
        this.target = model.getTarget();
        this.turret = model.getTurret();
        this.maxEscapeAngle = Math.asin(model.getTargetSpeed()/model.getProjectileSpeed());
        this.increment = 2 * maxEscapeAngle / (numOutputs - 1);
    }

    /**
     * Generates a firing solution based on the current state.
     * @return A firing solution.
     */
    public FiringSolution fire() {
        return null;
        // TODO
    }

    /**
     * Updates when the field model updates.
     * @param o Model we're observing.
     * @param arg Arguments passed from model.
     */
    public void update(Observable o, Object arg) {

        Iterator<Instance> instanceIterator = this.instances.iterator();
        while (instanceIterator.hasNext()) {
            Instance currentInstance = instanceIterator.next();
            currentInstance.advance();
            if (currentInstance.isOutOfBounds()) {
                instanceIterator.remove();
                continue;
            }
        }

        instances.add(new Instance());
    }

    /**
     * Returns our instances.
     * @return
     */
    public List<Instance> getInstances() {
        return this.instances;
    }

    /**
     * Creates a learning instance.
     */
    public class Instance {

        private TrackingProjectile[] projectiles;
        private Features features;

        /**
         * Constructs an instance using current model parameters.
         */
        public Instance() {
            // Features are set at the time at which the instance is generated.
            this.features = new Features(model);

            projectiles = new TrackingProjectile[numOutputs];
            for (int c = 0; c < numOutputs; c++) {
                FiringSolution currentFiringSolution = new FiringSolution(turret, target);
                currentFiringSolution.setOffsetRadians(-maxEscapeAngle + increment * c);
                projectiles[c] = new TrackingProjectile(turret.fire(target, model.getProjectileSpeed(), currentFiringSolution), target);
            }
        }

        /**
         * Gets a list of our projectiles.
         * @return List of projectiles.
         */
        public TrackingProjectile[] getProjectiles() {
            return this.projectiles;
        }


        /**
         * Advances our projectiles one tick.
         */
        public void advance() {
            for (TrackingProjectile p : projectiles) {
                p.advance();
            }
        }

        /**
         * Checks if any of the projectiles is out of bounds.
         * @return True if out of bounds. Else false.
         */
        public boolean isOutOfBounds() {
            for (TrackingProjectile p : this.projectiles) {
                if (p.isOutOfFieldBounds(model)) return true;
            }

            return false;
        }

        /**
         * Gets the index of the closest projectile.
         * @return Closest projectile.
         */
        public int getClosestProjectile() {
            int currentClosest = 0;
            double currentDistance = this.projectiles[0].getClosestApproachDistance();

            for (int c = 1; c < this.projectiles.length; c++) {
                if (this.projectiles[c].getClosestApproachDistance() < currentDistance) {
                    currentDistance = this.projectiles[c].getClosestApproachDistance();
                    currentClosest = c;
                }
            }

            return currentClosest;
        }

    }

    /**
     * Helper class, extracts features from model.
     */
    private class Features {
        private double distance;
        private double relativeXVelocity;
        private double relativeYVelocity;

        public Features(OneTurretOneTargetModel model) {
            this.distance = model.getTurret().getPosition().getDistance(model.getTarget().getPosition());
            RealVector relativeVelocity = model.getTurret().getRelativeVelocity(model.getTarget());
            this.relativeXVelocity = relativeVelocity.getEntry(0);
            this.relativeYVelocity = relativeVelocity.getEntry(1);
        }
    }

}
