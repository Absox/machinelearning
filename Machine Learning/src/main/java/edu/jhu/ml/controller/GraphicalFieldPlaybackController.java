package edu.jhu.ml.controller;

import edu.jhu.ml.io.TargetPositionDataFile;
import edu.jhu.ml.model.FieldModel;
import edu.jhu.ml.model.OneTurretOneTargetModel;

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
     *
     */
    public GraphicalFieldPlaybackController(FieldModel model) {
        this.model = model;
        this.state = GraphicalFieldControllerState.STOPPED;
        this.modelUpdater = new Thread(new GraphicalFieldPlaybackRunnable(this.model, this));
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


        public GraphicalFieldPlaybackRunnable(FieldModel model, GraphicalFieldPlaybackController controller) {
            this.model = model;
            this.controller = controller;
        }

        public void run() {
            while (true) {
                try {
                    if (this.controller.getState() == GraphicalFieldControllerState.STARTED) {
                        ((OneTurretOneTargetModel) this.model).advance();
                    }
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }
        }

    }
}
