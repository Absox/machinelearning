package edu.jhu.ml.gui;

import edu.jhu.ml.model.FieldModel;

import javax.swing.*;

/**
 * Window class.
 * Created by Ran on 11/23/2015.
 */
public class FieldWindow extends JFrame {

    private FieldView view;

    /**
     * Constructs a window for holding a view.
     * @param title
     * @param model
     */
    public FieldWindow(String title, FieldModel model) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FieldView fieldView = new FieldView(model);
        this.view = fieldView;
        this.setContentPane(fieldView);
        this.pack();

        this.setVisible(true);
    }

    /**
     * Accessor for the view contained within this window.
     * @return FieldView contained within this window.
     */
    public FieldView getView() {
        return this.view;
    }
}
