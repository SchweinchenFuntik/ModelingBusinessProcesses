package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.gui.FocusShellElement;
import com.funtik.mbp.util.Direction;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Iterator;

/**
 * Created by funtik on 07.05.17.
 */
public class LineElement extends BaseLineElement {
    // тут эти точки
    private Point beg;
    private Point end;

    private double x, y;
    EventHandler<MouseEvent> eD = e ->{
        Element n = (Element) e.getSource();
        n.setElementX(n.getElementX() + e.getX() - x);
        n.setElementY(n.getElementY() + e.getY() - y);
        e.consume();
    };
    EventHandler<MouseEvent> eP = e ->{
        x = e.getX(); y = e.getY();
    };

    public LineElement(double begX, double begY, double endX, double endY) {
        super(begX, begY, endX, endY);

        beg = new Point(this.begX, this.begY);
        end = new Point(this.endX, this.endY);

        beg.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        beg.addEventFilter(MouseEvent.MOUSE_DRAGGED, eD);
        end.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        end.addEventFilter(MouseEvent.MOUSE_DRAGGED, eD);
        // переписать линию так что бы у нее были персоналные настройки
        //beg.layoutXProperty().bind();
        //beg.getX().bind(Bindings.add(layoutXProperty(), Bindings.divide(widthProperty(), 2)));
        //beg.getY().bind(layoutYProperty());

       // end.getX()

        //getChildren().addAll(beg, end);
    }

    public Point getBeg() {
        return beg;
    }

    public Point getEnd() {
        return end;
    }

    protected double getDeltaAngleDirection(double al){
        Iterator<Direction> i = getDirections().iterator();
        while(i.hasNext()){
            Direction d = i.next();
            if(d==Direction.TOP) al = 180-al;
            else if(d==Direction.RIGHT) al *= -1;
        }
        return al;
    }

    protected double getLengthLine(double begX, double begY, double endX, double endY){
        double a = Math.abs(begX-endX), b = Math.abs(begY-endY);
        double angle = a<b ? ((Math.atan(a/b)*180)/Math.PI):90-((Math.atan(b/a)*180)/Math.PI);
        getLineRotate().setAngle(getDeltaAngleDirection(angle));
        return Math.sqrt(a*a+b*b);
    }

    FocusShellElement fs;
    @Override
    public void focus() {
        fs = new com.funtik.mbp.gui.FocusShellElement(this, true, beg, end);
    }

    public FocusShellElement getFs() {
        return fs;
    }
}
