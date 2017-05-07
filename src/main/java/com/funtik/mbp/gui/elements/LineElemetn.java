package com.funtik.mbp.gui.elements;

import com.funtik.mbp.util.Direction;
import java.util.Iterator;

/**
 * Created by funtik on 07.05.17.
 */
public class LineElemetn extends BaseLineElement {

    public LineElemetn(){}

    public LineElemetn(double begX, double begY, double endX, double endY) {
        super(begX, begY, endX, endY);
    }

    protected double getDeltaAngleDirection(double al){
        Iterator<Direction> i = getDirections().iterator();
        while(i.hasNext()){
            Direction d = i.next();
            if(d==Direction.TOP) al = 180-al;
            else if(d==Direction.RIGHT) al *= -1;
        }
        return al;
    }

    protected double getLengthLine(double begX, double begY, double endX, double endY){
        double a = Math.abs(begX-endX), b = Math.abs(begY-endY);
        double angle = a<b ? ((Math.atan(a/b)*180)/Math.PI):90-((Math.atan(b/a)*180)/Math.PI);
        getLineRotate().setAngle(getDeltaAngleDirection(angle));
        return Math.sqrt(a*a+b*b);
    }

}
