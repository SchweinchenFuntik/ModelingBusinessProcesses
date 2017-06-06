package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.FocusShell;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Created by funtik on 02.06.17.
 */
public class AnchorElement extends AnchorPane implements NodeElement {
    @Override
    public double getElementWidth() {
        return getWidth();
    }

    @Override
    public double getElementHeight() {
        return getHeight();
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
