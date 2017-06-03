package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.Element;
import com.funtik.mbp.elements.FocusShell;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Created by funtik on 02.06.17.
 */
public class AnchorElement extends AnchorPane implements Element<AnchorPane, ContextMenu> {
    @Override
    public double getElementX() {
        return getLayoutX();
    }

    @Override
    public double getElementY() {
        return getLayoutY();
    }

    @Override
    public double getElementWidth() {
        return getWidth();
    }

    @Override
    public double getElementHeight() {
        return getHeight();
    }

    @Override
    public void setElementX(double x) {
        setLayoutX(x);
    }

    @Override
    public void setElementY(double y) {
        setLayoutY(y);
    }

    @Override
    public void setElementWidth(double width) {
        setPrefWidth(width);
    }

    @Override
    public void setElementHeight(double height) {
        setPrefHeight(height);
    }

    @Override
    public void focus() {
        Pane p = (Pane) getNode().getParent();
        p.getChildren().remove(getNode());
        FocusShell<Group> shell = getFocusShell();
        p.getChildren().add(shell.getShell());
    }

    @Override
    public void focusNot() {
        Pane p = (Pane) getNode().getParent();
        p.getChildren().remove(getFocusShell().getShell());
        p.getChildren().add(getNode());
    }

    @Override
    public FocusShell getFocusShell() {
        return null;
    }
}
