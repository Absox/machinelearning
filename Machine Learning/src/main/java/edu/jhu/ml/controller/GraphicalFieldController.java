package edu.jhu.ml.controller;

import edu.jhu.ml.gui.FieldView;
import edu.jhu.ml.gui.FieldWindow;
import edu.jhu.ml.io.TargetPositionDataFile;
import edu.jhu.ml.model.FieldModel;
import edu.jhu.ml.model.OneTurretOneTargetModel;
import org.apache.commons.math3.linear.RealVector;

import java.util.Iterator;

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
        this.playbackController = new GraphicalFieldPlaybackController(this.model);
    }

    public void toggle() {
        this.playbackController.toggle();
    }

    /**
     * Uses target positions from file.
     * @param file Data file with target positions.
     */
    public void invokeTargetPositionsFromFile(TargetPositionDataFile file) {
        if (model instanceof OneTurretOneTargetModel) {
            OneTurretOneTargetModel currentModel = (OneTurretOneTargetModel)model;
            FieldView view = this.window.getView();

            // Update model at 60 fps.
            Runnable modelUpdater = new Runnable() {
                public void run() {
                    Iterator<RealVector> dataIterator = file.getPositions().iterator();

                    while (dataIterator.hasNext()) {
                        try {
                            RealVector nextPosition = dataIterator.next();
                            currentModel.moveTargetTowards(nextPosition);
                            currentModel.advance();
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                        }
                    }

                    System.out.println("Accuracy: " + currentModel.getHitCounter() + "/" + currentModel.getShotCounter());
                }
            };

            Thread frameLock = new Thread(modelUpdater);
            frameLock.start();
        }
    }

    /**
     * Invokes mouse control of target.
     */
    public void invokeMouseControlOfTarget() {
        // Note: refactor by replacing type-dependent code with State/Strategy design pattern.
        if (model instanceof OneTurretOneTargetModel) {

            FieldView view = this.window.getView();

            view.addMouseMotionListener(new GraphicalFieldMouseController(model, view));
        }
    }


}
