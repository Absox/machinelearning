package edu.jhu.ml.math;

import java.util.List;

/**
 * Created by Ran on 11/21/2015.
 */
public class NeuralNet {

    /**
     * Constructs an artificial network for classification.
     * @param inputs Dimension of input data.
     * @param outputs Number of classes of output.
     */
    public NeuralNet(int inputs, int outputs) {

    }

    /**
     * Updates network weights via backpropagation algorithm.
     */
    private void update() {
        // TODOx
    }

    private List<NeuralNetLayer> layers;

    /**
     * Object representation of a layer within the artificial neural net.
     */
    private static class NeuralNetLayer {
        private List<NeuralNetNode> nodes;
    }

    /**
     * A node within the neural net.
     */
    private static class NeuralNetNode {

        /**
         * Synaptic weights.
         */
        double[] weights;

    }
}
