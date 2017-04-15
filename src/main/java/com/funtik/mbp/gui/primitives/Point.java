package com.funtik.mbp.gui.primitives;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Created by funtik on 02.04.17.
 */
public class Point extends StackPane implements Element, ConnectPoint {

    protected SimpleObjectProperty<Shape> shape;
    protected static SimpleDoubleProperty szToCenter = new SimpleDoubleProperty(4);
    private SimpleDoubleProperty xCenter;
    private SimpleDoubleProperty yCenter;

    public Point(double x, double y){
        Circle c = new Circle(szToCenter.get());
        c.radiusProperty().bind(szToCenter);
        init(x, y, c);
    }
    public Point(){
        Circle c = new Circle(szToCenter.get());
        c.radiusProperty().bind(szToCenter);
        init(0, 0, c);
    }

    private void init(double x, double y, Shape s){
        shape = s!=null ? new SimpleObjectProperty<>(s):new SimpleObjectProperty<>();
        xCenter = new SimpleDoubleProperty(x + szToCenter.get());
        yCenter = new SimpleDoubleProperty(y + szToCenter.get());
        szToCenter.addListener((observable, ov, nv) -> {
            double sz = nv.doubleValue() - ov.doubleValue();
            if(sz==0) return;
            xCenter.set(xCenter.get()+sz);
            yCenter.set(yCenter.get()+sz);
        });
        xCenter.addListener((observable, ov, nv) -> setLayoutX(nv.doubleValue() - szToCenter.get()));
        yCenter.addListener((observable, ov, nv) -> setLayoutY(nv.doubleValue() - szToCenter.get()));
        layoutXProperty().addListener((observable, ov, nv) -> xCenter.setValue(nv.doubleValue() + szToCenter.get()));
        layoutYProperty().addListener((observable, ov, nv) -> yCenter.setValue(nv.doubleValue() + szToCenter.get()));
        setLayoutX(x); setLayoutY(y);
        shape.addListener((observable, ov, nv) -> {
            if(ov.equals(nv)) return;
            ObservableList<Node> ch = getChildren();
            ch.remove(ov); ch.add(nv);
        });
        getChildren().add(shape.get());
    }

    public static void setSizePoint(double size){
        szToCenter.set(size);
    }
    public static double getSizePoint(){
        return szToCenter.get();
    }

    public void setCenter(double x, double y){
        xCenter.setValue(x); yCenter.setValue(y);
    }

    @Override
    public DoubleProperty getX() {
        return xCenter;
    }

    @Override
    public DoubleProperty getY() {
        return yCenter;
    }

    @Override
    public boolean updateConnectPoint(double x, double y) {
        xCenter.setValue(x); yCenter.setValue(y);
        return true;
    }


    @Override
    public void focus() {

    }

    @Override
    public void focusNot() {

    }
}
