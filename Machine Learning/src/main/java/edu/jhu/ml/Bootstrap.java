package edu.jhu.ml;

import org.apache.commons.cli.*;

/**
 * Main class. Handles command-line options, initializes other classes.
 * Created by Ran on 11/23/2015.
 */
public class Bootstrap {


    static Options options = new Options();

    /**
     * Main method.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TargetingVisualizerFacade facade = new TargetingVisualizerFacade("ML Targeting Algorithm Visualizer");

    }

}
