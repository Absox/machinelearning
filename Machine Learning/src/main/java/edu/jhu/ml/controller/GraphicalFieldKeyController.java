package edu.jhu.ml.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard controller for our GraphicalFieldController.
 * Created by Ran on 12/5/2015.
 */
public class GraphicalFieldKeyController implements KeyListener {

    private GraphicalFieldController controller;

    /**
     * This controller feeds controls into another controller.
     * @param controller Controller we're invoking methods on.
     */
    public GraphicalFieldKeyController(GraphicalFieldController controller) {
        this.controller = controller;
    }

    /**
     * Key press handler.
     * @param e Key press event.
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case ' ':
                this.controller.toggle();
                break;
            case 's':
                this.controller.stop();
                break;
        }
    }

    /**
     * Key release handler.
     * @param e Key release event.
     */
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Key typed handler.
     * @param e Key typed event.
     */
    public void keyTyped(KeyEvent e) {

    }

}
