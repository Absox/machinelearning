package edu.jhu.ml.io;

import edu.jhu.ml.model.OneTurretOneTargetModel;
import org.apache.commons.math3.linear.RealVector;

import java.util.*;

/**
 * Created by Ran on 12/9/2015.
 */
public class PositionRecorder implements Observer {

    private List<RealVector> positions;
    private boolean isRecording = false;

    public PositionRecorder(OneTurretOneTargetModel model) {
        model.addObserver(this);
    }

    public void start() {
        this.positions = new LinkedList<>();
        this.isRecording = true;
    }

    public void stop() {
        this.isRecording = false;
        Scanner keyboard = new Scanner(System.in);
    }

    public void update(Observable model, Object args) {

    }
}
