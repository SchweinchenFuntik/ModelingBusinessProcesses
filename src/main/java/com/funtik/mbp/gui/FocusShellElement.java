package com.funtik.mbp.gui;

import com.funtik.mbp.elements.Element;
import com.funtik.mbp.elements.FocusShell;
import com.funtik.mbp.gui.elements.Point;
import javafx.scene.Group;
import javafx.scene.layout.Region;

/**
 * Created by funtik on 19.05.17.
 */
public class FocusShellElement extends Group {

    public FocusShellElement(Element<Region, ?> el, boolean isAddPoint, Point... points){
        getChildren().add(el.getNode());
        if(isAddPoint) getChildren().addAll(points);
    }

}
