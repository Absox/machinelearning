package edu.jhu.ml.io;

import edu.jhu.ml.model.OneTurretOneTargetModel;
import org.apache.commons.math3.linear.RealVector;

import java.util.*;

/**
 * Responsible for recording target positions into new data files.
 * Created by Ran on 12/9/2015.
 */
public class PositionRecorder implements Observer {

    private List<RealVector> positions;
    private boolean isRecording = false;

    /**
     * Records target positions from the given model.
     * @param model Model to record target positions from.
     */
    public PositionRecorder(OneTurretOneTargetModel model) {
        model.addObserver(this);
    }

    /**
     * Starts recording.
     */
    public void start() {
        this.positions = new LinkedList<>();
        this.isRecording = true;
    }

    /**
     * Stops recording.
     */
    public void stop() {
        this.isRecording = false;
        /*
        System.out.println("Filename to write to:");
        Scanner keyboard = new Scanner(System.in);
        String filename = keyboard.nextLine();
        keyboard.close();
        this.flushToFile(filename);
        */
        for (RealVector v : this.positions) {
            System.out.println(v);
        }
    }

    /**
     * Called by the model we're observing every model tick.
     * @param model Model we're observing.
     * @param args Arguments passed by model.
     */
    public void update(Observable model, Object args) {
        if (this.isRecording) {
            OneTurretOneTargetModel currentModel = (OneTurretOneTargetModel)model;
            this.positions.add(currentModel.getTarget().getPosition().copy());
        }
    }

    /**
     * Flushes our data to a file.
     * @param filename Filename of output file.
     */
    public void flushToFile(String filename) {

    }
}
