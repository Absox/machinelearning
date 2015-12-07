package edu.jhu.ml.math;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by Ran on 12/7/2015.
 */
public class NeuralNetwork {

    private int numInputs;
    private int numOutputs;
    private int[] numHiddenLayerNodes;

    RealMatrix[] weightMatrices;

    /**
     * Constructs a neural network with the following characteristics:
     * @param numInputs Number of inputs.
     * @param numHiddenLayerNodes An array of ints containing the number of hidden layer nodes on each layer.
     * @param numOutputs Number of outputs;
     */
    public NeuralNetwork(int numInputs, int[] numHiddenLayerNodes, int numOutputs) {
        // We require 1 weight matrix for every hidden layer, and then one for the output layer.
        this.weightMatrices = new RealMatrix[numHiddenLayerNodes.length + 1];
    }

    /**
     * Initializes.
     */
    public void initialize() {

    }

    /**
     * Activation function.
     * @param z
     * @return
     */
    private static double activationFunction(double z) {
        return 1 / (1 + Math.exp(-z));
    }
}
