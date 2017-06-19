package com.funtik.mbp.controller;

import com.funtik.mbp.PaneTool;
import com.funtik.mbp.gui.ProgressSlider;
import com.funtik.mbp.gui.elements.Arrow;
import com.funtik.mbp.util.Notations;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.FlowPane;
import jfxtras.labs.scene.control.BigDecimalField;
import jfxtras.scene.control.ToggleGroupValue;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by funtik on 26.03.17.
 */
public class ToolBar implements Initializable{
    @FXML private BigDecimalField zoom;
    @FXML private FlowPane tool;
    @FXML private ProgressSlider z;
    @FXML private javafx.scene.control.ToolBar barPrimitive;


    @FXML private ToggleButton arrow;
    @FXML private ToggleButton squiggling;
    @FXML private ToggleButton rectangleIDEF0;
    @FXML private ToggleButton rectangleIDEF3;
    @FXML private ToggleButton rectangleDFD;
    @FXML private ToggleButton ExternalReferenceDFD;
    @FXML private ToggleButton DataStoreDFD;
    @FXML private ToggleButton ReferentIDEF3;
    @FXML private ToggleGroupValue<String> groups;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groups.add(arrow, null);
        groups.add(squiggling, null);
        groups.add(rectangleDFD, null);
        groups.add(rectangleIDEF0, null);
        groups.add(rectangleIDEF3, null);
        groups.add(ExternalReferenceDFD, null);
        groups.add(ReferentIDEF3, null);
        groups.add(DataStoreDFD, null);

        groups.selectedToggleProperty().addListener((ob, ov, nv) ->{
            if(ov == nv) return;
            String event = "Default";
            if(nv == arrow) event = "DrawArrow";
            if(nv == squiggling) event = "DrawSquiggling";
            if(nv == rectangleDFD || nv == rectangleIDEF0
                    || nv == rectangleIDEF3) event = "DrawRectangle";
            if(nv == ReferentIDEF3) event = "DrawReferent";
            if(nv == DataStoreDFD) event = "DrawDataStore";
            if(nv == ExternalReferenceDFD) event = "ExternalReference";
            PaneTool.<Window>getController("Window").setEvent(event);
        });

        zoom.setOnScroll((ScrollEvent e) -> {
            if(e.getDeltaY()<0) zoom.decrement();
            else zoom.increment();
        });
        z.valueProperty().addListener((ob, ol, nv) -> {
            double d = nv.doubleValue()/100;
            zoom.setNumber(BigDecimal.valueOf(d));
            PaneTool.<Window>getController("Window").setZoom(d);
        });
        zoom.numberProperty().addListener((ob, ov, nv) -> {
            PaneTool.<Window>getController("Window").setZoom(nv.doubleValue());
            z.setValue(nv.doubleValue()*100);
        });
    }

    public void setDiagramType(Notations type){
        ObservableList it = barPrimitive.getItems();
        it.clear();
        if(type == null) return;
        switch (type){
            case IDEF0:
                it.addAll(rectangleIDEF0, arrow, squiggling);
                break;
            case DFD:
                it.addAll(rectangleDFD, arrow, squiggling, ExternalReferenceDFD, DataStoreDFD);
                break;
            case IDEF3:
                it.addAll(rectangleIDEF3, arrow, squiggling, ReferentIDEF3);
                break;
        }
    }

    public void zoomIncrement(){ zoom.increment(); }
    public void zoomDecrement(){ zoom.decrement(); }
}
