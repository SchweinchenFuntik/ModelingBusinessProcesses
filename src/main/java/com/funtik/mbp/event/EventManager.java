package com.funtik.mbp.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by funtik on 03.04.17.
 */
public class EventManager {
    private Event def;
    private HashMap<String, Event> events;
    private ArrayList<String> eventsNames;
    private ArrayList serviceElements;

    public EventManager(Event def){}
    public void addEvent(String name, Event e){}
    public void applyEvent(String name){}
    public void applyDefaultEvent(){}
    public void removeEvent(Event e){}
    public void removeEvent(String name){}
    public void removeEvent(Class c){}
    public List getEventsNames(){ return eventsNames; }
    public Map getEvents(){ return events; }
}
