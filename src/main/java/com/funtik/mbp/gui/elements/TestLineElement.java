package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.FocusShell;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

/**
 * Created by funtik on 03.06.17.
 */
public class TestLineElement extends RegionRotate {

    private double x, y;
    EventHandler<MouseEvent> eD = e ->{
        Element n = (Element) e.getSource();
        n.setElementX(n.getElementX() + e.getX() - x);
        n.setElementY(n.getElementY() + e.getY() - y);
        e.consume();
    };

    EventHandler<MouseEvent> eP = e ->{
        x = e.getX(); y = e.getY();
        e.consume();
    };

    private Point beg;
    private Point end;
    private DoubleProperty widthLine = new SimpleDoubleProperty(1.5);

    public TestLineElement(double begX, double begY, double endX, double endY){
        Line line = new Line();
        init(begX, begY, endX, endY, 8, line);
        line.strokeWidthProperty().bind(widthLine);
        line.layoutXProperty().bind(Bindings.divide(widthRegion, 2));
        line.endYProperty().bind(Bindings.subtract(heightProperty(), 2));
        beg = new Point(this.begX, this.begY);
        end = new Point(this.endX, this.endY);
        shell = new FocusShellElement(this, beg, end);

        beg.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        beg.addEventFilter(MouseEvent.MOUSE_DRAGGED, eD);
        end.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        end.addEventFilter(MouseEvent.MOUSE_DRAGGED, eD);
    }

    @Override
    public FocusShell<Group> getFocusShell() {
        return shell;
    }
}
