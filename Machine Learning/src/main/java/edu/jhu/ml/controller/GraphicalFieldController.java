package edu.jhu.ml.controller;

import edu.jhu.ml.gui.FieldView;
import edu.jhu.ml.gui.FieldWindow;
import edu.jhu.ml.io.TargetPositionDataFile;
import edu.jhu.ml.model.FieldModel;

/**
 * Controller for field, with GUI.
 * Created by Ran on 11/23/2015.
 */
public class GraphicalFieldController {

    private FieldModel model;
    private FieldWindow window;
    private GraphicalFieldPlaybackController playbackController;

    /**
     * Constructs a controller for a model.
     * @param model Model which this object controls.
     */
    public GraphicalFieldController(FieldModel model, FieldWindow window) {
        this.model = model;
        this.window = window;

        window.addKeyListener(new GraphicalFieldKeyController(this));
    }

    /**
     * Toggles playback.
     */
    public void toggle() {
        if (this.playbackController != null) {
            this.playbackController.toggle();
        }
    }

    /**
     * Uses target positions from file.
     * @param file Data file with target positions.
     */
    public void invokeTargetPositionsFromFile(TargetPositionDataFile file) {
        this.playbackController = new GraphicalFieldPlaybackController(this.model, file);
    }

    /**
     * Invokes mouse control of target.
     */
    public void invokeMouseControlOfTarget() {
        this.playbackController = new GraphicalFieldPlaybackController(this.model);
        // Note: refactor by replacing type-dependent code with State/Strategy design pattern.
        FieldView view = this.window.getView();
        view.addMouseMotionListener(new GraphicalFieldMouseController(model, view));
    }
}
