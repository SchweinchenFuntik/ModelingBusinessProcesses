package com.funtik.mbp.elements;


/**
 * Created by funtik on 19.05.17.
 */
public interface FocusShell<ElGroup> {
    ElGroup getFocusShell(Element el, boolean isCreateGUI, ConnectPoint... points);
    void addPoint(boolean isCreateGUI, ConnectPoint... points);
    ElGroup getShell();
}
