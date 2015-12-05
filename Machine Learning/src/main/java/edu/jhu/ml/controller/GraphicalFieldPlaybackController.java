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
     * Pauses playback.
     */
    public void pause() {

    }

    /**
     * Starts playback.
     */
    public void play() {

    }

    /**
     * Resets model.
     */
    public void restart() {

    }

    private static class GraphicalFieldPlaybackRunnable implements Runnable {

        private GraphicalFieldPlaybackController controller;
        private FieldModel model;
        private TargetPositionDataFile playbackFile;


        public GraphicalFieldPlaybackRunnable(FieldModel model, GraphicalFieldPlaybackController controller) {
            this.model = model;
            this.controller = controller;
        }

        public GraphicalFieldPlaybackRunnable(FieldModel model, GraphicalFieldPlaybackController controller, TargetPositionDataFile file) {
            this.model = model;
            this.controller = controller;
            this.playbackFile = file;
            System.out.println("Creating controller with file");
        }

        public void run() {
            OneTurretOneTargetModel currentModel = (OneTurretOneTargetModel)this.model;
            if (this.playbackFile != null) {
                Iterator<RealVector> dataIterator = this.playbackFile.getPositions().iterator();
                while (true) {
                    try {
                        if (this.controller.getState() == GraphicalFieldControllerState.STARTED) {
                            if (dataIterator.hasNext()) {

                                RealVector nextPosition = dataIterator.next();
                                currentModel.moveTargetTowards(nextPosition);
                                currentModel.advance();
                            } else {
                                break;
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
