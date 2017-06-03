package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.Element;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by funtik on 18.05.17.
 * @author funtik
 * @version 0.1
 */
public class Rec extends Group implements Element {


    private double x, y;
    EventHandler<MouseEvent> eC = e ->{
        Element n = (Element) e.getSource();
        n.setElementX(n.getElementX() + e.getX() - x);
        n.setElementY(n.getElementY() + e.getY() - y);
        e.consume();
    };
    EventHandler<MouseEvent> eP = e ->{
        x = e.getX(); y = e.getY();
    };

    private DoubleProperty width, height;
    private Point pLeftTop, pLeftDown, pRightTop, pRightDown;
    private VBox pane;

    public Rec(){
        pane        = new VBox();
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

        pane.layoutXProperty().bind(pLeftTop.getX());
        pane.layoutYProperty().bind(pLeftTop.getY());

        width.addListener((ob, ov, nv) -> pane.setPrefWidth(nv.doubleValue()));
        height.addListener((ob, ov, nv) -> pane.setPrefHeight(nv.doubleValue()));

        Text t = new Text("adwadawdwa\nawdwad");
        Label l = new Label("sdawdwa");

        l.setTextAlignment(TextAlignment.CENTER);
        t.setTextAlignment(TextAlignment.CENTER);
        StackPane st = new StackPane(t);
        pane.getChildren().add(st);

        pane.setOnMouseClicked(e->{
       //     System.out.println("PrefWidth"+st.getPrefWidth()+"\tPrefHeight = "+st.getPrefHeight());
       //     System.out.println("Width"+st.getMinWidth()+"\tHeight = "+st.getMinHeight());
        //    System.out.println(l.getLayoutBounds().getWidth());
        });



        getChildren().addAll(top, left, down, right, pane, pLeftTop, pLeftDown, pRightDown, pRightTop);
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
        double dx = x - pLeftTop.getX().get();
        pLeftTop.getX().setValue(x);
        pRightDown.getX().setValue(pRightDown.getX().get()+dx);
    }

    @Override
    public void setElementY(double y) {
        double dy = y - pLeftTop.getY().get();
        pLeftTop.getY().setValue(y);
        pRightDown.getY().setValue(pRightDown.getY().get()+dy);
    }

    @Override
    public void setElementWidth(double width) {
        pRightTop.getX().setValue(pLeftTop.getX().get()+width);
    }

    @Override
    public void setElementHeight(double height) {
        pLeftDown.getY().setValue(pLeftTop.getY().get()+height);
    }
}
