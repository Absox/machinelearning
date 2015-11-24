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

    /**
     * Invokes mouse control of target.
     */
    public void invokeMouseControlOfTarget() {
        // Note: refactor by replacing type-dependent code with State/Strategy design pattern.
        if (model instanceof OneTurretOneTargetModel) {

            OneTurretOneTargetModel currentModel = (OneTurretOneTargetModel)model;

            FieldView view = this.window.getView();

            view.addMouseMotionListener(new MouseMotionListener() {

                public void mouseMoved(MouseEvent e) {

                    int mouseX = e.getX() - view.getWidth()/2;
                    int mouseY = view.getHeight()/2 - e.getY();
                    double[] values = {mouseX, mouseY};
                    currentModel.moveTargetTowards(new ArrayRealVector(values));
                }

                public void mouseDragged(MouseEvent e) {

                }
            });

            // Updates the model at 60 fps
            Runnable modelUpdater = new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            currentModel.advance();
                            Thread.sleep(16);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            };

            Thread frameLock = new Thread(modelUpdater);
            frameLock.start();
        }
    }


}
