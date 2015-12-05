package edu.jhu.ml.controller;

import edu.jhu.ml.model.FieldModel;

/**
 * Controller for playback in a Graphical field.
 * Created by Ran on 12/5/2015.
 */
public class GraphicalFieldPlaybackController {

    private FieldModel model;
    private GraphicalFieldControllerState state;

    /**
     *
     */
    public GraphicalFieldPlaybackController(FieldModel model) {
        this.model = model;
        this.state = GraphicalFieldControllerState.STOPPED;
    }

    public void toggle() {

    }

    public void pause() {

    }

    public void play() {

    }

    public void restart() {

    }
}
