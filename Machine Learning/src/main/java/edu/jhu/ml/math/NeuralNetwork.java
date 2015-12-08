package edu.jhu.ml.math;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.Random;

/**
 * Created by Ran on 12/7/2015.
 */
public class NeuralNetwork {

    private int numInputs;
    private int numOutputs;
    private double learningRate;

    private int[] numHiddenLayerNodes;
    private RealMatrix[] weightMatrices;

    /**
     * Constructs a neural network with the following characteristics:
     * @param numInputs Number of inputs.
     * @param numHiddenLayerNodes An array of ints containing the number of hidden layer nodes on each layer.
     * @param numOutputs Number of outputs;
     */
    public NeuralNetwork(int numInputs, int[] numHiddenLayerNodes, int numOutputs, double learningRate) {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenLayerNodes = numHiddenLayerNodes;
        this.learningRate = learningRate;

        // We require 1 weight matrix for every hidden layer, and then one for the output layer.
        this.weightMatrices = new RealMatrix[numHiddenLayerNodes.length + 1];
        this.weightMatrices[0] = new Array2DRowRealMatrix(numHiddenLayerNodes[0], numInputs);
        for (int c = 1; c < numHiddenLayerNodes.length; c++) {
            this.weightMatrices[c] = new Array2DRowRealMatrix(numHiddenLayerNodes[c-1], numHiddenLayerNodes[c]);
        }
        this.weightMatrices[numHiddenLayerNodes.length] = new Array2DRowRealMatrix(numOutputs, numHiddenLayerNodes[numHiddenLayerNodes.length - 1]);

        this.initialize();
    }

    /**
     * Initializes.
     */
    public void initialize() {

        Random rng = new Random(0);
        for (int c = 0; c < weightMatrices.length; c++) {
            RealMatrix currentWeightMatrix = weightMatrices[c];
            for (int i = 0; i < currentWeightMatrix.getRowDimension(); i++) {
                for (int j = 0; j < currentWeightMatrix.getColumnDimension(); j++) {
                    currentWeightMatrix.setEntry(i, j, rng.nextDouble());
                }
            }
        }

    }

    public double[] getOutput(double[] inputs) {
        State state = new State(inputs);
        return state.output.toArray();
    }

    /**
     * Updates weight matrices via the backpropagation algorithm.
     * @param inputs
     * @param truths
     */
    public void learn(double[] inputs, double[] truths) {
        if (inputs.length != numInputs || truths.length != numOutputs) return;
        State state = new State(inputs);

        // Calculate deltas
        RealVector[] deltas = new RealVector[numHiddenLayerNodes.length + 1];
        deltas[numHiddenLayerNodes.length] = new ArrayRealVector(numOutputs);
        for (int c = 0; c < numOutputs; c++) {
            deltas[numHiddenLayerNodes.length].setEntry(c, (state.output.getEntry(c) - truths[c]) * (state.output.getEntry(c) - Math.pow(state.output.getEntry(c), 2)));
        }
        for (int n = numHiddenLayerNodes.length - 1; n >= 0; n--) {
            deltas[n] = new ArrayRealVector(numHiddenLayerNodes[n]);
            for (int c = 0; c < numHiddenLayerNodes[n]; c++) {
                deltas[n].setEntry(c, (state.hiddenLayers[n].getEntry(c) - Math.pow(state.hiddenLayers[n].getEntry(c), 2)) * weightMatrices[n+1].getColumnVector(c).dotProduct(deltas[n+1]));
            }
        }

        // Calculate updates
        RealMatrix[] gradients = new RealMatrix[weightMatrices.length];
        for (int n = weightMatrices.length - 1; n >= 1; n--) {
            gradients[n] = new Array2DRowRealMatrix(weightMatrices[n].getRowDimension(), weightMatrices[n].getColumnDimension());
            for (int c = 0; c < weightMatrices[n].getRowDimension(); c++) {
                for (int d = 0; d < weightMatrices[n].getColumnDimension(); d++) {
                    gradients[n].setEntry(c, d, deltas[n].getEntry(c) * state.hiddenLayers[n-1].getEntry(d));
                }
            }
        }
        gradients[0] = new Array2DRowRealMatrix(weightMatrices[0].getRowDimension(), weightMatrices[0].getColumnDimension());
        for (int c = 0; c < weightMatrices[0].getRowDimension(); c++) {
            for (int d = 0; d < weightMatrices[0].getColumnDimension(); d++) {
                gradients[0].setEntry(c, d, deltas[0].getEntry(c) * inputs[d]);
            }
        }

        // Apply updates
        for (int n = 0; n < weightMatrices.length; n++) {
            weightMatrices[n] = weightMatrices[n].add(gradients[n].scalarMultiply(-learningRate));
        }

    }

    public int getClass(double[] input) {
        if (input.length != numInputs) return -1;
        double[] outputs = this.getOutput(input);

        int index = 0;
        double currentMax = outputs[0];
        for (int c = 1; c < numInputs; c++) {
            if (outputs[c] > currentMax) {
                currentMax = outputs[c];
                index = c;
            }
        }
        return index;
    }

    /**
     * Activation function.
     * @param z
     * @return
     */
    private static double activationFunction(double z) {
        return 1 / (1 + Math.exp(-z));
    }

    private class State {

        private RealVector[] hiddenLayers;
        private RealVector output;

        public State(double[] inputs) {
            this(new ArrayRealVector(inputs));
        }

        public State(RealVector input) {
            this.hiddenLayers = new RealVector[numHiddenLayerNodes.length];
            this.output = new ArrayRealVector(numOutputs);

            this.hiddenLayers[0] = new ArrayRealVector(numHiddenLayerNodes[0]);
            for (int c = 0; c < numHiddenLayerNodes[0]; c++) {
                hiddenLayers[0].setEntry(c, activationFunction(weightMatrices[0].getRowVector(c).dotProduct(input)));
            }
            for (int n = 1; n < numHiddenLayerNodes.length; n++) {
                hiddenLayers[n] = new ArrayRealVector(numHiddenLayerNodes[n]);
                for (int c = 0; c < numHiddenLayerNodes[n]; c++) {
                    hiddenLayers[n].setEntry(c, activationFunction(weightMatrices[n].getRowVector(c).dotProduct(hiddenLayers[n-1])));
                }
            }
            for (int c = 0; c < numOutputs; c++) {
                output.setEntry(c, activationFunction(weightMatrices[numHiddenLayerNodes.length].getRowVector(c).dotProduct(hiddenLayers[numHiddenLayerNodes.length - 1])));
            }
        }

    }
}
