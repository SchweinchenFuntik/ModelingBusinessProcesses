package com.funtik.mbp.element;


/**
 * Created by funtik on 19.05.17.
 */
public interface FocusShell<ElGroup> {
    ElGroup createFocusShell(Element el, boolean isCreateGUI, ConnectPoint... points);
    void addPoint(boolean isCreateGUI, ConnectPoint... points);
    ElGroup getShell();
}
