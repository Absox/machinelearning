import edu.jhu.ml.model.Target;
import edu.jhu.ml.model.Turret;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests turret geometric calculations.
 * Created by Ran on 11/24/2015.
 */
public class TurretGeometryTest {

    @Test
    public void getRelativePositionTest() {
        double[] turretValues = {0, 0};
        Turret testTurret = new Turret(new ArrayRealVector(turretValues));
        double[] targetPositionValues = {5, 5};
        double[] targetBearingValues = {1, 0};
        Target testTarget = new Target();
        testTarget.setPosition(new ArrayRealVector(targetPositionValues));
        testTarget.setBearing(new ArrayRealVector(targetBearingValues));
        testTarget.setSpeed(1);

        RealVector relativeVelocity = testTurret.getRelativeVelocity(testTarget);
        assertEquals(relativeVelocity.getEntry(0), Math.sqrt(2)/2, 0.0001);
        assertEquals(relativeVelocity.getEntry(1), -Math.sqrt(2)/2, 0.0001);
    }
}
