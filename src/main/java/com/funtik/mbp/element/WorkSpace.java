package com.funtik.mbp.element;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Created by funtik on 20.04.17.
 */
public class WorkSpace extends Pane {
    private final ObservableList<Element> focus = FXCollections.observableArrayList();
    private final ObservableList<Element> elements = FXCollections.observableArrayList();

    private EventManager eventManager = new EventManager();

    public WorkSpace(){
        addEventFilter(MouseEvent.MOUSE_CLICKED, this::focusElement);
    }

    public EventManager getEventManager(){
        return eventManager;
    }

    private void focusElement(MouseEvent e){
        Object target = e.getTarget();
        Object src = e.getSource();
        System.out.println(target);
        System.out.println(src);
        System.out.println(e.isShiftDown());
    }
}
