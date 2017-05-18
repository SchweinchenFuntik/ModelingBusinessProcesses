package com.funtik.mbp.gui.elements;

import com.funtik.mbp.util.elements.LogicalConnectPoint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Created by funtik on 08.05.17.
 */
public class ArrowPoint extends Point {
    private LogicalConnectPoint in;
    private LogicalConnectPoint out;
    private Type type;

    public enum Type{ DEFAULT, LINE, ARROW, DOUBLE_ARROW, DUGA }

    public ArrowPoint(){
        init(0, 0 , Type.DEFAULT);
    }
    public ArrowPoint(double x, double y){
        init(x, y, Type.DEFAULT);
    }
    public ArrowPoint(double x, double y, Type type){
        init(x, y, type);
    }
    public ArrowPoint(Type type){
        init(0, 0, type);
    }

    private void init(double x, double y, Type type){
        this.type = type;

        setLayoutX(x); setLayoutY(y);

        in  = new LogicalConnectPoint();
        out = new LogicalConnectPoint();

        Shape p = null;
        switch (type){
            case ARROW:         p = getArrow();         break;
            case DOUBLE_ARROW:  p = getDoubleArrow();   break;
            case LINE:          p = getLine();          break;
            case DUGA:          p = getDuga();          break;
        }
        if(p != null) setPointShape(p);
    }

    public static Shape getDoubleArrow(){ return null; }
    public static Shape getArrow(){
        return new Polygon(0, 0, szToCenterClass.get(), szToCenterClass.get()*3, szToCenterClass.get()*2, 0);
    }
    public static Shape getLine(){
        return new Line(szToCenterClass.get(), 0, szToCenterClass.get(), szToCenterClass.get()*2);
    }
    public static Shape getDuga(){

        return null;
    }
}
