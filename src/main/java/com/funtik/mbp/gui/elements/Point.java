package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.ConnectPoint;
import com.funtik.mbp.elements.Element;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
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
    protected static SimpleDoubleProperty szToCenterClass = new SimpleDoubleProperty(4);
    private DoubleProperty xCenter;
    private DoubleProperty yCenter;
    private DoubleProperty szToCenter; // реализовать уникальный размер
    private BooleanProperty isDefSize;

    public Point(DoubleProperty xCenter, DoubleProperty yCenter){
        init(0, 0, null, xCenter, yCenter, true);
    }

    public Point(double x, double y, Shape s, boolean isDefBind){
        init(x, y, s, null, null,isDefBind);
    }

    public Point(double x, double y, Shape s){
        init(x, y, s, null, null, true);
    }

    public Point(double x, double y){
        init(x, y, new Circle(), null, null, true);
    }

    public Point(){
        init(0, 0, new Circle(), null, null, true);
    }

    private void init(double x, double y, Shape s, DoubleProperty xCenter, DoubleProperty yCenter, boolean isDefBind){
        shape = s!=null ? new SimpleObjectProperty<>(s):new SimpleObjectProperty<>(new Circle());
        this.xCenter = xCenter == null ? new SimpleDoubleProperty():xCenter;
        this.yCenter = yCenter == null ? new SimpleDoubleProperty():yCenter;
        szToCenter = new SimpleDoubleProperty(szToCenterClass.get());
        szToCenter.bind(szToCenterClass);

        isDefSize = new SimpleBooleanProperty(true);
        isDefSize.addListener((ob, ov, nv) -> {
            if(nv==ov) return;
            if(nv) szToCenter.bind(szToCenterClass);
            else szToCenter.unbind();
        });

        setLayoutX(x); setLayoutY(y);
        shape.addListener((observable, ov, nv) -> {
            if(ov.equals(nv)) return;
            ObservableList<Node> ch = getChildren();
            ch.remove(ov); ch.add(nv);
        });
        s = shape.get();
        if(isDefBind && s != null){
            if(s instanceof Circle)
                ((Circle) s).radiusProperty().bind(szToCenter);
            else if(s instanceof javafx.scene.shape.Rectangle) {
                NumberBinding nb = Bindings.multiply(szToCenter, 2);
                ((Rectangle) s).heightProperty().bind(nb);
                ((Rectangle) s).widthProperty().bind(nb);
            }
        }

        getChildren().add(shape.get());

        layoutXProperty().bind(Bindings.subtract(this.xCenter, szToCenter));
        layoutYProperty().bind(Bindings.subtract(this.yCenter, szToCenter));

        szToCenter.addListener((observable, ov, nv) -> {
            double sz = nv.doubleValue() - ov.doubleValue();
            if(sz==0) return;
            xCenter.set(xCenter.get()+sz);
            yCenter.set(yCenter.get()+sz);
        });
    }

    public void setPointShape(Shape s){
        shape.setValue(s);
    }

    public Shape getPointShape(){
        return shape.get();
    }

    public static void setSizePoint(double size){
        szToCenterClass.set(size);
    }
    public static double getSizePoint(){
        return szToCenterClass.get();
    }

    public static DoubleProperty szToCenterProperty(){ return szToCenterClass; } ///???????

    public void setCenter(double x, double y){
        xCenter.setValue(x); yCenter.setValue(y);
    }

    private void updateSzToCenterX(ObservableValue<? extends Number> observable, Number ov, Number nv){}
    private void updateSzToCenterY(){}

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

    @Override
    public void setElementX(double x) {
        double sz = szToCenter.get();
        xCenter.set(x+sz);
    }

    @Override
    public void setElementY(double y) {
        double sz = szToCenter.get();
        yCenter.set(y+sz);
    }

    @Override
    public double getElementX() {
        return layoutXProperty().get();
    }

    @Override
    public double getElementY() {
        return layoutYProperty().get();
    }

    @Override
    public ConnectPoint getConnectPoint(double x, double y) {
        // проверка являются кординаты правильнимы
        // еще возможно проверка на обновления текущей точки updatePoint
        return this;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+": "+getX().get()+"\t"+getY().get();
    }
}
