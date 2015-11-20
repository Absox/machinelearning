package org.jhu.ml.io;

/**
 * Responsible for reading/parsing data from files.
 * Created by Ran on 11/19/2015.
 */
public class PositionDataFile {

    /**
     * Reads data from file specified.
     * @param filename Name of file.
     */
    public PositionDataFile(String filename) {
        // TODO
    }

    /**
     * A single row of data.
     */
    public class DataRow {

        /**
         * Time.
         */
        double t;
        /**
         * X position.
         */
        double x;
        /**
         * Y position.
         */
        double y;
        /**
         * X velocity.
         */
        double dx;
        /**
         * Y velocity.
         */
        double dy;
    }

}
