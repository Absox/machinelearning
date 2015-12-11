import edu.jhu.ml.TargetingVisualizerFacade;
import edu.jhu.ml.math.NeuralNetworkTargeting;
import org.junit.Test;

/**
 * Created by Ran on 12/10/2015.
 */
public class NeuralTargetingTest {

    @Test
    public void test() {

        TargetingVisualizerFacade facade = new TargetingVisualizerFacade("Neural Targeting Test");
        facade.bindTargetingAlgorithm(new NeuralNetworkTargeting(3, 5, 3));
        facade.invokeMouseControl();

    }

}
