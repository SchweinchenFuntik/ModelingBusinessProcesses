package com.funtik.mbp.model;

import com.funtik.mbp.annotacion.AddProperty;
import com.funtik.mbp.util.xml.XmlData;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

/**
 * Created by funtik on 14.06.17.
 */
public class Cost extends PropertiesBase<Cost> {
    @AddProperty(name="name")
    private SimpleStringProperty name;
    @AddProperty(name="definition")
    private SimpleDoubleProperty definition;

    public Cost(){
        super();
    }

    public void setName(String name){
        this.name.setValue(name);
    }

    public void setDefinition(double cost){
        this.definition.setValue(cost);
    }

    public String getName(){
        return name.get();
    }

    public double getDefinition(){
        return definition.get();
    }

    @Override
    public String toString() {
        return "Cost[name=" + (name == null ? "":name.get())+" definition="
                +(definition == null ? String.valueOf(0d):definition.get())+"]";
    }
}

