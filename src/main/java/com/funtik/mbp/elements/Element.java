package com.funtik.mbp.elements;

/**
 * Created by funtik on 02.04.17.
 * @author Funtik
 * @version 0.9
 */
public interface Element<TypeGUI, TypePopup> {
    // poka po defolty
    default double  getElementX(){ return 0; }
    default double  getElementY(){ return 0; }
    default double  getElementWidth(){ return 0; }
    default double  getElementHeight(){ return 0; }
    default void    setElementX(double x){}
    default void    setElementY(double y){}
    default void    setElementWidth(double width){}
    default void    setElementHeight(double height){}

    //???
    default void update(){}

    default void focus(){}
    default void focusNot(){}
    default FocusShell getFocusShell(){
        return (FocusShell) this;
    }
    default TypeGUI getNode(){
        return (TypeGUI)this;
    }
    // poka po defolty
    default void updatePopupMenu(TypePopup menu){}
    default ConnectPoint getConnectPoint(double x, double y){ return null; }
}
