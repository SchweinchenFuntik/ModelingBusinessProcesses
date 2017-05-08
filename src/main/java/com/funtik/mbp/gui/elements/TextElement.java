package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.ConnectPoint;
import com.funtik.mbp.elements.Element;
import com.funtik.mbp.util.Direction;
import com.funtik.mbp.util.elements.LogicalConnectPoint;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.EnumSet;
import java.util.Iterator;

/**
 * Created by funtik on 07.05.17.
 * @author funtik
 * @version 0.01
 */
public class TextElement extends StackPane implements Element {
    private Text text;
    private ConnectPoint point = new LogicalConnectPoint();
    private double dx = 0, dy = 0;

    public TextElement(String text){
        getStyleClass().add("border");
        this.text = new Text(text);
        getChildren().add(this.text);
        // Сделать bind Layout к point
        // тоней разницу между ними

    }



    @Override
    public ConnectPoint getConnectPoint(double x, double y) {
        double  w = getElementWidth(), h = getElementHeight();

        EnumSet<Direction> d = Direction.getDirections(w/2, h/2, x, y);

        Iterator<Direction> i = d.iterator();
        double dx = 0, dy = 0, xx = 0, yy = 0;
        // поробывать еще добавить проверку w/h, тоесть учитывать длину
        while (i.hasNext()){
            switch (i.next()){
                case TOP:   dy  = y;            break;
                case LEFT:  dx  = x;            break;
                case DOWN:  dy  = (yy = h)-y;   break;
                case RIGHT: dx  = (xx = w)-x;   break;
            }
        }
        if(dx<dy) yy = y;
        else xx = x;
        this.dx = xx; this.dy = yy;
        if(!point.updateConnectPoint(xx+getElementX(), yy+getElementY()))
            return null;
        return point;
    }

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
}