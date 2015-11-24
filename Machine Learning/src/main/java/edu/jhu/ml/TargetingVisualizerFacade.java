package edu.jhu.ml;

import edu.jhu.ml.controller.FieldController;
import edu.jhu.ml.gui.FieldWindow;
import edu.jhu.ml.model.FieldModel;
import edu.jhu.ml.model.OneTurretOneTargetModel;

/**
 * Facade class for handling initialization of model, gui, and controllers.
 * Created by Ran on 11/23/2015.
 */
public class TargetingVisualizerFacade {

    private FieldModel model;
    private FieldWindow window;
    private FieldController controller;

    /**
     * Constructs a facade to handle the components of a targeting algorithm visualizer.
     * @param s
     */
    public TargetingVisualizerFacade(String s) {
        OneTurretOneTargetModel fieldModel = new OneTurretOneTargetModel();
        FieldWindow fieldWindow = new FieldWindow(s, fieldModel);
        this.model = fieldModel;
        this.window = fieldWindow;
        this.controller = new FieldController(fieldModel);
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
    public FieldController getController() {
        return this.controller;
    }

}
