package com.funtik.mbp.gui.elements;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Created by funtik on 15.06.17.
 */
public class RectangleIDEF3 extends Rectangle {

    StackPane bottomLeft, bottomRigth;

    public RectangleIDEF3(){
        AnchorPane.setBottomAnchor(idPane, -2d);
        AnchorPane.setLeftAnchor(idPane, 5d);
        bottomLeft = new StackPane();
        bottomRigth = new StackPane();
        bottomLeft.getStyleClass().add("border");
        bottomRigth.getStyleClass().add("border");
        bottomLeft.setStyle("-fx-border-width: 1.5 0.7 0 0;");
        bottomRigth.setStyle("-fx-border-width: 1.5 0 0 0.7;");

        HBox bottomPane = new HBox(bottomLeft, bottomRigth);
        bottomPane.prefWidthProperty().bind(prefWidthProperty());
        NumberBinding bindW = Bindings.divide(bottomPane.prefWidthProperty(), 2);
        bottomLeft.prefWidthProperty().bind(bindW);
        bottomRigth.prefWidthProperty().bind(bindW);
        bottomPane.setPrefHeight(20);
        AnchorPane.setBottomAnchor(bottomPane, 0d);
        AnchorPane.setLeftAnchor(bottomPane, 0d);
        getChildren().add(bottomPane);
    }
}
