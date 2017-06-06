package com.funtik.mbp.gui.elements;

import com.funtik.mbp.util.Direction;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import javax.xml.soap.Node;
import java.util.EnumSet;

/**
 * Created by funtik on 20.04.17.
 * @author funtik
 * @version 0.3
 */
public abstract class BaseLineElement extends Region implements NodeElement {
    protected static DoubleProperty widthLineRegion   = new SimpleDoubleProperty(8);
    protected static DoubleProperty widthLine         = new SimpleDoubleProperty(1.5);
    private EnumSet<Direction> directions;
    private Rotate rotate;
    private Line line = null;
    protected DoubleProperty begX, begY, endX, endY;

    public BaseLineElement(){
        init(0, 0, 0, 0);
    }

    public BaseLineElement(double begX, double begY, double endX, double endY){
        init(begX, begY, endX, endY);
    }

    private void init(double begX, double begY, double endX, double endY){
        this.line       = new Line();
        this.rotate     = new Rotate();
        this.begX       = new SimpleDoubleProperty();
        this.begY       = new SimpleDoubleProperty();
        this.endX       = new SimpleDoubleProperty();
        this.endY       = new SimpleDoubleProperty();
        prefWidthProperty().bind(widthLineRegion);
        line.layoutXProperty().bind(Bindings.divide(widthLineRegion, 2));
        getTransforms().add(rotate);
//        setElementY(begY);

        line.endYProperty().bind(Bindings.subtract(heightProperty(), 2));
        line.strokeWidthProperty().bind(widthLine);
        rotate.pivotXProperty().bind(Bindings.divide(widthProperty(), 2));
        layoutXProperty().bind(Bindings.subtract(this.begX, rotate.pivotXProperty()));
        layoutYProperty().bind(this.begY);
        this.begX.addListener((ob, ov, nv) -> updateLine(nv.doubleValue(), getBegY(), getEndX(), getEndY()));
        this.endX.addListener((ob, ov, nv) -> updateLine(getBegX(), getBegY(), nv.doubleValue(), getEndY()));
        this.begY.addListener((ob, ov, nv) -> updateLine(getBegX(), nv.doubleValue(), getEndX(), getEndY()));
        this.endY.addListener((ob, ov, nv) -> updateLine(getBegX(), getBegY(), getEndX(), nv.doubleValue()));

        updateLine(begX, begY, endX, endY);
        getChildren().addAll(line);
        // beg and end add na WorkPane
    }

    protected void updateLine(double begX, double begY, double endX, double endY){
        this.begX.set(begX); this.begY.set(begY);
        this.endX.set(endX); this.endY.set(endY);
        directions = Direction.getDirections(begX, begY, endX, endY);
        if(directions.size()>1) setHeight(getLengthLine(begX, begY, endX, endY));
        else if(directions.size() == 1){
            Direction d = directions.iterator().next();
            boolean b = false;
            switch (d){
                case TOP:   rotate.setAngle(-180);          break;
                case DOWN:  rotate.setAngle(0);             break;
                case LEFT:  rotate.setAngle(90);  b=true;   break;
                case RIGHT: rotate.setAngle(-90); b=true;   break;
            }
            setHeight(b ? Math.abs(begX-endX):Math.abs(begY-endY));
        }

    }

    /**
     * Определяет длину линии когда Directions == 2
     * @param begX
     * @param begY
     * @param endX
     * @param endY
     * @return
     */
    protected abstract double getLengthLine(double begX, double begY, double endX, double endY);

    public void setLengthLine(double l){
        setHeight(l);
    }

    private boolean isNotUpdate(double begX, double begY, double endX, double endY){
        return  this.begX.get()==begX && this.begY.get()==begY &&
                this.endX.get()==endX && this.endY.get()==endY;
    }

    public void setPoint(double begX, double begY, double endX, double endY){
        updateLine(begX, begY, endX, endY);
    }

    public void setBegPoint(double begX, double begY){
        updateLine(begX, begY, endX.get(), endY.get());
    }

    public void setEndPoint(double endX, double endY){
        updateLine(begX.get(), begY.get(), endX, endY);
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

    public static void setWidthLineRegion(double w){
        widthLineRegion.setValue(w);
    }

    public static void setWidthLine(double w){
        widthLine.setValue(w);
    }

    public EnumSet<Direction> getDirections() {
        return directions;
    }

    public Rotate getLineRotate() {
        return rotate;
    }

    public double getBegX() {
        return begX.get();
    }

    public DoubleProperty begXProperty() {
        return begX;
    }

    public double getBegY() {
        return begY.get();
    }

    public DoubleProperty begYProperty() {
        return begY;
    }

    public double getEndX() {
        return endX.get();
    }

    public DoubleProperty endXProperty() {
        return endX;
    }

    public double getEndY() {
        return endY.get();
    }

    public DoubleProperty endYProperty() {
        return endY;
    }
}
