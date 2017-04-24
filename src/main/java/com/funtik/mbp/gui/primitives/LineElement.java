package com.funtik.mbp.gui.primitives;

import com.funtik.mbp.util.Direction;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

import java.util.EnumSet;
import java.util.Iterator;

/**
 * Created by funtik on 20.04.17.
 */
public class LineElement extends RegionElement {
    private EnumSet<Direction> directions;
    private double rotate = getRotate();
    private Line line = null;
    private DoubleProperty height, width = new SimpleDoubleProperty(6);

    public LineElement(double begX, double begY, double endX, double endY){
        //getStyleClass().add("border");

        setLayoutX(begX-2.5); setLayoutY(begY);
        directions = Direction.getDirections(begX, begY, endX, endY);
        System.out.println(directions);
        double w = 5, h = -1;
        if(directions.size()>1){
            Direction ver, hor;
            Iterator<Direction> i = directions.iterator();
            ver = i.next(); hor = i.next();
            double x, y;
            int al = 0;
            if(ver==Direction.LEFT){
                al = -1;
            }
            if(hor==Direction.TOP){
                al *= 40;
            } else {
                al *= 150;
            }
            setRotate(al);
        } else {
            Direction d = directions.iterator().next();
            h = Math.abs(begY-endY);
            switch (d){
                case TOP:   setRotate(0);   break;
                case DOWN:  setRotate(180); break;
                case LEFT:  setRotate(-90); break;
                case RIGHT: setRotate(90);  break;
            }
        }
        line = new Line(0, h ,0, 0);
        line.setStrokeWidth(2);
        line.setLayoutX(w/2-1);
        getChildren().add(line);
    }
}
