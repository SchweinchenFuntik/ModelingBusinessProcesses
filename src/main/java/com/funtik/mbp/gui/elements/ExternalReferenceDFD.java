package com.funtik.mbp.gui.elements;

import javafx.scene.layout.AnchorPane;

/**
 * Created by funtik on 15.06.17.
 */
public class ExternalReferenceDFD extends Rectangle {

    public ExternalReferenceDFD(){
        AnchorPane.setTopAnchor(idPane, 0d);
        AnchorPane.setLeftAnchor(idPane, 3d);
        setStyle("-fx-border-width: 5 2 2 5;");
    }
}
