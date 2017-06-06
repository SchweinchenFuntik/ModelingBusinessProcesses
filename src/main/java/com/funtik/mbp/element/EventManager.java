package com.funtik.mbp.element;

import com.funtik.mbp.event.Dragging;
import com.funtik.mbp.util.functions.Func;

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
    private HashMap<String, Event> events = new HashMap<>();
    private HashMap<String, Func> apply;// ???? +--+-+-
    private ArrayList<String> eventsNames; //????
    private ArrayList serviceElements;

    public EventManager(){
        events.put("Dragging", new Dragging());
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
}
