package com.funtik.mbp.gui.elements;

import javafx.scene.layout.AnchorPane;

/**
 * Created by funtik on 15.06.17.
 */
public class RectangleIDEF0 extends Rectangle {
    private static int ID = 0;

    public RectangleIDEF0(){
        AnchorPane.setBottomAnchor(idPane, 1d);
        AnchorPane.setRightAnchor(idPane, 5d);

        AnchorPane.setBottomAnchor(costPane, 1d);
        AnchorPane.setLeftAnchor(costPane, 5d);
        id.setValue(ID++);
    }

}
