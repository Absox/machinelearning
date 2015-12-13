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


    private static Options options = new Options();

    /**
     * Main method.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        registerOptions();

        CommandLineParser parser = new DefaultParser();
        TargetingAlgorithm algorithm = null;
        String filename = null;
        boolean visualize = false;

        try {
            CommandLine cmd = parser.parse(options, args);
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
                        int output = 5;
                        if (cmd.hasOption("outputs")) output = Integer.parseInt(cmd.getOptionValue("outputs"));
                        int hidden = 5;
                        if (cmd.hasOption("hidden")) hidden = Integer.parseInt(cmd.getOptionValue("hidden"));
                        double learningRate = 1;
                        if (cmd.hasOption("rate")) learningRate = Double.parseDouble(cmd.getOptionValue("rate"));
                        int memory = 1;
                        if (cmd.hasOption("memory")) memory = Integer.parseInt(cmd.getOptionValue("memory"));
                        algorithm = new NeuralNetworkTargeting(output, hidden, learningRate, memory);
                        break;
                    default:
                        System.err.println("Must specify a valid targeting algorithm!");
                        System.exit(1);
                }
            }
            if (cmd.hasOption("visualize")) {
                visualize = true;
            }

        } catch (ParseException e) {
            System.out.println("Error in parsing options: " + e.getMessage());
            help();
            return;
        }

        TargetingVisualizerFacade facade = new TargetingVisualizerFacade("Targeting Algorithm Visualizer");
        facade.bindTargetingAlgorithm(algorithm);
        if (visualize) {
            facade.getView().visualizeAlgorithm(algorithm);
        }
        if (filename != null) {
            facade.invokeFileControl(filename);
        } else {
            facade.invokeMouseControl();
        }
    }

    /**
     * Prints usage guide.
     */
    private static void help() {
        HelpFormatter help = new HelpFormatter();
        help.printHelp("Usage guide:", options);
    }

    /**
     * Registers command-line options.
     */
    private static void registerOptions() {

        Option positionsFile = new Option("f", "file", true, "Target position data file");
        positionsFile.setRequired(false);
        options.addOption(positionsFile);

        Option algorithm = new Option("a", "algorithm", true, "Targeting algorithm");
        algorithm.setRequired(true);
        options.addOption(algorithm);

        Option numOutputs = new Option("o", "outputs", true, "Number of classes for classification");
        numOutputs.setRequired(false);
        options.addOption(numOutputs);

        Option numHiddenNodes = new Option("h", "hidden", true, "Number of hidden nodes for neural network");
        numHiddenNodes.setRequired(false);
        options.addOption(numHiddenNodes);

        Option learningRate = new Option("r", "rate", true, "Learning rate for neural network");
        learningRate.setRequired(false);
        options.addOption(learningRate);

        Option visualize = new Option("v", "visualize", false, "Visualize targeting algorithm");
        visualize.setRequired(false);
        options.addOption(visualize);

        Option memory = new Option("m", "memory", true, "Amount of memory for neural network targeting");
        memory.setRequired(false);
        options.addOption(memory);

    }

}
