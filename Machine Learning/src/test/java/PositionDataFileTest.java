import edu.jhu.ml.io.TargetPositionDataFile;
import org.apache.commons.math3.linear.RealVector;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ran on 11/28/2015.
 */
public class PositionDataFileTest {

    private TargetPositionDataFile dataFile;

    @Before
    public void initialize() {
        try {
            this.dataFile = new TargetPositionDataFile("linear_data.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataParsing() {
        if (this.dataFile != null) {
            List<RealVector> positions = this.dataFile.getPositions();
            RealVector currentPosition = positions.get(0);
            assertEquals(currentPosition.getEntry(0), 0, 0);
            assertEquals(currentPosition.getEntry(1), 200, 0);
            assertEquals(this.dataFile.getLength(), 561);
        }
    }

}
