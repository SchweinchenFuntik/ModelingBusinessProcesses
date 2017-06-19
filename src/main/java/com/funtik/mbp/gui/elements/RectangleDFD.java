package com.funtik.mbp.gui.elements;

import javafx.scene.layout.AnchorPane;

/**
 * Created by funtik on 15.06.17.
 */
public class RectangleDFD extends Rectangle {

    public RectangleDFD(){
        setStyle("-fx-border-radius: 10;");
        AnchorPane.setTopAnchor(idPane, 5d);
        AnchorPane.setRightAnchor(idPane, 5d);
    }
}
