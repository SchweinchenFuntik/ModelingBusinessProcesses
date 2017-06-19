package com.funtik.mbp.gui.elements;

import com.funtik.mbp.annotacion.AddProperty;
import com.funtik.mbp.model.Diagram;
import com.funtik.mbp.util.xml.XmlData;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

/**
 * Created by funtik on 15.06.17.
 */
public class Rectangle extends BaseRectangleElement {
    protected StackPane idPane;
    protected StackPane costPane;
    protected DoubleProperty cost;
    protected SimpleObjectProperty<Diagram> parentDiagram = new SimpleObjectProperty<>();
    @AddProperty(name="decompose", type=Diagram.class, XmlData= XmlData.Type.CONTENT, isCreate = false)
    protected SimpleObjectProperty<Diagram> decompose;
    @AddProperty(name="days", type = Integer.class, isCreate = false)
    protected IntegerProperty days;
    @AddProperty(name="frequency", type = Integer.class, isCreate = false)
    protected IntegerProperty frequency;
    @AddProperty(name="costs", type=ArrayList.class, XmlData = XmlData.Type.CONTENT)
    protected ArrayList costs; // ????

    public Rectangle(boolean b){}

    public Rectangle(){
        decompose   = new SimpleObjectProperty<>();
        days        = new SimpleIntegerProperty();
        cost        = new SimpleDoubleProperty();
        Label l     = new Label(String.valueOf(id.get()));
        Label lDays = new Label(String.valueOf(cost.get()) + " $");

        dtText.setValue(20);

        cost.addListener((ob, ov, nv) ->
                lDays.setText(String.valueOf(nv.intValue()) + " $"));

        id.addListener((ob, ov, nv) ->
                l.setText(String.valueOf(nv.intValue())));

        idPane      = new StackPane(l);
        costPane    = new StackPane(lDays);

        getChildren().addAll(idPane, costPane);
    }

    public Diagram getDecompose() {
        return decompose.get();
    }

    public SimpleObjectProperty<Diagram> decomposeProperty() {
        return decompose;
    }

    public void setDecompose(Diagram decompose) {
        this.decompose.set(decompose);
    }

    public Diagram getParentDiagram() {
        return parentDiagram.get();
    }

    public SimpleObjectProperty<Diagram> parentDiagramProperty() {
        return parentDiagram;
    }

    public void setParentDiagram(Diagram parentDiagram) {
        this.parentDiagram.set(parentDiagram);
    }
}
