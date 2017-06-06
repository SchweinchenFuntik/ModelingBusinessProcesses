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
        max = new SimpleDoubleProperty();
    }

    public ChecksDoubleProperty(double val, double min){
        super(val);
        this.min = new SimpleDoubleProperty(min);
    }
    public ChecksDoubleProperty(double val, double min, double max){
        super(val);
        this.min = new SimpleDoubleProperty(min);
        this.max = new SimpleDoubleProperty(max);
    }


    @Override
    public void set(double d){
        if(d>=min.get() && d<=max.get()) super.set(d);
    }

    public void setMin(double min){ this.min.set(min); }

    public double getMin(){ return min.get(); }

    public void setMax(double max){ this.max.set(max); }

    public double getMax(){ return max.get(); }

    public DoubleProperty minProperty(){ return min; }

    public DoubleProperty maxProperty(){ return max; }
}
