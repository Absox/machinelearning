package edu.jhu.ml.gui;

import edu.jhu.ml.model.FieldModel;

import javax.swing.*;

/**
 * Window class.
 * Created by Ran on 11/23/2015.
 */
public class FieldWindow extends JFrame {

    public FieldWindow(FieldModel model) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FieldView fieldView = new FieldView(model);

        this.add(fieldView);
        this.pack();

        this.setVisible(true);

    }
}
