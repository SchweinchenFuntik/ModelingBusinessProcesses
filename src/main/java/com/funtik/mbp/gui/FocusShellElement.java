package com.funtik.mbp.gui;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.gui.elements.Point;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * Created by funtik on 19.05.17.
 */
public class FocusShellElement extends Group {

    public FocusShellElement(Element<Node, ?> el, boolean isAddPoint, Point... points){
        getChildren().add(el.getNode());
        if(isAddPoint) getChildren().addAll(points);
    }

}
