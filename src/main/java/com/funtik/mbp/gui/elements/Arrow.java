package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.WorkSpace;
import com.google.common.graph.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Created by funtik on 05.06.17.
 */
public class Arrow extends Group implements NodeElement {

    private DoubleProperty widthLine = new SimpleDoubleProperty(1.5);
    private MutableValueGraph<ArrowPoint, ArrowLine> graph = ValueGraphBuilder.directed().build();

    public Arrow(){}

    public void addLine(ArrowPoint beg, ArrowPoint end, ArrowLine line){
        graph.putEdgeValue(beg, end, line);
        if(getChildren().isEmpty()) getChildren().addAll(line, beg, end);
        else getChildren().addAll(line, end);
    }

    public double getWidthLine() {
        return widthLine.get();
    }

    public DoubleProperty widthLineProperty() {
        return widthLine;
    }

    public MutableValueGraph<ArrowPoint, ArrowLine> getGraph() {
        return graph;
    }

    @Override
    public void focus() {
        graph.nodes().forEach(ap -> {
            if(ap.getType() == ArrowPoint.Type.DEFAULT) ap.setVisible(true);
        });
    }

    @Override
    public void focusNot() {
        graph.nodes().forEach(ap -> {
            if(ap.getType() == ArrowPoint.Type.DEFAULT) ap.setVisible(false);
        });
    }

}
