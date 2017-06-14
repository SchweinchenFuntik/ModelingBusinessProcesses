package com.funtik.mbp.gui.elements;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

/**
 * Created by funtik on 08.05.17.
 */
public class ArrowLine extends RegionRotate {

    private ObjectProperty<Arrow> parentArrow;
    private Line line;

    public ArrowLine(double begX, double begY, double endX, double endY){
        parentArrow = new SimpleObjectProperty<>();
        line = new Line();
        line.setStrokeLineCap(StrokeLineCap.BUTT );
        init(begX, begY, endX, endY, 8, line);
    }

    public ArrowLine(DoubleProperty begX, DoubleProperty begY, DoubleProperty endX, DoubleProperty endY){
        init(begX, begY, endX, endY);
    }
    public ArrowLine(ArrowPoint beg, ArrowPoint end){
        init(beg.getX(), beg.getY(), end.getX(), end.getY());
    }


    private void init(DoubleProperty begX, DoubleProperty begY, DoubleProperty endX, DoubleProperty endY){
        parentArrow = new SimpleObjectProperty<>();
        line = new Line();
        line.setStroke(Color.BLACK);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.setStrokeLineJoin(StrokeLineJoin.ROUND);
        line.setSmooth(true);

        parentArrow.addListener((ob, ov, nv) -> {
            if(nv == null) return;
            line.strokeWidthProperty().unbind();
            line.strokeWidthProperty().bind(nv.widthLineProperty());
        });
        init(begX.get(), begY.get(), endX.get(), endY.get(), 8, line);

        this.begX.bind(begX);
        this.begY.bind(begY);
        this.endX.bind(endX);
        this.endY.bind(endY);

        line.layoutXProperty().bind(Bindings.divide(widthRegion, 2.0));
        line.endYProperty().bind(Bindings.subtract(heightProperty(), 1.4));
    }
//
//    @Override
//    protected double getLength(double begX, double begY, double endX, double endY) {
//        System.out.println("LINE " + begX + "\t" +begY);
//        System.out.println("\t " + endX + "\t" +endY);
//        return -1;
//    }

    public Arrow getParentArrow() {
        return parentArrow.get();
    }

    public void setParentArrow(Arrow parentArrow) {
        this.parentArrow.setValue(parentArrow);
    }
}
