package com.funtik.mbp.element;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.EventManager;
import com.funtik.mbp.model.Diagram;
import com.funtik.mbp.model.Project;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Created by funtik on 20.04.17.
 */
public class WorkSpace extends Pane {
    private Diagram diagram;
    private final ObservableList<Element> focus = FXCollections.observableArrayList();
    private final ObservableList<Element> elements;

    private EventManager eventManager;

    public WorkSpace(){
        setPrefSize(700, 700);
        eventManager = new EventManager();
        elements = FXCollections.observableArrayList();
        addEventFilter(MouseEvent.MOUSE_CLICKED, this::focusElement);
        elements.addListener(this::updateElement);
    }

    public WorkSpace(Diagram diagram){
        setPrefSize(700, 700);
        eventManager = new EventManager();
        elements = FXCollections.observableArrayList();
        addEventFilter(MouseEvent.MOUSE_CLICKED, this::focusElement);
        elements.addListener(this::updateElement);
    }

    public EventManager getEventManager(){
        return eventManager;
    }

    private void focusElement(MouseEvent e){
        Object target = e.getTarget();
        Object src = e.getSource();
        System.out.println(e.getX() + "\t" + e.getY());
//        System.out.println(target);
//        System.out.println(src);
//        System.out.println(e.isShiftDown());
    }

    public void updateElement(ListChangeListener.Change<? extends Element> c) {
        while (c.next()) {
            if (c.wasAdded())
                for (Element e : c.getAddedSubList())
                    getChildren().add((Node) e.getNode());
            if (c.wasRemoved())
                for (Element e : c.getRemoved())
                    getChildren().remove(e.getNode());
        }
    }

    // ?????
    public void openProject(Project project){}

    public void setDiagram(Diagram d){

    }

    public Diagram getDiagram() {
        return diagram;
    }

    public List<Element> getElements() {
        return elements;
    }
}
