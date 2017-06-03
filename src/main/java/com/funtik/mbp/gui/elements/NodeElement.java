package com.funtik.mbp.gui.elements;

import com.funtik.mbp.WorkSpace;
import com.funtik.mbp.elements.Element;
import com.funtik.mbp.elements.FocusShell;
import com.funtik.mbp.util.ref.ClassRef;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;

/**
 * Created by funtik on 02.06.17.
 * @author funtik
 * @version 0.1
 */
public interface NodeElement extends Element<Node, ContextMenu> {
    @Override
    default double getElementX() {
        return getNode().getLayoutX();
    }

    @Override
    default double getElementY() {
        return getNode().getLayoutY();
    }

    @Override
    default void setElementX(double x) {
        getNode().setLayoutX(x);
    }

    @Override
    default void setElementY(double y) {
        getNode().setLayoutY(y);
    }

    @Override
    default double getElementWidth() {
        return ClassRef.calcMethod("getWidth", getNode(), double.class);
    }

    @Override
    default double getElementHeight() {
        return ClassRef.calcMethod("getHeight", getNode(), double.class);
    }

    @Override
    default void setElementWidth(double width) {
        ClassRef.calcMethod("setPrefWidth", getNode(), void.class, width);
    }

    @Override
    default void setElementHeight(double height) {
        ClassRef.calcMethod("setPrefHeight", getNode(), void.class, height);
    }

    @Override
    default void focus() {
        Parent parent = getNode().getParent();
        if(!(parent instanceof WorkSpace)) return;
        WorkSpace p = (WorkSpace)  parent;
        FocusShell<Group> shell = getFocusShell();
        if(shell == null) return;
        p.getChildren().remove(getNode());
        p.getChildren().add(shell.getShell());
    }
}
