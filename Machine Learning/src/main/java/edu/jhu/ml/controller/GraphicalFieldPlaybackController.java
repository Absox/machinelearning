package edu.jhu.ml.controller;

import edu.jhu.ml.io.TargetPositionDataFile;
import edu.jhu.ml.model.FieldModel;
import edu.jhu.ml.model.OneTurretOneTargetModel;
import org.apache.commons.math3.linear.RealVector;

import java.util.Iterator;

/**
 * Controller for playback in a Graphical field.
 * Created by Ran on 12/5/2015.
 */
public class GraphicalFieldPlaybackController {

    private FieldModel model;
    private GraphicalFieldControllerState state;
    private Thread modelUpdater;
    private TargetPositionDataFile playbackFile;

    /**
     * Creates a playback controller.
     * @param model Model whose playback we're controlling.
     */
    public GraphicalFieldPlaybackController(FieldModel model) {
        this.model = model;
        this.state = GraphicalFieldControllerState.STOPPED;
        this.modelUpdater = new Thread(new GraphicalFieldPlaybackRunnable(this.model, this));
        this.modelUpdater.start();
    }

    /**
     * Creates a playback controller with file data.
     * @param model Model whose playback we're controlling.
     * @param file File from which we're reading target position data.
     */
    public GraphicalFieldPlaybackController(FieldModel model, TargetPositionDataFile file) {
        this.playbackFile = file;
        this.model = model;
        this.state = GraphicalFieldControllerState.STOPPED;
        this.modelUpdater = new Thread(new GraphicalFieldPlaybackRunnable(this.model, this, file));
        this.modelUpdater.start();
    }

    /**
     * Accessor for playback state.
     * @return Playback state of this controller.
     */
    public GraphicalFieldControllerState getState() {
        return this.state;
    }

    /**
     * Toggles playback state.
     */
    public void toggle() {
        if (this.state == GraphicalFieldControllerState.STOPPED || this.state == GraphicalFieldControllerState.PAUSED) {
            this.state = GraphicalFieldControllerState.STARTED;
        } else {
            this.state = GraphicalFieldControllerState.PAUSED;
        }
    }

    /**
     * Stops playback.
     */
    public void stop() {
        this.state = GraphicalFieldControllerState.STOPPED;
        this.modelUpdater.interrupt();
        System.out.println("Accuracy: " + this.model.getHitCounter() + "/" + this.model.getShotCounter());
    }

    /**
     * Resets model.
     */
    public void restart() {
        // TODO
    }

    /**
     * Runnable object for our thread. Handles advancing the model.
     */
    private static class GraphicalFieldPlaybackRunnable implements Runnable {

        private GraphicalFieldPlaybackController controller;
        private FieldModel model;
        private TargetPositionDataFile playbackFile;

        /**
         * Constructs a runnable.
         * @param model Model that we're controlling.
         * @param controller Controller that we're taking input from.
         */
        public GraphicalFieldPlaybackRunnable(FieldModel model, GraphicalFieldPlaybackController controller) {
            this.model = model;
            this.controller = controller;
        }

        /**
         * Constructs a runnable.
         * @param model Model that we're controlling.
         * @param controller Controller that we're taking input from.
         * @param file File that we're using target position data from.
         */
        public GraphicalFieldPlaybackRunnable(FieldModel model, GraphicalFieldPlaybackController controller, TargetPositionDataFile file) {
            this(model, controller);
            this.playbackFile = file;
            System.out.println("Creating controller with file");
        }

        /**
         * Behavior of the playback thread.
         */
        public void run() {
            OneTurretOneTargetModel currentModel = (OneTurretOneTargetModel)this.model;
            if (this.playbackFile != null) {
                Iterator<RealVector> dataIterator = this.playbackFile.getPositions().iterator();
                int playBackCount = 0;

                while (true) {
                    try {
                        if (this.controller.getState() == GraphicalFieldControllerState.STARTED) {
                            if (dataIterator.hasNext()) {
                                RealVector nextPosition = dataIterator.next();
                                currentModel.moveTargetTowards(nextPosition);
                                currentModel.advance();
                            } else {
                                playBackCount++;
                                System.out.println("Accuracy for playback # " + playBackCount + ":");
                                System.out.println(currentModel.getHitCounter() + "/" + currentModel.getShotCounter());
                                currentModel.resetShotCounters();

                                dataIterator = this.playbackFile.getPositions().iterator();
                                continue;
                            }
                        }
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        // Do nothing.
                    }
                }
            } else {
                while (true) {
                    try {
                        if (this.controller.getState() == GraphicalFieldControllerState.STARTED) {
                            currentModel.advance();
                        }
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        // Do nothing
                    }
                }
            }
        }

    }
}
