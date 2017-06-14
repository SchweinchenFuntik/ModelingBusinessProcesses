package com.funtik.mbp.element;

/**
 * Created by funtik on 02.04.17.
 * @author Funtik
 * @version 0.9
 */
public interface Element<TypeGUI, TypePopup> extends Comparable<Element> {
    // poka po defolty
    default double  getElementX(){ return 0; }
    default double  getElementY(){ return 0; }
    default double  getElementWidth(){ return 0; }
    default double  getElementHeight(){ return 0; }
    default void    setElementX(double x){}
    default void    setElementY(double y){}
    default void    setElementWidth(double width){}
    default void    setElementHeight(double height){}

    default void focus(){}
    default void focusNot(){}
    default FocusShell getFocusShell(){
        return (FocusShell) this;
    }
    default WorkSpace getWorkSpace(){ return null; }
    default TypeGUI getNode(){
        return (TypeGUI)this;
    }
    // poka po defolty
    default void updatePopupMenu(TypePopup menu){}
    default ConnectPoint getConnectPoint(double x, double y){ return null; }

    @Override
    default int compareTo(Element e){
        boolean isX = getElementX() > e.getElementX(),
                isY = getElementY() > e.getElementY(),
                isW = getElementWidth() > e.getElementWidth(),
                isH = getElementHeight() > e.getElementHeight();

        if((isX && !isW) || (isY && !isH)) return 1;
        // пока так
        return 0;
    }
}
