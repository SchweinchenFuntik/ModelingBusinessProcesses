package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.ConnectPoint;
import com.funtik.mbp.util.LogicalConnectPoint;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

/**
 * Created by funtik on 08.05.17.
 */
public class ArrowPoint extends Point {
    public enum Type{ DEFAULT, LINE, ARROW, DOUBLE_ARROW, INTERSECTION }

    protected Type type; // переделать на проперти
    protected LogicalConnectPoint in, out;
    private ObjectProperty<Arrow> parentArrow;
    private Rotate rotate;
    private DoubleProperty height;
    private DoubleProperty width;

    public ArrowPoint(){
        init(0, 0 , Type.DEFAULT);
    }
    public ArrowPoint(double x, double y){
        init(x, y, Type.DEFAULT);
    }
    public ArrowPoint(double x, double y, Type type){
        init(x, y, type);
    }
    public ArrowPoint(Type type){
        init(0, 0, type);
    }

    private void init(double x, double y, Type type){
        this.type   = type;
        width       = new SimpleDoubleProperty(8);
        height      = new SimpleDoubleProperty(8);
        rotate      = new Rotate();
        in          = new LogicalConnectPoint();
        out         = new LogicalConnectPoint();
        parentArrow = new SimpleObjectProperty<>();

        getTransforms().add(rotate);
        rotate.pivotXProperty().bind(Bindings.divide(width, 2.0));
        rotate.pivotYProperty().bind(Bindings.divide(height, 2.0));

        prefWidthProperty().bind(width);
        prefHeightProperty().bind(height);


        parentArrow.addListener((ob, ov, nv) -> {
            if(nv == null) return;
            Shape s = shape.get();
            if(s == null) return;
            if(s instanceof Polygon) return;
            s.strokeWidthProperty().unbind();
            s.strokeWidthProperty().bind(nv.widthLineProperty());
        });

        Shape p = null;
        switch (type){
            case ARROW:         p = getArrow();         break;
            case DOUBLE_ARROW:  p = getDoubleArrow();   break;
            case LINE:          p = getLine();          break;
            case INTERSECTION:  p = getIntersection();  break;
            case DEFAULT:       setVisible(false);
        }
        if(p != null) setPointShape(p);
        setElementX(x); setElementY(y);
    }

    public Shape getConnectLine(){
        return  null;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public DoubleProperty rotateTProperty() {
        return rotate.angleProperty();
    }

    public Arrow getParentArrow() {
        return parentArrow.get();
    }

    public void setParentArrow(Arrow parentArrow) {
        this.parentArrow.setValue(parentArrow);
    }

//
//    @Override
//    public DoubleProperty getX() {
//        switch (type) {
//            case ARROW:
//            case DOUBLE_ARROW: return out.getX();
//            default: return super.getX();
//        }
//    }
//
//    @Override
//    public DoubleProperty getY() {
//        switch (type) {
//            case ARROW:
//            case DOUBLE_ARROW: return out.getY();
//            default: return super.getY();
//        }
//    }

    @Override
    public void setElementX(double x) {
        double sz = getWidth() / 2;
        switch (type){
            case ARROW:
                System.out.println("width = " + width.get());
                xCenter.setValue(x);
                break;
            default: xCenter.set(x+sz);
        }
    }

    public void setElementY(double y) {
        double sz = getHeight() / 2;
        System.out.println("Y = "+y);
        switch (type){
            case ARROW:
                System.out.println("height = " + height.get());
                yCenter.setValue(y);
                break;
            default: yCenter.set(y+sz);
        }
    }

    // переопределить так что бы определять
    // также учитывать type
    @Override
    public ConnectPoint getConnectPoint(double x, double y) {
        switch (type) {
            case DEFAULT: return super.getConnectPoint(x, y);
        }
        double w = getElementWidth()/2;
        return x <= w ? in:out;
    }


    public Shape getArrow(){
        //rotate.setPivotX();
        removeUpdateCenter();
        layoutXProperty().unbind();
        layoutYProperty().unbind();
        layoutXProperty().bind(Bindings.subtract(xCenter, Bindings.divide(width, 2)));
        layoutYProperty().bind(Bindings.subtract(yCenter, height));
        width.setValue(szToCenter.get()*2); height.setValue(szToCenter.get()*3);
        rotate.pivotXProperty().unbind();
        rotate.pivotYProperty().unbind();
        rotate.pivotXProperty().bind(Bindings.divide(width, 2.0));
        rotate.pivotYProperty().bind(height);
        Polygon p = new Polygon(0, 0, szToCenter.get(), szToCenter.get()*3, szToCenter.get()*2, 0, 0, 0);
        p.setSmooth(true);
        return p;
    }

    public Shape getDoubleArrow(){
        Polygon p = new Polygon();
        ObservableList<Double> ps = p.getPoints();
        ps.addAll(szToCenter.get(), szToCenter.get()*3);
        ps.addAll(0.0, 0.0);
        ps.addAll(szToCenter.get()*2, 0.0);
        ps.addAll(szToCenter.get(), szToCenter.get()*3);
        ps.addAll(0.0, szToCenter.get()*3);
        ps.addAll(szToCenter.get(), szToCenter.get()*6);
        ps.addAll(szToCenter.get()*2, szToCenter.get()*3);
        ps.addAll(szToCenter.get(), szToCenter.get()*3);
        return p;
    }

    public Shape getLine(){
        Line l = new Line(szToCenter.get(), 0, szToCenter.get(), szToCenter.get()*2);
        if(parentArrow.get() == null) l.setStrokeWidth(1.5);
        else l.strokeWidthProperty().bind(parentArrow.get().widthLineProperty());
        return l;
    }

    public Shape getIntersection(){
        Arc a = new Arc(szToCenter.get()*1.7, szToCenter.get()*1.7, szToCenter.get()*1.7, szToCenter.get()*1.7,
                0, 180);
        a.setStroke(Color.BLACK);
        a.setStyle("-fx-fill: -fx-background;");
        if(parentArrow == null) a.setStrokeWidth(1.5);
        else a.strokeWidthProperty().bind(parentArrow.get().widthLineProperty());
        return a;
    }
}
