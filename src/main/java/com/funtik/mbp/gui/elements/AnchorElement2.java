package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.Element;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Created by funtik on 02.06.17.
 */
public class AnchorElement2 extends AnchorPane implements Element<AnchorPane, Object> {

    private Polygon top, left, bottom, right;

    public AnchorElement2(){
        init();
    }

    private void init(){
        top     = createPolygon(false);
        left    = createPolygon(false);
        bottom  = createPolygon(true);
        right   = createPolygon(true);

        prefWidthProperty().addListener((ob, ov, nv) ->{
            double d = nv.doubleValue();
            updatePoint(top, bottom, 2, d/2.0, d);
            updatePoint(left, right, 2, d/2.0, 0.0);
        });
        prefHeightProperty().addListener((ob, ov,  nv) ->{
            double d = nv.doubleValue();
            updatePoint(top, bottom, 3, d/2.0, 0.0);
            updatePoint(left, right, 3, d/2.0, d);
        });

        AnchorPane.setTopAnchor     (top,       0.0);
        AnchorPane.setLeftAnchor    (top,       0.0);
        AnchorPane.setTopAnchor     (left,      0.0);
        AnchorPane.setLeftAnchor    (left,      0.0);
        AnchorPane.setLeftAnchor    (bottom,    0.0);
        AnchorPane.setBottomAnchor  (bottom,    0.0);
        AnchorPane.setTopAnchor     (right,     0.0);
        AnchorPane.setRightAnchor   (right,     0.0);

        getChildren().addAll(top, left, bottom, right);
    }


    private void updatePoint(Polygon p1, Polygon p2, int index, double d1, double d2){
        p1.getPoints().set(index,   d1);
        p1.getPoints().set(index+2, d2);
        p2.getPoints().set(index,   d1);
        p2.getPoints().set(index+2, d2);
    }

    private Polygon createPolygon(boolean rotate){
        Polygon p = new Polygon(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        p.setStyle("-fx-fill: -fx-background;");
        p.addEventFilter(MouseEvent.MOUSE_MOVED,    this::mouseMoved);
        p.addEventFilter(MouseEvent.MOUSE_EXITED,   this::mouseExited);
        if(rotate) p.setRotate(180);
        return  p;
    }

    private void mouseExited(MouseEvent e){
        Polygon p = (Polygon) e.getSource();
        p.setStyle("-fx-fill: -fx-background;");
    }

    private void mouseMoved(MouseEvent e){
        Polygon p = (Polygon) e.getSource();
        p.setStyle("-fx-fill: -fx-focus-color;");
    }


}
