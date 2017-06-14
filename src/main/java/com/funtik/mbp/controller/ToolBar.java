package com.funtik.mbp.controller;

import com.funtik.mbp.PaneTool;
import com.funtik.mbp.gui.ProgressSlider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroupValue t = new ToggleGroupValue();


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

    public void zoomIncrement(){ zoom.increment(); }
    public void zoomDecrement(){ zoom.decrement(); }
}
