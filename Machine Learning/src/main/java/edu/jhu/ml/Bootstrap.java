package edu.jhu.ml;

import edu.jhu.ml.math.DirectFireTargeting;
import edu.jhu.ml.math.LinearTargeting;
import edu.jhu.ml.math.NeuralNetworkTargeting;
import edu.jhu.ml.math.TargetingAlgorithm;
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
        CommandLine cmd = null;
        TargetingAlgorithm algorithm = null;
        String filename = null;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("file")) {
                filename = cmd.getOptionValue("file");
            }
            if (cmd.hasOption("algorithm")) {
                switch (cmd.getOptionValue("algorithm")) {
                    case "direct":
                        algorithm = new DirectFireTargeting();
                        break;
                    case "linear":
                        algorithm = new LinearTargeting();
                        break;
                    case "neural":
                        break;
                }
            }

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
        //facade.invokeFileControl("mouse.txt");
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

        Option algorithm = new Option("a", "algorithm", true, "Targeting algorithm");
        options.addOption(algorithm);

        Option numOutputs = new Option("o", "outputs", false, "Number of classes for classification");
        options.addOption(numOutputs);

        Option numHiddenNodes = new Option("h", "hidden", false, "Number of hidden nodes for neural network");
        options.addOption(numHiddenNodes);

        Option visualize = new Option("v", "visualize", false, "Visualize targeting algorithm");
        options.addOption(visualize);

    }

}
