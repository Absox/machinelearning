package edu.jhu.ml;

import edu.jhu.ml.gui.FieldWindow;
import edu.jhu.ml.model.OneTurretOneTargetModel;

/**
 * Main class.
 * Created by Ran on 11/23/2015.
 */
public class Bootstrap {

    /**
     * Main method.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {

        OneTurretOneTargetModel model = new OneTurretOneTargetModel();
        FieldWindow fieldWindow = new FieldWindow(model);

    }

}
