package com.funtik.mbp.gui;

import com.funtik.mbp.elements.ConnectPoint;
import com.funtik.mbp.elements.Element;
import com.funtik.mbp.elements.FocusShell;
import com.funtik.mbp.gui.elements.Point;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * Created by funtik on 19.05.17.
 */
public class FocusShellElement extends Group implements FocusShell{

    public FocusShellElement(Element<Region, ?> el, boolean isAddPoint, Point... p){

        getChildren().add(el.getNode());
        if(isAddPoint) getChildren().addAll(p);
        else{

        }
    }

}
