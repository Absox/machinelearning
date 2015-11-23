package edu.jhu.ml.gui;

import edu.jhu.ml.model.FieldModel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * View
 * Created by Ran on 11/23/2015.
 */
public class FieldView extends JPanel implements Observer {

    private FieldModel model;

    public FieldView(FieldModel model) {
        this.setDoubleBuffered(true);
        this.model = model;
    }

    /**
     * Updates when model is updated.
     * @param o Observable that this watches.
     * @param arg Arguments.
     */
    public void update(Observable o, Object arg) {
        // TODO
    }

    /**
     * Draws an object.
     * @param g
     */
    private void draw(GraphicalRepresentation g) {
        // TODO
    }
}
