package com.funtik.mbp.gui.elements;

import com.funtik.mbp.util.ChecksDoubleProperty;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
/**
 * Created by funtik on 02.06.17.
 */
public class RectangleShell extends FocusShellElement {
    private Point leftTop, leftDown, rightTop, rightDown;
    private ChecksDoubleProperty width, height;

    public RectangleShell(NodeElement element, DoubleProperty x, DoubleProperty y,
                          DoubleProperty prefWidth, DoubleProperty prefHeight){
        super(element);
        width       = new ChecksDoubleProperty();
        height      = new ChecksDoubleProperty();
        width.bind(Bindings.add(x, prefWidth));
        height.bind(Bindings.add(y, prefHeight));
        width.minProperty().bind(Bindings.add(x, 10));
        height.minProperty().bind(Bindings.add(y, 10));
        leftTop     = new Point(x, y);
        rightTop    = new Point(width, y);
        leftDown    = new Point(x, height);
        rightDown   = new Point(width, height);

        element .getWorkSpace()
                .getEventManager()
                .applyEventAll("Dragging", leftTop, leftDown, rightTop, rightDown);

        super.getFocusShell(element, false, leftTop, leftDown, rightTop, rightDown);
    }

}
