package edu.jhu.ml.gui;

import edu.jhu.ml.model.FieldModel;

import javax.swing.*;
import java.awt.*;

/**
 * Window class.
 * Created by Ran on 11/23/2015.
 */
public class FieldWindow extends JFrame {

    public FieldWindow(FieldModel model) {
        this.setSize(new Dimension(1067, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
