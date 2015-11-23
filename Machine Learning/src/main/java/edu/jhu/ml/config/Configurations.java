package edu.jhu.ml.config;

/**
 * Configurations for program.
 * Created by Ran on 11/23/2015.
 */
public class Configurations {

    /**
     * Filename of configurations file.
     */
    private static final String CONFIG_FILENAME = "configurations.txt";

    /**
     * Singleton instance of configurations.
     */
    private static final Configurations configInstance = new Configurations(CONFIG_FILENAME);

    /**
     * Accessor for singleton.
     * @return Configurations object.
     */
    public static Configurations getConfigurations() {
        return configInstance;
    }

    /**
     * Initializes configurations from file.
     * @param filename Filename to read configurations from.
     */
    private Configurations(String filename) {
        // TODO
    }
}
