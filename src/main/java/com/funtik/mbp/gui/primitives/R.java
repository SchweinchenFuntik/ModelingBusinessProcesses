package com.funtik.mbp.gui.primitives;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Created by funtik on 06.04.17.
 */
public class R extends Group implements Element {

    double x, y;

    EventHandler<MouseEvent> eC = e ->{
        Point p = (Point) e.getSource();
        p.setLayoutX(p.getLayoutX()+e.getX()-x);
        p.setLayoutY(p.getLayoutY()+e.getY()-y);
    };
    EventHandler<MouseEvent> eP = e ->{
        x = e.getX(); y = e.getY();
    };

    public R(){
        AnchorPane r = new AnchorPane();
        r.getStyleClass().add("rectangle");
        r.setPrefSize(200, 400);
        Point   pLeftTop    = new Point(),
                pLeftDown   = new Point(),
                pRightTop   = new Point(),
                pRightDown  = new Point();
        pLeftTop.setCenter(r.getLayoutX(), r.getLayoutY());
        pLeftDown.setCenter(r.getLayoutX(), r.getHeight());
        pRightTop.setCenter(r.getWidth(), r.getLayoutY());
        pRightDown.setCenter(r.getWidth(), r.getHeight());

        pLeftTop.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);
        pLeftDown.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);
        pRightDown.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);
        pRightTop.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);

        pLeftTop.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pLeftDown.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pRightDown.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pRightTop.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);

        pRightDown.getX().addListener((observable, ov, nv) -> {
            double o = ov.doubleValue(), n = nv.doubleValue();
            DoubleProperty x = pRightTop.getX();
            x.setValue(x.getValue()-(o-n));
        });
        pRightDown.getY().addListener((observable, ov, nv) -> {});

        pRightTop.getX().addListener((observable, ov, nv) -> {});
        pRightTop.getY().addListener((observable, ov, nv) -> {});

        //r.layoutXProperty().bind(pLeftTop.getX());
        //r.layoutYProperty().bind(pLeftTop.getY());

        //r.layoutXProperty().bind(pLeftDown.getX());
        //r.prefHeightProperty().bind(pLeftDown.getY());


        r.prefWidthProperty().bind(pRightTop.getX());
        r.layoutYProperty().bind(pRightTop.getY());

        r.prefWidthProperty().bind(pRightDown.getX());
        r.prefHeightProperty().bind(pRightDown.getY());

        getChildren().addAll(r, pRightTop, pRightDown);

        //layoutXProperty().bind(r.layoutXProperty());
        //layoutYProperty().bind(r.layoutYProperty());
    }


}
