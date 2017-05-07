package com.funtik.mbp.builder;

import com.funtik.mbp.util.elements.LogicalConnectPoint;
import javafx.util.Builder;

import java.util.function.Function;

/**
 * Created by funtik on 07.05.17.
 */
public class LogicalConnectPointBuilder implements Builder<LogicalConnectPoint> {
    private LogicalConnectPoint point = new LogicalConnectPoint();

    public LogicalConnectPointBuilder setLogicFunc(Function<LogicalConnectPoint, Boolean> funclogic){
        point.setFuncLogic(funclogic);
        return this;
    }

    public LogicalConnectPointBuilder setPoint(double x, double y){
        point.getX().set(x); point.getY().set(y);
        return this;
    }

    public static LogicalConnectPointBuilder getBuilder(){
        return new LogicalConnectPointBuilder();
    }

    @Override
    public LogicalConnectPoint build() {
        return point;
    }
}
