package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.ConnectPoint;
import com.funtik.mbp.util.Direction;
import com.funtik.mbp.util.elements.LogicalConnectPoint;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * Created by funtik on 07.05.17.
 * @author funtik
 * @version 0.01
 * Перемещая это елемент двигать ConnectionPoint, который привязан к этому компоненту
 */
public class TextElement extends StackPane implements NodeElement {
    private Text text;

    // переделать под Point
    // или объеденить их
    private ConnectPoint point = new LogicalConnectPoint(){
        @Override
        public boolean updateConnectPoint(double x, double y) {
            setLayoutX(x-dx); setLayoutY(y-dx);
            return true;
        }
    };
    private double dx = 0, dy = 0;

    public TextElement(String text){
        getStyleClass().add("border");
        this.text = new Text(text);
        getChildren().add(this.text);
        point.getX().bind(Bindings.add(layoutXProperty(), dx));
        point.getY().bind(Bindings.add(layoutYProperty(), dy));
    }

    @Override
    public ConnectPoint getConnectPoint(double x, double y) {
        double  xe  = getElementX(),        ye  = getElementY(),
                w   = getElementWidth(),    h   = getElementHeight();

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
        if(!point.updateConnectPoint(xx+xe, yy+ye)) return null;
        return point;
    }

}
