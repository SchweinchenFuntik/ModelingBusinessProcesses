package com.funtik.mbp.util;

import com.funtik.mbp.element.ConnectPoint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.function.Function;

/**
 * Created by funtik on 07.05.17.
 * @author funtik
 * @version 0.1
 */
public class LogicalConnectPoint implements ConnectPoint {
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private Function<LogicalConnectPoint, Boolean> funcLogic;

    public LogicalConnectPoint(){}

    public LogicalConnectPoint(Function<LogicalConnectPoint, Boolean> func){
        funcLogic = func;
    }

    @Override
    public DoubleProperty getX() {
        return x;
    }

    @Override
    public DoubleProperty getY() {
        return y;
    }

    @Override
    public boolean updateConnectPoint(double x, double y) {
        if(!(funcLogic !=null ? funcLogic.apply(this):true)) return false;
        this.x.set(x); this.y.set(y);
        return true;
    }

    public void setFuncLogic(Function<LogicalConnectPoint, Boolean> func) {
        this.funcLogic = func;
    }

    @Override
    public String toString() {
        return "x = "+x.get()+"\ty = "+y.get();
    }
}
