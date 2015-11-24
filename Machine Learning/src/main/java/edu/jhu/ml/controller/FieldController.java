package edu.jhu.ml.controller;

import edu.jhu.ml.gui.FieldView;
import edu.jhu.ml.gui.FieldWindow;
import edu.jhu.ml.model.FieldModel;
import edu.jhu.ml.model.OneTurretOneTargetModel;
import org.apache.commons.math3.linear.ArrayRealVector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Controller for field.
 * Created by Ran on 11/23/2015.
 */
public class FieldController {

    private FieldModel model;
    private FieldWindow window;

    /**
     * Constructs a controller for a model.
     * @param model Model which this object controls.
     */
    public FieldController(FieldModel model, FieldWindow window) {
        this.model = model;
        this.window = window;
    }

    public void invokeMouseControlOfTarget() {
        if (model instanceof OneTurretOneTargetModel) {

            OneTurretOneTargetModel currentModel = (OneTurretOneTargetModel)model;

            FieldView view = this.window.getView();

            view.addMouseMotionListener(new MouseMotionListener() {

                public void mouseMoved(MouseEvent e) {

                    int mouseX = e.getX() - view.getWidth()/2;
                    int mouseY = view.getHeight()/2 - e.getY();
                    double[] values = {mouseX, mouseY};
                    currentModel.setTargetPosition(new ArrayRealVector(values));
                    currentModel.advance();
                }

                public void mouseDragged(MouseEvent e) {

                }

            });
        }


    }
}
