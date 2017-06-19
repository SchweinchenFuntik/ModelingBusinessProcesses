package com.funtik.mbp.controller;

import com.funtik.mbp.util.Notations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ScrollEvent;
import jfxtras.scene.control.ToggleGroupValue;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by funtik on 19.06.17.
 */
public class Decompose implements Initializable {
    @FXML private Spinner<Integer> amountElement;
    @FXML private RadioButton rbIDEF0, rbDFD, rbIDEF3;
    @FXML private ToggleGroupValue groups;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groups.add(rbIDEF0, null);
        groups.add(rbDFD, null);
        groups.add(rbIDEF3, null);
        amountElement.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7, 3));
    }

    public void scroll(ScrollEvent e){
        if(e.getDeltaY()>0) amountElement.increment();
        else  amountElement.decrement();
    }

    public int getAmountElements(){
        return amountElement.getValue();
    }

    public Notations getDiagramType(){
        if(groups.getSelectedToggle() == rbIDEF0)   return Notations.IDEF0;
        if(groups.getSelectedToggle() == rbDFD)     return Notations.DFD;
        if(groups.getSelectedToggle() == rbIDEF3)   return Notations.IDEF3;
        return null;
    }
}
