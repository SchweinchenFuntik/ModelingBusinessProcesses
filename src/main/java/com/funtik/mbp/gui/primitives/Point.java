package com.funtik.mbp.gui.primitives;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;

/**
 * Created by funtik on 02.04.17.
 * при создании АрровПоинт прдумать переделать
 * @author Funtik
 * @version 0.8
 */
public class Point extends StackPane implements Element, ConnectPoint {

    protected SimpleObjectProperty<Shape> shape;
    protected static SimpleDoubleProperty szToCenter = new SimpleDoubleProperty(4);
    private SimpleDoubleProperty xCenter;
    private SimpleDoubleProperty yCenter;

    public Point(double x, double y, Shape s, boolean isDefBind){
        init(x, y, s, isDefBind);
    }

    public Point(double x, double y, Shape s){
        init(x, y, s, true);
    }

    public Point(double x, double y){
        init(x, y, new Circle(), true);
    }

    public Point(){
        init(0, 0, new Circle(), true);
    }

    private void init(double x, double y, Shape s, boolean isDefBind){
        if(isDefBind && s != null){
            if(s instanceof Circle)
                ((Circle) s).radiusProperty().bind(szToCenter);
            else if(s instanceof javafx.scene.shape.Rectangle) {
                NumberBinding nb = Bindings.multiply(szToCenter, 2);
                ((Rectangle) s).heightProperty().bind(nb);
                ((Rectangle) s).widthProperty().bind(nb);
            }
        }
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
        getStyleClass().add("point");
        getChildren().add(shape.get());
    }
    public void setPointShape(Shape s){
        shape.setValue(s);
    }

    public static void setSizePoint(double size){
        szToCenter.set(size);
    }
    public static double getSizePoint(){
        return szToCenter.get();
    }

    public static DoubleProperty szToCenterProperty(){ return szToCenter; } ///???????

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
    public void focus() {}

    @Override
    public void focusNot() {}
}
