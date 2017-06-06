package com.funtik.mbp.gui.elements;

import com.google.common.graph.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;

/**
 * Created by funtik on 05.06.17.
 */
public class Arrow extends Group implements NodeElement {

    private DoubleProperty widthLine = new SimpleDoubleProperty(1.5);

    private MutableValueGraph<ArrowPoint, ArrowLine> graph = ValueGraphBuilder.directed().build();

    private void init(){
    }

    public double getWidthLine() {
        return widthLine.get();
    }

    public DoubleProperty widthLineProperty() {
        return widthLine;
    }

    @Override
    public void focus() {

    }

    @Override
    public void focusNot() {

    }
}
