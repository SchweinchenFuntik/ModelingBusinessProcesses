package com.funtik.mbp.gui.elements;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Created by funtik on 15.06.17.
 */
public class Rectangle extends BaseRectangleElement {
    protected StackPane idPane;

    public Rectangle(){
        Label l = new Label(String.valueOf(id.get()));
        id.addListener((ob, ov, nv) ->
                l.setText(String.valueOf(nv.intValue())));
        idPane = new StackPane(l);
        getChildren().add(idPane);
    }
}
