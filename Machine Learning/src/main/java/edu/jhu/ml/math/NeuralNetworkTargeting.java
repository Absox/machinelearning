package edu.jhu.ml.math;

import edu.jhu.ml.model.OneTurretOneTargetModel;
import edu.jhu.ml.model.Target;
import edu.jhu.ml.model.TrackingProjectile;
import edu.jhu.ml.model.Turret;
import org.apache.commons.math3.linear.RealVector;

import java.util.*;

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

    /**
     * Model that we're observing.
     */
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
     * Queue of features.
     */
    private LinkedList<Features> features;

    /**
     * Length of feature queue.
     */
    private int memory;

    /**
     * Constructor for a neural network targeting algorithm.
     * @param numOutputs Number of outputs.
     * @param numHiddenLayerNodes Number of hidden layer nodes.
     * @param learningRate Learning rate.
     */
    public NeuralNetworkTargeting(int numOutputs, int numHiddenLayerNodes, double learningRate, int memory) {
        this.neuralNetwork = new SingleHiddenLayerNeuralNetwork(3 * memory, numHiddenLayerNodes, numOutputs, learningRate);
        this.numOutputs = numOutputs;
        this.instances = new LinkedList<>();
        this.memory = memory;
        this.features = new LinkedList<>();
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
        // We are only ready to fire if we have enough information in memory.
        if (this.features.size() == this.memory) {
            FiringSolution solution = new FiringSolution(turret, target);
            int classification = this.neuralNetwork.getClass(getInputsFromFeatures(this.features));
            solution.setOffsetRadians(-maxEscapeAngle + classification * increment);
            return solution;
        } else {
            return null;
        }
    }

    /**
     * Updates when the field model updates.
     * @param o Model we're observing.
     * @param arg Arguments passed from model.
     */
    public void update(Observable o, Object arg) {

        // Update all our instances.
        Iterator<Instance> instanceIterator = this.instances.iterator();
        while (instanceIterator.hasNext()) {
            Instance currentInstance = instanceIterator.next();
            currentInstance.advance();

            // If all trackers in the instance are out of field bounds, the information is finalized.
            if (currentInstance.isOutOfBounds()) {
                // We learn from it and remove it from our list.
                this.neuralNetwork.learn(currentInstance.getInputs(), currentInstance.getTruths());
                instanceIterator.remove();
                continue;
            }
        }

        this.features.add(new Features(model));
        // If we have more features than we want, we dequeue.
        if (this.features.size() > this.memory) {
            this.features.remove();
        }
        // Add a new instance, if we have sufficient data in our queue of features.
        if (this.features.size() == this.memory) {
            instances.add(new Instance());
        }
    }

    /**
     * Returns our instances.
     * @return
     */
    public List<Instance> getInstances() {
        return this.instances;
    }

    /**
     * Concatenates scaled feature vectors.
     * @param featuresList List of features.
     * @return Array containing the concatenated scaled features of all in the list.
     */
    private static double[] getInputsFromFeatures(List<Features> featuresList) {
        double[] result = new double[3 * featuresList.size()];

        for (int c = 0; c < featuresList.size(); c++) {
            double[] scaledFeatures = featuresList.get(c).getScaledFeatures();
            result[3 * c] = scaledFeatures[0];
            result[3 * c + 1] = scaledFeatures[1];
            result[3 * c + 2] = scaledFeatures[2];
        }

        return result;
    }

    /**
     * Creates a learning instance.
     */
    public class Instance {

        private TrackingProjectile[] projectiles;
        private double[] inputs;

        /**
         * Constructs an instance using current model parameters.
         */
        public Instance() {
            // We concatenate the scaled feature vectors into our inputs.
            this.inputs = getInputsFromFeatures(features);

            projectiles = new TrackingProjectile[numOutputs];
            for (int c = 0; c < numOutputs; c++) {
                FiringSolution currentFiringSolution = new FiringSolution(turret, target);
                currentFiringSolution.setOffsetRadians(-maxEscapeAngle + increment * c);
                projectiles[c] = new TrackingProjectile(turret.fire(target, model.getProjectileSpeed(), currentFiringSolution), target);
            }
        }

        /**
         * Accessor for input values.
         * @return Array of input values.
         */
        public double[] getInputs() {
            return this.inputs;
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
                if (!p.isOutOfFieldBounds(model)) return false;
            }

            return true;
        }

        /**
         * Gets the truths for this learning instance.
         * @return Truths.
         */
        public double[] getTruths() {

            double[] truths = new double[projectiles.length];

            double maxDistance = projectiles[0].getClosestApproachDistance();
            double minDistance = projectiles[0].getClosestApproachDistance();

            for (int c = 1; c < projectiles.length; c++) {
                if (projectiles[c].getClosestApproachDistance() > maxDistance) {
                    maxDistance = projectiles[c].getClosestApproachDistance();
                }
                if (projectiles[c].getClosestApproachDistance() < minDistance) {
                    minDistance = projectiles[c].getClosestApproachDistance();
                }
            }

            double range = maxDistance - minDistance;
            if (range == 0) {
                for (int c = 0; c < projectiles.length; c++) {
                    truths[c] = 1;
                }
            } else {
                for (int c = 0; c < projectiles.length; c++) {
                    truths[c] = 1.0 - (projectiles[c].getClosestApproachDistance() - minDistance)/range;
                }
            }
            return truths;
        }
    }

    /**
     * Helper class, extracts features from model.
     */
    private class Features {
        private double distance;
        private double relativeXVelocity;
        private double relativeYVelocity;
        private double maxDistance;

        /**
         * Extracts features from model.
         * @param model Model to get features from.
         */
        public Features(OneTurretOneTargetModel model) {
            this.distance = model.getTurret().getPosition().getDistance(model.getTarget().getPosition());
            RealVector relativeVelocity = model.getTurret().getRelativeVelocity(model.getTarget());
            this.relativeXVelocity = relativeVelocity.getEntry(0);
            this.relativeYVelocity = relativeVelocity.getEntry(1);
            this.maxDistance = Math.sqrt(Math.pow(model.getWidth() / 2, 2) + Math.pow(model.getHeight() / 2, 2));
        }

        /**
         * Returns string representation.
         * @return String representation of these features.
         */
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("Distance: ");
            result.append(distance);
            result.append(" Relative velocity: (");
            result.append(relativeXVelocity);
            result.append(", ");
            result.append(relativeYVelocity);
            result.append(") - ");
            result.append("Scaled: ");
            result.append(Arrays.toString(this.getScaledFeatures()));
            return result.toString();
        }

        /**
         * Scales and normalizes features.
         * @return Scaled and normalized array of features.
         */
        public double[] getScaledFeatures() {
            double[] result = new double[3];
            result[0] = distance / maxDistance;
            result[1] = relativeXVelocity / model.getProjectileSpeed();
            result[2] = relativeYVelocity / model.getProjectileSpeed();
            return result;
        }
    }

}
