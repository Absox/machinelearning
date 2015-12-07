package edu.jhu.ml.math;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.Random;

/**
 * Created by Ran on 12/7/2015.
 */
public class SingleHiddenLayerNeuralNetwork {

    private int numInputs;
    private int numOutputs;
    private int numHiddenNodes;

    private RealMatrix hiddenLayerWeights;
    private RealMatrix outputWeights;

    private double learningRate;

    /**
     * Constructs a neural network with a single hidden layer.
     * @param numInputs Number of inputs.
     * @param numHiddenNodes Number of hidden nodes.
     * @param numOutputs Number of outputs.
     */
    public SingleHiddenLayerNeuralNetwork(int numInputs, int numHiddenNodes, int numOutputs, double learningRate) {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenNodes = numHiddenNodes;
        this.learningRate = learningRate;
        this.hiddenLayerWeights = new Array2DRowRealMatrix(numHiddenNodes, numInputs);
        this.outputWeights = new Array2DRowRealMatrix(numOutputs, numHiddenNodes);

        this.initialize();
    }

    /**
     * Initializes weights randomly.
     */
    public void initialize() {
        Random rng = new Random(0);
        for (int c = 0; c < numHiddenNodes; c++) {
            for (int d = 0; d < numInputs; d++) {
                hiddenLayerWeights.setEntry(c, d, rng.nextDouble());
            }
            for (int d = 0; d < numOutputs; d++) {
                outputWeights.setEntry(d, c, rng.nextDouble());
            }
        }
    }

    /**
     * Gets the output of this neural net for a given input.
     * @param inputs Array of inputs.
     * @return Output of the neural net.
     */
    public double[] getOutput(double[] inputs) {
        if (inputs.length != numInputs) return null; // Contract for this method
        State state = new State(inputs);
        return state.outputs.toArray();
    }

    /**
     * Updates the weights.
     * @param inputs Inputs.
     * @param truths Truth values for output.
     * @return Returns the output of the neural net after learning.
     */
    public double[] learn(double[] inputs, double[] truths) {
        if (inputs.length != numInputs || truths.length != numOutputs) return null;
        State state = new State(inputs);

        // Calculate deltas.
        RealVector outputDeltas = new ArrayRealVector(numOutputs);
        for (int c = 0; c < numOutputs; c++) {
            outputDeltas.setEntry(c, (state.outputs.getEntry(c) - truths[c]) * (state.outputs.getEntry(c) - Math.pow(state.outputs.getEntry(c), 2)));
        }
        RealVector hiddenDeltas = new ArrayRealVector(numHiddenNodes);
        for (int c = 0; c < numHiddenNodes; c++) {
            hiddenDeltas.setEntry(c, (state.hiddenLayer.getEntry(c) - Math.pow(state.hiddenLayer.getEntry(c), 2)) * outputWeights.getColumnVector(c).dotProduct(outputDeltas));
        }

        // Calculate updates.
        RealMatrix hiddenWeightGradient = new Array2DRowRealMatrix(numHiddenNodes, numInputs);
        for (int c = 0; c < numHiddenNodes; c++) {
            for (int d = 0; d < numInputs; d++) {
                hiddenWeightGradient.setEntry(c, d, inputs[d] * hiddenDeltas.getEntry(c));
            }
        }
        RealMatrix outputWeightGradient = new Array2DRowRealMatrix(numOutputs, numHiddenNodes);
        for (int c = 0; c < numOutputs; c++) {
            for (int d = 0; d < numHiddenNodes; d++) {
                outputWeightGradient.setEntry(c, d, state.hiddenLayer.getEntry(d) * outputDeltas.getEntry(c));
            }
        }

        // Apply updates
        this.hiddenLayerWeights = this.hiddenLayerWeights.add(hiddenWeightGradient.scalarMultiply(-learningRate));
        this.outputWeights = this.outputWeights.add(outputWeightGradient.scalarMultiply(-learningRate));

        return this.getOutput(inputs);
    }

    /**
     * Activation function for the neural net.
     * @param z Value for which we're computing the activation function.
     * @return Value of activation function.
     */
    private static double activationFunction(double z) {
        return 1 / (1 + Math.exp(-z));
    }

    /**
     * Gets the state of all the nodes of the neural net.
     */
    private class State {
        RealVector hiddenLayer;
        RealVector outputs;

        public State(double[] inputs) {
            this(new ArrayRealVector(inputs));
        }

        public State(RealVector input) {
            this.hiddenLayer = new ArrayRealVector(numHiddenNodes);
            this.outputs = new ArrayRealVector(numOutputs);

            for (int c = 0; c < numHiddenNodes; c++) {
                hiddenLayer.setEntry(c, activationFunction(hiddenLayerWeights.getRowVector(c).dotProduct(input)));
            }
            for (int c = 0; c < numOutputs; c++) {
                outputs.setEntry(c, activationFunction(outputWeights.getRowVector(c).dotProduct(hiddenLayer)));
            }
        }
    }
}
