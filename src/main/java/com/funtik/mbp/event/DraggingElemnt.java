package com.funtik.mbp.event;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Created by funtik on 03.04.17.
 */
public class DraggingElemnt implements EventMouse<Node>, EventKey<Node> {

    @Override
    public void dragged(MouseEvent e) {

    }

    @Override
    public void pressed(KeyEvent e) {
    }
}
