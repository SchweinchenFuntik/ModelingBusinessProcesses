package com.funtik.mbp.event;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.EventKey;
import com.funtik.mbp.element.EventMouse;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Created by funtik on 03.04.17.
 */
public class Dragging implements EventMouse<Node>, EventKey<Node> {

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

    @Override
    public void apply(Element el) {
        Node n = (Node) el.getNode();
        n.addEventFilter(MouseEvent.MOUSE_PRESSED, this::pressed);
        n.addEventFilter(MouseEvent.MOUSE_DRAGGED, this::dragged);
    }

    @Override
    public void unApply(Element el) {
        Node n = (Node) el.getNode();
        n.removeEventFilter(MouseEvent.MOUSE_PRESSED, this::pressed);
        n.removeEventFilter(MouseEvent.MOUSE_DRAGGED, this::dragged);
    }
}
