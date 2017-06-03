package com.funtik.mbp.util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;

/**
 * Created by funtik on 29.04.17.
 */
public class ChecksDoubleProperty extends SimpleDoubleProperty {
    private SimpleDoubleProperty min;
    private SimpleDoubleProperty max;

    public ChecksDoubleProperty(){
        min = new SimpleDoubleProperty();
    }

    public ChecksDoubleProperty(double val){
        super(val);
        min = new SimpleDoubleProperty();
    }

    public ChecksDoubleProperty(double val, double min){
        super(val);
        this.min = new SimpleDoubleProperty(min);
    }

    public ChecksDoubleProperty(double val, ObservableValue<? extends Number> bindMin){
        super(val);
        min.bind(bindMin);
    }


    @Override
    public void set(double d){
        if(d>=min.get()) super.set(d);
    }

    public void setMin(double min){ this.min.set(min); }

    public double getMin(){ return min.get(); }

    public DoubleProperty minProperty(){ return min; }
}
