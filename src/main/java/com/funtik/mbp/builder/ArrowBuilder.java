package com.funtik.mbp.builder;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.WorkRegion;
import com.funtik.mbp.element.WorkSpace;
import com.funtik.mbp.gui.elements.Arrow;
import com.funtik.mbp.gui.elements.ArrowLine;
import com.funtik.mbp.gui.elements.ArrowPoint;
import com.funtik.mbp.util.Direction;
import javafx.util.Builder;

import java.util.*;

/**
 * Created by funtik on 08.06.17.
 */
public class ArrowBuilder implements Builder<Arrow> {

    private ArrayList<ArrowPoint> begs = new ArrayList<>();
    private ArrayList<ArrowPoint> ends = new ArrayList<>();
    private ArrayList<ArrowLine> lines = new ArrayList<>();
    private WorkSpace wp;
    private Arrow a = new Arrow();
    private Element in, out;

    public ArrowBuilder moveToArrow(double xBeg, double yBeg, double xEnd, double yEnd){
        List<Element> elements = wp.getElements();
        WorkRegion region = new WorkRegion(xBeg, yBeg, xEnd, yEnd, wp);
        List<Element> elementsReg = region.getElements();
        elementsReg.remove(in);
        elementsReg.remove(out);
        if(elementsReg.size() == 0) primaryBuild(xBeg, yBeg, xEnd, yEnd);
        else {
            ArrayList tl = new ArrayList(), tr = new ArrayList(), dl = new ArrayList(), dr = new ArrayList<>();
            double xCenter = (xEnd - xBeg) / 2, yCenter = (yEnd - yBeg) / 2;
            elementsReg.forEach(e -> {
                EnumSet<Direction> dir = Direction.getDirections(xCenter, yCenter, e.getElementX(), e.getElementY());
                Object [] d =  dir.toArray();


            });
            System.out.print(elementsReg);
        }
        return this;
    }

    private void primaryBuild(double xBeg, double yBeg, double xEnd, double yEnd){
        double xC = xBeg + (xEnd - xBeg) / 2;
        ArrowPoint b = create(xBeg, yBeg, xC, yBeg);
        b = create(b, xC, yEnd, false);
        create(b, xEnd, yEnd, true);
    }

    private void primaryBuild(double xBeg, double yBeg, double xCenter, double yCenter, double xEnd, double yEnd){
        ArrowPoint b = create(xBeg, yBeg, xCenter, yCenter);
        b = create(b, xCenter, yCenter, false);
        create(b, xEnd, yEnd, true);
    }

    private ArrowPoint create(double xBeg, double yBeg, double xEnd, double yEnd){
//        System.out.println(xBeg + "\t" +yBeg);
//        System.out.println(xEnd + "\t" +yEnd);
        ArrowPoint b = new ArrowPoint(xBeg, yBeg);
        ArrowPoint e = new ArrowPoint(xEnd, yEnd);
        ArrowLine l = new ArrowLine(b, e);
        e.rotateProperty().bind(l.rotateProperty());
        a.addLine(b, e, l);
        return e;
    }

    private ArrowPoint create(ArrowPoint b, double xEnd, double yEnd, boolean isEnd){
        ArrowPoint e = new ArrowPoint(xEnd, yEnd, isEnd ? ArrowPoint.Type.ARROW: ArrowPoint.Type.DEFAULT);
        ArrowLine l = new ArrowLine(b, e);
        e.rotateTProperty().bind(l.rotateTProperty());
        a.addLine(b, e, l);
        return e;
    }

    public ArrowBuilder setElements(Element in, Element out){
        this.in = in;
        this.out = out;
        return this;
    }


    public ArrowBuilder setOut(Element out){
        this.out = out;
        return this;
    }

    public ArrowBuilder setIn(Element in){
        this.in = in;
        return this;
    }


    public ArrowBuilder(WorkSpace workSpace){
        wp = workSpace;
    }
    public ArrowBuilder(){}
    public static ArrowBuilder getBuilder(WorkSpace wp){
        return new ArrowBuilder(wp);
    }
    public static ArrowBuilder getBuilder(){
        return new ArrowBuilder();
    }

    @Override
    public Arrow build() {
        return a;
    }
}
