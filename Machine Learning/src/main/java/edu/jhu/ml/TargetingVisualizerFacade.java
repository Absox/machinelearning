package edu.jhu.ml;

import edu.jhu.ml.controller.GraphicalFieldController;
import edu.jhu.ml.gui.FieldView;
import edu.jhu.ml.gui.FieldWindow;
import edu.jhu.ml.io.TargetPositionDataFile;
import edu.jhu.ml.math.TargetingAlgorithm;
import edu.jhu.ml.model.FieldModel;
import edu.jhu.ml.model.OneTurretOneTargetModel;

import java.io.FileNotFoundException;

/**
 * Facade class for handling initialization of model, gui, and controllers.
 * Created by Ran on 11/23/2015.
 */
public class TargetingVisualizerFacade {

    private FieldModel model;
    private FieldWindow window;
    private GraphicalFieldController controller;

    /**
     * Constructs a facade to handle the components of a targeting algorithm visualizer.
     * @param s
     */
    public TargetingVisualizerFacade(String s) {
        OneTurretOneTargetModel fieldModel = new OneTurretOneTargetModel();
        FieldWindow fieldWindow = new FieldWindow(s, fieldModel);
        this.model = fieldModel;
        this.window = fieldWindow;
        this.controller = new GraphicalFieldController(fieldModel, fieldWindow);
    }

    /**
     * Returns the view.
     * @return FieldView that we're displaying in our window.
     */
    public FieldView getView() {
        return this.window.getView();
    }

    /**
     * Accessor for the model.
     * @return the FieldModel.
     */
    public FieldModel getModel() {
        return this.model;
    }

    /**
     * Accessor for the controller.
     * @return Controller for the fieldModel.
     */
    public GraphicalFieldController getController() {
        return this.controller;
    }

    /**
     * Binds a targeting algorithm.
     * @param algorithm Targeting algorithm.
     */
    public void bindTargetingAlgorithm(TargetingAlgorithm algorithm) {
        OneTurretOneTargetModel currentModel = (OneTurretOneTargetModel)this.model;
        currentModel.bindTargetingAlgorithm(algorithm);
    }

    /**
     * Invokes file control.
     * @param filename Name of file.
     */
    public void invokeFileControl(String filename) {
        try {
            this.controller.invokeTargetPositionsFromFile(new TargetPositionDataFile(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invokes mouse control.
     */
    public void invokeMouseControl() {
        this.controller.invokeMouseControlOfTarget();
    }

}
