package com.funtik.mbp.element;

import com.funtik.mbp.util.Direction;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * Created by funtik on 07.06.17.
 */
public class WorkRegion {

    private ArrayList<Element> elements = new ArrayList<>();
    private ArrayList<Element>  tLeft = new ArrayList<>(), tRight = new ArrayList<>(),
                                dLeft = new ArrayList<>(), dRight = new ArrayList<>();
    private ArrayList<Element> blockRegion;
    private Element start, end;
    private double xBeg, yBeg,  xEnd, yEnd, xCenter, yCenter;

    public WorkRegion(double xBeg, double yBeg, double xEnd, double yEnd, WorkSpace ws){
        ws.getElements().forEach(el -> {
            if( isRegion(el.getElementX(), el.getElementY(), xBeg, yBeg, xEnd, yEnd) ||
                isRegion(el.getElementWidth(), el.getElementHeight(), xBeg, yBeg, xEnd, yEnd)) elements.add(el);
        });
        this.xBeg = xBeg; this.yBeg = yBeg; this.xEnd = xEnd; this.yEnd = yEnd;
        xCenter = (xEnd - xBeg)/2; yCenter = (yEnd - yBeg)/2;
    }


    private boolean isRegion(double x, double y, double xBeg, double yBeg, double xEnd, double yEnd){
        return x >= xBeg && x <= xEnd && y >= yBeg && y <= yEnd;
    }

    public void createBlockRegion(){
        elements.forEach(e -> {
            double x = e.getElementX() + 5, y = e.getElementY() + 5;
            BlockRegion blockRegion = new BlockRegion(x, y, e.getElementWidth() + x, e.getElementHeight() + y);
            EnumSet<Direction> dir = Direction.getDirections(xCenter, yCenter, blockRegion.xBeg, blockRegion.yBeg);
            Iterator<Direction> itr = dir.iterator();
            while(itr.hasNext()) switch (itr.next()){
                case TOP:   blockRegion.verticalBeg     = Direction.TOP;    break;
                case LEFT:  blockRegion.horizontalBeg   = Direction.LEFT;   break;
                case DOWN:  blockRegion.verticalBeg     = Direction.DOWN;   break;
                case RIGHT: blockRegion.horizontalBeg   = Direction.RIGHT;
            }
            dir = Direction.getDirections(xCenter, yCenter, blockRegion.xEnd, blockRegion.yEnd);
            itr = dir.iterator();
            while(itr.hasNext()) switch (itr.next()){
                case TOP:   blockRegion.verticalEnd     = Direction.TOP;    break;
                case LEFT:  blockRegion.horizontalEnd   = Direction.LEFT;   break;
                case DOWN:  blockRegion.verticalEnd     = Direction.DOWN;   break;
                case RIGHT: blockRegion.horizontalEnd   = Direction.RIGHT;
            }
        });
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    private class BlockRegion{

        private BlockRegion(double xBeg, double yBeg, double xEnd, double yEnd){
            this.xBeg = xBeg; this.yBeg = yBeg; this.xEnd = xEnd; this.yEnd = yEnd;
        }

        double xBeg, yBeg, xEnd, yEnd;
        Direction verticalBeg, horizontalBeg, verticalEnd, horizontalEnd;
    }

}
