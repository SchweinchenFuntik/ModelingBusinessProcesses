package com.funtik.mbp;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Created by funtik on 23.04.17.
 */
public class LineT extends Region {

    public LineT(double bX, double bY, double eX, double eY){
        getStyleClass().add("t");
        setLayoutX(bX); setLayoutY(bY);
        setMinHeight(eY-bY);
        setMinWidth(5);
        getChildren().add(new Region());
    }
}
