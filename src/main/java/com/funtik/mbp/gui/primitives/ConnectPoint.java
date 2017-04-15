package com.funtik.mbp.gui.primitives;

import javafx.beans.property.DoubleProperty;

/**
 * Created by funtik on 02.04.17.
 */
public interface ConnectPoint {
    DoubleProperty getX();
    DoubleProperty getY();
    boolean updateConnectPoint(double x, double y);
}
