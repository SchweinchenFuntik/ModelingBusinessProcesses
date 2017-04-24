package com.funtik.mbp.util;

import java.util.EnumSet;

/**
 * Created by funtik on 20.04.17.
 */
public enum Direction {
    LEFT, TOP, RIGHT, DOWN;

    public static Direction getDirection(double x0, double y0, double x, double y){
        if(x0==x){
            if(y0<y) return DOWN;
            if(y0>y) return TOP;
        } else if(y0==y){
            if(x0<x) return RIGHT;
            if(x0>x) return LEFT;
        } return null;
    }

    public static EnumSet<Direction> getDirections(double x0, double y0, double x, double y){
        EnumSet<Direction> e = EnumSet.noneOf(Direction.class);
        if(x0<x) e.add(RIGHT);
        else if(x0>x) e.add(LEFT);
        if(y0<y) e.add(DOWN);
        else if(y0>y) e.add(TOP);
        return e;
    }
}