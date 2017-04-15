package com.funtik.mbp.gui.primitives;

import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;


/**
 * Created by funtik on 02.04.17.
 */
public class Rectangle extends Region implements Element {

    public Rectangle(double x, double y){
        init(x, y, null);
    }

    private void init(double x, double y, String text){
        getStyleClass().add("rectangle");
        setLayoutX(x); setLayoutY(y);
        setPrefSize(300, 70);
        double sz = 0;//Point.getSizePoint();
        ObservableList ch = getChildren();
        Point p = new Point();
        p.setCenter(0, 0);
        layoutXProperty().bindBidirectional(p.getX());
        layoutYProperty().bindBidirectional(p.getY());
        ch.add(p);
        p = new Point();
        p.setCenter(getPrefWidth(), 0);
        prefWidthProperty().bindBidirectional(p.getX());
        prefHeightProperty().bindBidirectional(p.getY());
        ch.add(p);
        p = new Point();
        p.setCenter(getPrefWidth(), getPrefHeight());
        prefWidthProperty().bindBidirectional(p.getX());
        prefHeightProperty().bindBidirectional(p.getY());
        ch.add(p);
        p = new Point();
        p.setCenter(0, getPrefHeight());
        //prefWidthProperty().bindBidirectional(p.getX());
        //prefHeightProperty().bindBidirectional(p.getY());
        ch.add(p);
        ch.add(new Label("adawd"));
        setPrefSize(300, 70);
    }

    @Override
    public void focus() {

    }

    @Override
    public void focusNot() {

    }
}
