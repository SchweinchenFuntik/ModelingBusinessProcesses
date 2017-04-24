package com.funtik.mbp.gui.primitives;

import javafx.scene.control.ContextMenu;
import javafx.scene.layout.Region;
import java.util.List;

/**
 * Created by funtik on 20.04.17.
 */
public class RegionElement extends Region implements Element<Region, ContextMenu>{

    public RegionElement(double x, double y, double width, double height, List childs){
        init(x, y, width, height, childs);
    }

    public RegionElement(double x, double y, double width, double height){
        init(x, y, width, height, null);
    }

    public RegionElement(double x, double y){
        init(x, y, 0, 0, null);
    }

    public RegionElement(){
        init(0, 0, 0, 0, null);
    }

    private void init(double x, double y, double width, double height, List childs){
        setLayoutX(x); setLayoutY(y); setWidth(width); setHeight(height);
        if(childs != null) getChildren().addAll(childs);
    }

    @Override public void setElementX(double x) { setLayoutX(x); }

    @Override public void setElementY(double y) { setLayoutY(y); }

    @Override public double getElementX() { return getLayoutX(); }

    @Override public double getElementY() { return getLayoutY(); }

    @Override public void setElementHeight(double height) { setHeight(height); }

    @Override public void setElementWidth(double width) { setWidth(width); }

    @Override public double getElementHeight() { return getHeight(); }

    @Override public double getElementWidth() { return getWidth(); }
}
