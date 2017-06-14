package com.funtik.mbp.model;

import com.funtik.mbp.annotacion.AddProperty;
import com.funtik.mbp.element.Element;
import com.funtik.mbp.util.Notations;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by funtik on 11.06.17.
 */
public class Diagram extends PropertiesBase {
    @AddProperty(name="name")
    private SimpleStringProperty name;

    @AddProperty(name="author")
    private SimpleStringProperty author;

    @AddProperty(name="type", type = Notations.class)
    private SimpleObjectProperty<Notations> type;

    @AddProperty(name="decompose", type = Diagram.class, XmlData = Type.CONTENT)
    private SimpleObjectProperty<Diagram> decompose;

    @AddProperty(name="pageNumber")
    private SimpleIntegerProperty pageNumber;
    @AddProperty(name="cNumber")
    private SimpleIntegerProperty cNumber;
    @AddProperty(name="nodeNumber")
    private SimpleStringProperty nodeNumber;
    @AddProperty(name="usedAt")
    private SimpleStringProperty usedAt;

    @AddProperty(name="elements", type=ArrayList.class, XmlData = Type.CONTENT, isCreate = false)
    private ObservableList<Element> elements = FXCollections.observableArrayList(); // пока так или ElementModel


    public Diagram(){}

    @Override
    public String toString() {
        return name.get();
    }
}
