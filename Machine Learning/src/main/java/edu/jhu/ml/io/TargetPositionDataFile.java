package edu.jhu.ml.io;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A file containing target positional data.
 * Created by Ran on 11/23/2015.
 */
public class TargetPositionDataFile {

    private List<RealVector> positions;

    /**
     * Reads target positional data from specified file.
     * @param filename Name of file to read data from.
     */
    public TargetPositionDataFile(String filename) throws FileNotFoundException {
        this.positions = new ArrayList<>();

        Scanner fileScanner = new Scanner(new File(filename));
        while (fileScanner.hasNextLine()) {

            String[] currentLine = fileScanner.nextLine().trim().split("\\s+");
            double[] values = new double[2];
            values[0] = Double.parseDouble(currentLine[0]);
            values[1] = Double.parseDouble(currentLine[1]);
            RealVector currentVector = new ArrayRealVector(values);
            this.positions.add(currentVector);

        }
        fileScanner.close();
    }

    /**
     * Accessor for positions.
     * @return List of RealVector positions in file.
     */
    public List<RealVector> getPositions() {
        return this.positions;
    }

    /**
     * Accessor for # of data frames.
     * @return # of frames.
     */
    public int getLength() {
        return this.positions.size();
    }

}
