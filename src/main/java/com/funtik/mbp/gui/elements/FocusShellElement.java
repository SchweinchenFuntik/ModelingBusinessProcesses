package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.ConnectPoint;
import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.FocusShell;
import com.sun.istack.internal.NotNull;
import javafx.collections.ObservableList;
import javafx.scene.Group;

/**
 * Created by funtik on 31.05.17.
 */
public class FocusShellElement implements FocusShell<Group>{

    private Group group;
    private NodeElement element;

    public FocusShellElement(@NotNull NodeElement el){
        element = el;
    }

    public FocusShellElement(@NotNull NodeElement el, ConnectPoint... points){
        element = el;
        createFocusShell(el, false, points);
    }

    @Override
    public Group createFocusShell(Element el, boolean isCreateGUI, ConnectPoint... points) {
        if(el == null) return null;
        if(group == null) group = new Group();
        ObservableList children = group.getChildren();
        if(isCreateGUI)
            for(ConnectPoint p:points) children.add(new Point(p.getX(), p.getY()));
        else children.addAll(points);
        return group;
    }

    @Override
    public void addPoint(boolean isCreateGUI, ConnectPoint... points) {
        if(group == null) return;
        ObservableList children = group.getChildren();
        if(isCreateGUI)
            for(ConnectPoint p:points) children.add(new Point(p.getX(), p.getY()));
        else children.addAll(points);
    }

    @Override
    public Group getShell() {
///        group.getChildren().add(element.getNode());
        return group;
    }

}
