package edu.jhu.ml.gui;

import edu.jhu.ml.math.NeuralNetworkTargeting;
import edu.jhu.ml.math.TargetingAlgorithm;
import edu.jhu.ml.model.FieldModel;
import edu.jhu.ml.model.TrackingProjectile;
import org.apache.commons.math3.linear.RealVector;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A view for a field.
 * Created by Ran on 11/23/2015.
 */
public class FieldView extends JPanel implements Observer {

    private static final int PREFERRED_WIDTH = 1067;
    private static final int PREFERRED_HEIGHT = 600;

    private FieldModel model;
    private TargetingAlgorithm algorithm;

    private double escapeAngle;
    private boolean isRecording;

    /**
     * Constructs a view for a FieldModel.
     * @param model Model which to watch.
     */
    public FieldView(FieldModel model) {
        this.setDoubleBuffered(true);
        model.addObserver(this);
        this.model = model;
        this.escapeAngle = Math.asin(model.getTargetSpeed()/model.getProjectileSpeed());
        this.isRecording = false;
    }

    /**
     * Paints the component.
     * @param g Graphics context onto which to draw.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Clears the frame.
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        for(GraphicalRepresentation r : this.model.getDrawables()) {
            this.draw(r, g);
        }

        g.setColor(Color.black);
        int hits = this.model.getHitCounter();
        int shots = this.model.getShotCounter();
        g.drawString("Accuracy: " + hits + "/" + shots, 10, 10);

        if (this.isRecording) {
            g.setColor(Color.red);
            g.drawString("RECORDING", 10, 25);
        }

        this.drawMaximumEscapeArc(g);

        if (this.algorithm != null && this.algorithm instanceof NeuralNetworkTargeting) {
            List<NeuralNetworkTargeting.Instance> instances = ((NeuralNetworkTargeting) this.algorithm).getInstances();
            for (NeuralNetworkTargeting.Instance i : instances) {
                for (TrackingProjectile p : i.getProjectiles()) {
                    this.draw(p.getGraphicalRepresentation(), g);
                }
            }
        }

    }

    /**
     * Binds a targeting algorithm to visualize.
     * @param algorithm Algorithm to visualize.
     */
    public void visualizeAlgorithm(TargetingAlgorithm algorithm) {
        this.algorithm = algorithm;
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
        repaint();
    }

    /**
     * Draws an object.
     * @param r Graphical representation of the object to draw.
     * @param g Graphics context in which to draw it.
     */
    private void draw(GraphicalRepresentation r, Graphics g) {
        int x = this.getWidth()/2 + r.getX() - (int)(r.getRadius() / 2);
        int y = this.getHeight()/2 - r.getY() - (int)(r.getRadius() / 2);
        g.setColor(r.getColor());
        g.fillOval(x, y, (int)r.getRadius(), (int)r.getRadius());
    }

    /**
     * Draws the maximum escape arc.
     * @param g Graphics context on which to draw.
     */
    private void drawMaximumEscapeArc(Graphics g) {
        RealVector targetPosition = this.model.getTargetPositions().get(0);
        double startingAngle = Math.toDegrees(Math.atan2(targetPosition.getEntry(1), targetPosition.getEntry(0)) - escapeAngle);

        g.setColor(new Color(0f, 0f, 0f, 0.2f));
        g.fillArc(334, 100, 400, 400, (int)startingAngle, (int)Math.toDegrees(2 * escapeAngle));
    }

    /**
     * Toggles the recording flag.
     */
    public void toggleRecordingFlag() {
        this.isRecording = !this.isRecording;
        repaint();
    }

    /**
     * Gets preferred size for this view.
     * @return Preferred size.
     */
    public Dimension getPreferredSize() {
        return new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    }
}
