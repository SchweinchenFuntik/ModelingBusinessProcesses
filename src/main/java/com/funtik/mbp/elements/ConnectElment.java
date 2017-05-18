package com.funtik.mbp.elements;

/**
 * Created by funtik on 16.05.17.
 */
public interface ConnectElment<IN extends Element, OUT extends Element> {
    default boolean connect(IN in, OUT out, double x, double y, boolean depenc){
        ConnectPoint    cpIn    = in.getConnectPoint(x, y),
                        cpOut   = out.getConnectPoint(x, y);
        if(cpIn == null || cpOut == null) return false;
        if(depenc){
            cpIn.getX().bindBidirectional(cpOut.getX());
            cpIn.getY().bindBidirectional(cpOut.getY());
        } else {
            cpIn.getX().bind(cpOut.getX());
            cpIn.getY().bind(cpOut.getY());
        }
        return true;
    }

    default boolean disconnect(IN in, OUT out, double x, double y, boolean depenc){
        ConnectPoint    cpIn    = in.getConnectPoint(x, y),
                        cpOut   = out.getConnectPoint(x, y);
        if(cpIn == null || cpOut == null) return false;
        if(depenc){
            cpIn.getX().unbindBidirectional(cpOut.getX());
            cpIn.getY().unbindBidirectional(cpOut.getY());
        } else {
            cpIn.getX().unbind();
            cpIn.getY().unbind();
        }
        return true;
    }
}
