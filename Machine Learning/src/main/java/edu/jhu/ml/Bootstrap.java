package edu.jhu.ml;

import edu.jhu.ml.math.NeuralNetworkTargeting;
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
            System.out.println("Error in parsing options: " + e.getMessage());
            printHelp();
            return;
        }

        TargetingVisualizerFacade facade = new TargetingVisualizerFacade("Targeting Algorithm Visualizer");
        NeuralNetworkTargeting targetingAlgorithm = new NeuralNetworkTargeting(5, 10, 1);

        //facade.bindTargetingAlgorithm(new LinearTargeting());
        facade.bindTargetingAlgorithm(targetingAlgorithm);
        //facade.getView().visualizeAlgorithm(targetingAlgorithm);

        facade.invokeMouseControl();
        //facade.invokeFileControl("circular_data.txt");
        //facade.invokeFileControl("linear_data.txt");

    }

    /**
     * Prints usage guide.
     */
    private static void printHelp() {
        HelpFormatter help = new HelpFormatter();
        help.printHelp("Usage guide:", options);
    }

    /**
     * Registers command-line options.
     */
    private static void registerOptions() {
        Option positionsFile = new Option("f", "file", false, "Target position data file");
        options.addOption(positionsFile);
    }

}
