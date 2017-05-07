package com.funtik.mbp.event;

import com.funtik.mbp.elements.Element;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Created by funtik on 03.04.17.
 */
public class DraggingElemnt implements EventMouse<Node>, EventKey<Node> {

    private double x, y;

    @Override
    public void dragged(MouseEvent e) {
        x = e.getX(); y = e.getY();
    }

    @Override
    public void pressed(MouseEvent e) {
        Element el = (Element) e.getSource();
        el.setElementX(el.getElementX()+e.getX()-x);
        el.setElementY(el.getElementY()+e.getY()-y);
    }
}
