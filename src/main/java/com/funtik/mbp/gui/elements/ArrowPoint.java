package com.funtik.mbp.gui.elements;

import com.funtik.mbp.util.elements.LogicalConnectPoint;
import javafx.scene.shape.Shape;

/**
 * Created by funtik on 08.05.17.
 */
public class ArrowPoint extends Point {
    private LogicalConnectPoint cpStart;
    private LogicalConnectPoint cpSEnd;
    private Type type; // ????


    public enum Type{ DEFAULT, LINE, ARROW, DOUBLE_ARROW }

    public static Shape getDoubleArrow(){ return null; }
    public static Shape getArrow(){ return null; }
    public static Shape getLine(){ return null; }
}
