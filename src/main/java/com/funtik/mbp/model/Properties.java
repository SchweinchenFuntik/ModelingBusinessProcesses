package com.funtik.mbp.model;

import javafx.beans.property.Property;

import java.util.Map;

/**
 * Created by funtik on 11.06.17.
 */
public interface Properties {
    <T extends Property> T getProperty(String name);
    Map<String, Property> getProperties();
    Map<String, Class> getClasses();
    <T extends Object> T getValue(String name);
    void setValue(String name, Object val);
}
