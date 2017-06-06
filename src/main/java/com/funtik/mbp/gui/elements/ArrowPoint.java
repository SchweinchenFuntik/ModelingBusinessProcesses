package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.ConnectPoint;
import com.funtik.mbp.util.elements.LogicalConnectPoint;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * Created by funtik on 08.05.17.
 */
public class ArrowPoint extends Point {
    public enum Type{ DEFAULT, LINE, ARROW, DOUBLE_ARROW, INTERSECTION }

    protected Type type;
    protected LogicalConnectPoint in, out;
    private ObjectProperty<Arrow> parentArrow;

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
        in          = new LogicalConnectPoint();
        out         = new LogicalConnectPoint();
        parentArrow = new SimpleObjectProperty<>();

        in.getX().bindBidirectional(super.getX());
        out.getX().bindBidirectional(super.getX());
        in.getY().bind(layoutYProperty()); /// двухстороние связывание
        out.getY().bind(Bindings.add(layoutYProperty(), widthProperty()));
        setCenter(x, y);
        parentArrow.addListener((ob, ov, nv) -> {
            if(nv == null) return;
            Shape s = shape.get();
            if(s == null) return;
            if(s instanceof Polyline) return;
            s.strokeWidthProperty().unbind();
            s.strokeWidthProperty().bind(nv.widthLineProperty());
        });

        Shape p = null;
        switch (type){
            case ARROW:         p = getArrow();         break;
            case DOUBLE_ARROW:  p = getDoubleArrow();   break;
            case LINE:          p = getLine();          break;
            case INTERSECTION:  p = getIntersection();  break;
        }
        if(p != null) setPointShape(p);
    }

    public static Shape getDoubleArrow(){
        Polygon pl = new Polygon();
        Polygon p = new Polygon();
        ObservableList<Double> ps = p.getPoints();
        ps.addAll(szToCenterClass.get(), szToCenterClass.get()*3);
        ps.addAll(0.0, 0.0);
        ps.addAll(szToCenterClass.get()*2, 0.0);
        ps.addAll(szToCenterClass.get(), szToCenterClass.get()*3);
        ps.addAll(0.0, szToCenterClass.get()*3);
        ps.addAll(szToCenterClass.get(), szToCenterClass.get()*6);
        ps.addAll(szToCenterClass.get()*2, szToCenterClass.get()*3);
        ps.addAll(szToCenterClass.get(), szToCenterClass.get()*3);
        return p;
    }

    public static Shape getArrow(){
        Polygon p = new Polygon(0, 0, szToCenterClass.get(), szToCenterClass.get()*3, szToCenterClass.get()*2, 0, 0, 0);
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

    public Arrow getParentArrow() {
        return parentArrow.get();
    }

    public void setParentArrow(Arrow parentArrow) {
        this.parentArrow.setValue(parentArrow);
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
}
