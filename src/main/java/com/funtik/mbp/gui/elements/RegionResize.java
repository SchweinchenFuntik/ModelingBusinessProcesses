package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.Element;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

/**
 * Created by funtik on 18.05.17.
 * @author funtik
 * @version 0.1
 */
public class RegionResize extends Group implements Element<Node, ContextMenuEvent> {

    public DoubleProperty width, height;
    private double x, y;
    EventHandler<MouseEvent> eC = e ->{
        Element n = (Element) e.getSource();
        n.setElementX(n.getElementX() + e.getX() - x);
        n.setElementY(n.getElementY() + e.getY() - y);
    };
    EventHandler<MouseEvent> eP = e ->{
        x = e.getX(); y = e.getY();
    };

    private Point pLeftTop, pLeftDown, pRightTop, pRightDown;
    private VBox pane;

    public RegionResize(){
        width       = new SimpleDoubleProperty();
        height      = new SimpleDoubleProperty();
        pLeftTop    = new Point();
        pLeftDown   = new Point();
        pRightTop   = new Point();
        pRightDown  = new Point();
        Line    top = new Line(),
                down= new Line(),
                left = new Line(),
                right = new Line();

        top.startXProperty().bind(pLeftTop.getX());
        top.startYProperty().bind(pLeftTop.getY());
        top.endXProperty().bind(pRightTop.getX());
        top.endYProperty().bind(pRightTop.getY());

        down.startXProperty().bind(pLeftDown.getX());
        down.startYProperty().bind(pLeftDown.getY());
        down.endXProperty().bind(pRightDown.getX());
        down.endYProperty().bind(pRightDown.getY());

        left.startXProperty().bind(pLeftTop.getX());
        left.startYProperty().bind(pLeftTop.getY());
        left.endXProperty().bind(pLeftDown.getX());
        left.endYProperty().bind(pLeftDown.getY());

        right.startXProperty().bind(pRightTop.getX());
        right.startYProperty().bind(pRightTop.getY());
        right.endXProperty().bind(pRightDown.getX());
        right.endYProperty().bind(pRightDown.getY());

        pLeftTop.getX().bindBidirectional(pLeftDown.getX());
        pLeftTop.getY().bindBidirectional(pRightTop.getY());

        pRightDown.getX().bindBidirectional(pRightTop.getX());
        pRightDown.getY().bindBidirectional(pLeftDown.getY());

        pLeftTop.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pLeftTop.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);
        pLeftDown.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pLeftDown.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);
        pRightTop.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pRightTop.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);
        pRightDown.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pRightDown.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);

        width.bind(Bindings.subtract(pRightTop.getX(), pLeftTop.getX()));
        height.bind(Bindings.subtract(pLeftDown.getY(), pLeftTop.getY()));

        getChildren().addAll(top, left, down, right, pLeftTop, pLeftDown, pRightDown, pRightTop);

    }

    @Override
    public double getElementX() {
        return pLeftTop.getX().get();
    }

    @Override
    public double getElementY() {
        return pLeftTop.getY().get();
    }

    @Override
    public double getElementWidth() {
        return width.get();
    }

    @Override
    public double getElementHeight() {
        return height.get();
    }

    @Override
    public void setElementX(double x) {
        pLeftTop.getX().setValue(x);
    }

    @Override
    public void setElementY(double y) {
        pLeftTop.getY().setValue(y);
    }

    @Override
    public void setElementWidth(double width) {
        pRightTop.getX().setValue(pLeftTop.getX().get()+width);
    }

    @Override
    public void setElementHeight(double height) {
        pLeftDown.getY().setValue(pLeftTop.getY().get()+height);
    }

    @Override
    public int compareTo(Element e) {
        return 0;
    }
}
