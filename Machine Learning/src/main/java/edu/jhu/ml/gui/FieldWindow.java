package edu.jhu.ml.gui;

import edu.jhu.ml.model.FieldModel;

import javax.swing.*;

/**
 * Window class.
 * Created by Ran on 11/23/2015.
 */
public class FieldWindow extends JFrame {

    /**
     * Constructs a window for holding a view.
     * @param title
     * @param model
     */
    public FieldWindow(String title, FieldModel model) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FieldView fieldView = new FieldView(model);
        this.setContentPane(fieldView);
        this.pack();

        this.setVisible(true);

    }
}
