package com.funtik.mbp.gui.primitives;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;

/**
 * Created by funtik on 02.04.17.
 */
public interface Element {
    double getLayoutX();
    double getLayoutY();
    //void updateEvents();
    default void focus(){}
    default void focusNot(){}
    default Node getNode(){ return (Node)this; }
    default void updatePopupMenu(ContextMenu menu){}
    default ConnectPoint getConnectPoint(double x, double y){ return null; }
}
