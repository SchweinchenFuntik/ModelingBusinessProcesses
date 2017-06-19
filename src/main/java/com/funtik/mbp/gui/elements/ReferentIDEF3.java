package com.funtik.mbp.gui.elements;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * Created by funtik on 15.06.17.
 */
public class ReferentIDEF3 extends Rectangle{
    protected HBox bottomPane;
    public ReferentIDEF3(){
        bottomPane = new HBox();
        bottomPane.prefWidthProperty().bind(prefWidthProperty());
        bottomPane.setPrefHeight(20);
        bottomPane.getStyleClass().add("border");
        bottomPane.setStyle("-fx-border-width: 2 0 0 0;");

        AnchorPane.setBottomAnchor(bottomPane, 0d);
        AnchorPane.setLeftAnchor(bottomPane, 0d);

        getChildren().add(bottomPane);
        getChildren().remove(idPane);
    }
}
