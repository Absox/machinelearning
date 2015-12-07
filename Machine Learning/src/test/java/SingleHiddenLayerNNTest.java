import edu.jhu.ml.math.SingleHiddenLayerNeuralNetwork;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Ran on 12/7/2015.
 */
public class SingleHiddenLayerNNTest {

    @Test
    public void testValues() {

        SingleHiddenLayerNeuralNetwork testNN = new SingleHiddenLayerNeuralNetwork(2, 5, 2, 1);
        double[] testValues = {0, 1};
        double[] testTruths = {0, 1};

        double[] testValues2 = {1, 0};
        double[] testTruths2 = {1, 0};

        double[] testValues3 = {1, 1};
        double[] testValues4 = {0, 0};

        for (int c = 0; c < 100; c++) {
            testNN.learn(testValues, testTruths);
            testNN.learn(testValues2, testTruths2);
            testNN.learn(testValues3, testValues3);
            testNN.learn(testValues4, testValues4);
        }


        System.out.println("Outputs:");
        System.out.println(Arrays.toString(testNN.getOutput(testValues)));
        System.out.println(Arrays.toString(testNN.getOutput(testValues2)));
        System.out.println("Classifications: ");
        System.out.println(testNN.getClass(testValues));
        System.out.println(testNN.getClass(testValues2));

    }
}
