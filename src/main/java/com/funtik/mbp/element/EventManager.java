package com.funtik.mbp.element;

import com.funtik.mbp.event.Default;
import com.funtik.mbp.event.Dragging;
import com.funtik.mbp.util.functions.Func;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by funtik on 03.04.17.
 */
public class EventManager {
    private Event def;
    private Event current; ///????
    private HashMap<String, Event> events = new HashMap<>();
    private ArrayList<String> eventsNames; //????
    private ObservableList<Element> serviceElements = FXCollections.observableArrayList();
    private WorkSpace workSpace;

    public EventManager(){
        def = new Default();
        current = def;
        events.put("Dragging", new Dragging());
        events.put("Default", def);

        serviceElements.addListener(this::addElement);
    }
    public EventManager(Event def){}
    public void addEvent(String name, Event e){
        events.put(name, e);
    }
    public void applyEvent(String name){

    }
    public void applyDefaultEvent(){}
    public void removeEvent(String name){
        events.remove(name);
    }
    public List getEventsNames(){ return eventsNames; }
    public Map getEvents(){ return events; }

    public void applyEventAll(String name, Element... elements){
        for (Element el:elements) applyEvent(name, el);
    }
    public void applyEvent(String name, Element el){
       events.get(name).apply(el);
    }

    public void loadEvents(String pack) throws  ClassNotFoundException, IllegalAccessException,
                                                InstantiationException {
        File f = new File(pack);
        String[] ls = f.list();
        for(String name:ls){
            String s = name.substring(0, name.length()-5); // .java
            Class cl = Class.forName(s);
            if(cl.isPrimitive() || cl.isAnnotation() || cl.isInterface()) return;
            events.put(s, (Event)cl.newInstance());
        }
    }

    public void addElement(ListChangeListener.Change<? extends Element> c){
        if(c.wasAdded()){
            c.getAddedSubList().forEach(o -> {
                def.apply(o);
                current.apply(o);
            });
        }
    }
}
