package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.ConnectPoint;
import com.funtik.mbp.elements.Element;
import com.funtik.mbp.elements.FocusShell;
import javafx.collections.ObservableList;
import javafx.scene.Group;

/**
 * Created by funtik on 31.05.17.
 */
public class FocusShellElement implements FocusShell<Group>{

    private Group group;

    @Override
    public Group getFocusShell(Element el, boolean isCreateGUI, ConnectPoint... points) {
        if(el == null) return null;
        group = new Group();
        ObservableList children = group.getChildren();
        children.add(el.getNode());
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
        return group;
    }

}
