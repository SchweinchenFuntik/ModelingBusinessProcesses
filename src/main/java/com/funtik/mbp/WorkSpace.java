package com.funtik.mbp;

import com.funtik.mbp.elements.Element;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

/**
 * Created by funtik on 20.04.17.
 */
public class WorkSpace extends Pane{
    private final ObservableList<Element> focus = FXCollections.observableArrayList();
    private final ObservableList<Element> elements = FXCollections.observableArrayList();


}
