package edu.jhu.ml.gui;

import edu.jhu.ml.model.FieldModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * View
 * Created by Ran on 11/23/2015.
 */
public class FieldView extends JPanel implements Observer {

    private static final int PREFERRED_WIDTH = 1067;
    private static final int PREFERRED_HEIGHT = 600;

    private FieldModel model;

    /**
     * Constructs a view for a FieldModel.
     * @param model Model which to watch.
     */
    public FieldView(FieldModel model) {
        this.setDoubleBuffered(true);

        model.addObserver(this);
        this.model = model;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Clears the frame.
        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        for(GraphicalRepresentation r : this.model.getDrawables()) {
            this.draw(r, g);
        }
    }

    /**
     * Draws the field view.
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
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
     * @param r Graphical representation of the object to draw.
     * @param g Graphics context in which to draw it.
     */
    private void draw(GraphicalRepresentation r, Graphics g) {
        // TODO
        g.setColor(r.getColor());
        g.fillOval(r.getX(), r.getY(), (int)r.getRadius(), (int)r.getRadius());
    }

    /**
     * Gets preferred size for this view.
     * @return Preferred size.
     */
    public Dimension getPreferredSize() {
        return new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    }
}
