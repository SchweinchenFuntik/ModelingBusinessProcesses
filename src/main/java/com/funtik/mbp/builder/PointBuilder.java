package com.funtik.mbp.builder;

import com.funtik.mbp.gui.primitives.Point;
import com.funtik.mbp.util.functions.Func;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Builder;

import java.util.function.Consumer;

/**
 * Created by funtik on 20.04.17.
 * @author Funtik
 * @version 0.1
 */
public class PointBuilder implements Builder<Point> {

    private Shape shape = new Circle();
    private double x, y;
    private Func focus, notFocus;
    private Consumer bind;

    public PointBuilder setShape(Shape s){
        shape = s;
        return this;
    }

    public <T extends Shape> PointBuilder updateSizeToCenter(Consumer<T> bind){
        this.bind = bind;
        return this;
    }

    public PointBuilder setCenter(double x, double y){
        double sz = Point.getSizePoint();
        this.x = x - sz; this.y = y - sz;
        return this;
    }

    public PointBuilder setPoint(double x, double y){
        this.x = x; this.y = y;
        return this;
    }

    public PointBuilder setFuncFocus(Func focus){
        this.focus = focus;
        return this;
    }
    public PointBuilder setFuncNotFocus(Func notFocus){
        this.notFocus = notFocus;
        return this;
    }

    public static PointBuilder getBuilder(){ return new PointBuilder(); }

    @Override
    public Point build() {
        Point p = null;
        if(bind !=null) bind.accept(shape);
        if(focus!=null && notFocus!=null)
            return new Point(x, y, shape, bind==null){
                @Override public void focus() { focus.call(); }
                @Override public void focusNot() { notFocus.call(); }
            };
        else if(notFocus!=null)
            return new Point(x, y, shape, bind==null){
                @Override public void focusNot() { notFocus.call(); }
            };
         else if(focus!=null)
            return new Point(x, y, shape, bind==null){
                @Override public void focus() { focus.call(); }
            };
        return new Point(x, y, shape, bind==null);
    }
}
