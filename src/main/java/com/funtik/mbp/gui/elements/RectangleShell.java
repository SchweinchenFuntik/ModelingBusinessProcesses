package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.Element;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;

/**
 * Created by funtik on 02.06.17.
 */
public class RectangleShell extends FocusShellElement {
    private Point leftTop, leftDown, rightTop, rightDown;

    public RectangleShell(DoubleProperty x, DoubleProperty y, DoubleProperty prefWidth, DoubleProperty prefHeight){
    }

    public Group getFocusShell(Element el) {
        return super.getFocusShell(el, false, leftTop, leftDown, rightTop, rightDown);
    }
}
