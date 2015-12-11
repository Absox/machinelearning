package edu.jhu.ml.controller;

import edu.jhu.ml.gui.FieldView;
import edu.jhu.ml.model.FieldModel;
import edu.jhu.ml.model.OneTurretOneTargetModel;
import org.apache.commons.math3.linear.ArrayRealVector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Controller that handles mouse input.
 * Created by Ran on 12/5/2015.
 */
public class GraphicalFieldMouseController implements MouseMotionListener {

    private FieldModel model;
    private FieldView view;

    /**
     * Constructs a mouse controller.
     * @param model Model that this controller controls.
     * @param view View that will be sending mouse input.
     */
    public GraphicalFieldMouseController(FieldModel model, FieldView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Handles mouse drag events.
     * @param e MouseDragged event.
     */
    public void mouseDragged(MouseEvent e) {
        // Do nothing.
    }

    /**
     * Handles mouse moved events.
     * @param e MouseMoved event.
     */
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX() - view.getWidth()/2;
        int mouseY = view.getHeight()/2 - e.getY();
        double[] values = {mouseX, mouseY};
        ((OneTurretOneTargetModel)this.model).moveTargetTowards(new ArrayRealVector(values));
    }
}
