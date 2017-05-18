package com.funtik.mbp.gui.elements;

import com.funtik.mbp.util.Direction;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import java.util.EnumSet;

/**
 * Created by funtik on 20.04.17.
 * @author funtik
 * @version 0.3
 */
// созда BaseLineElement и наследоватся от него, также от него наследуется и ArrowLine
// ConnectionPoint beg, end
public abstract class BaseLineElement extends RegionElement {
    private static DoubleProperty widthLineRegion   = new SimpleDoubleProperty(8);
    private static DoubleProperty widthLine         = new SimpleDoubleProperty(1.5);
    private EnumSet<Direction> directions;
    private Rotate rotate;
    private Line line = null;
    private DoubleProperty begX, begY, endX, endY;

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
        line.layoutXProperty().bind(Bindings
                .divide(widthLineRegion, 2));

        getTransforms().add(rotate);
        setElementY(begY);
        line.endYProperty().bind(Bindings.subtract(heightProperty(), 2));
        line.strokeWidthProperty().bind(widthLine);
        rotate.pivotXProperty().bind(Bindings.divide(widthProperty(), 2));
        layoutXProperty().bind(Bindings.subtract(this.begX, rotate.pivotXProperty()));
        layoutYProperty().bind(this.begY);

        if(!isNotUpdate(begX, begY, endX, endY)) updateLine(begX, begY, endX, endY);
        getChildren().addAll(line);
        // beg and end add na WorkPane
    }

    private void updateLine(double begX, double begY, double endX, double endY){
        this.begX.set(begX); this.begY.set(begY);
        this.endX.set(endX); this.endY.set(endY);
        directions = Direction.getDirections(begX, begY, endX, endY);
        if(directions.size()>1) setHeight(getLengthLine(begX, begY, endX, endY));
        else {
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
        begX.set(x);
        //super.setElementX(x-widthLineRegion.get()/2);
    }

    @Override
    public void setElementY(double y) {
        begY.set(y);
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

}
