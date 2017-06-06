package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.FocusShell;
import com.funtik.mbp.util.Direction;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.util.EnumSet;
import java.util.Iterator;

/**
 * Created by funtik on 03.06.17.
 */
public class RegionRotate extends Region implements NodeElement {
    protected DoubleProperty widthRegion   = new SimpleDoubleProperty(8);
    protected EnumSet<Direction> directions;
    protected Rotate rotate;
    protected Shape shape;
    protected FocusShell<Group> shell;

    protected DoubleProperty begX, begY, endX, endY;

    protected void init(double begX, double begY, double endX, double endY, double widthRegion, Shape shape){
        this.shape          = shape;
        this.rotate         = new Rotate();
        this.begX           = new SimpleDoubleProperty();
        this.begY           = new SimpleDoubleProperty();
        this.endX           = new SimpleDoubleProperty();
        this.endY           = new SimpleDoubleProperty();
        this.widthRegion    = new SimpleDoubleProperty(widthRegion);

        getTransforms().add(rotate);
        prefWidthProperty().bind(this.widthRegion);
        rotate.pivotXProperty().bind(Bindings.divide(widthProperty(), 2));
        layoutXProperty().bind(Bindings.subtract(this.begX, rotate.pivotXProperty()));
        layoutYProperty().bind(this.begY);

        this.begX.addListener(this::update);
        this.begY.addListener(this::update);
        this.endX.addListener(this::update);
        this.endY.addListener(this::update);

        updateRegion(begX, begY, endX, endY);
        getChildren().addAll(shape);
    }

    protected void updateRegion(double begX, double begY, double endX, double endY) {
        this.begX.set(begX);
        this.begY.set(begY);
        this.endX.set(endX);
        this.endY.set(endY);
        updateRegion();
    }

    protected void updateRegion(){
        directions = Direction.getDirections(begX.get(), begY.get(), endX.get(), endY.get());
        if(directions.size()==2) setHeight(getLength(begX.get(), begY.get(), endX.get(), endY.get()));
        else if(directions.size() == 1){
            Direction d = directions.iterator().next();
            boolean b = false;
            switch (d){
                case TOP:   rotate.setAngle(-180);          break;
                case DOWN:  rotate.setAngle(0);             break;
                case LEFT:  rotate.setAngle(90);  b=true;   break;
                case RIGHT: rotate.setAngle(-90); b=true;   break;
            }
            setHeight(b ? Math.abs(begX.get()-endX.get()):Math.abs(begY.get()-endY.get()));
        }
    }

    /**
     * Метод вызывается если Directions == 2. Возращает длину региона(высота)
     * @param begX
     * @param begY
     * @param endX
     * @param endY
     * @return
     */
    protected double getLength(double begX, double begY, double endX, double endY){
        double a = Math.abs(begX-endX), b = Math.abs(begY-endY);
        double angle = a<b ? ((Math.atan(a/b)*180)/Math.PI):90-((Math.atan(b/a)*180)/Math.PI);
        rotate.setAngle(getDeltaAngleDirection(angle));
        return Math.sqrt(a*a+b*b);
    }

    protected double getDeltaAngleDirection(double al){
        Iterator<Direction> i = directions.iterator();
        while(i.hasNext()){
            Direction d = i.next();
            if(d==Direction.TOP) al = 180-al;
            else if(d==Direction.RIGHT) al *= -1;
        }
        return al;
    }

    public void update(Observable ob, Number ov, Number nv){
        updateRegion();
    }

    public void setPoints(double begX, double begY, double endX, double endY){
        updateRegion(begX, begY, endX, endY);
    }

    public void setBegPoint(double begX, double begY){
        updateRegion(begX, begY, endX.get(), endY.get());
    }

    public void setEndPoint(double endX, double endY){
        updateRegion(begX.get(), begY.get(), endX, endY);
    }


    @Override
    public void setElementX(double x) {
        begX.setValue(x);
    }

    @Override
    public double getElementX() {
        return begX.get();
    }

    @Override
    public void setElementY(double y) {
        begY.setValue(y);
    }

    @Override
    public double getElementY() {
        return begY.get();
    }
}
