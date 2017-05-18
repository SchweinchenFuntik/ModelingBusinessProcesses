package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.Element;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by funtik on 03.05.17.
 * много чего перепичывать
 */
public class RectangleVBox extends Group implements Element {
    private static final String baseText = "Rectangle";
    private static int ID = 0;


    private VBox pane;
    private Text text = new Text();
    private TextArea readerText;
    private Point       pLeftTop    = new Point(),
                        pLeftDown   = new Point(),
                        pRightTop   = new Point(),
                        pRightDown  = new Point();
    //// не тут
    private double x, y; // не тут
    EventHandler<MouseEvent> eC = e ->{
        //Point p = (Point) e.getSource();
        //p.getX().set(p.getLayoutX()+e.getX()-x);
        //p.getY().set(p.getLayoutY()+e.getY()-y);
        Element n = (Element) e.getSource();
        n.setElementX(n.getElementX()+e.getX()-x);
        n.setElementY(n.getElementY()+e.getY()-y);
    };
    EventHandler<MouseEvent> eP = e ->{
        x = e.getX(); y = e.getY();
    };//

    DoubleProperty width, height;
    double minWidth = 0, minHeight = 0;


    public RectangleVBox(){
        width = new SimpleDoubleProperty(0);
        height = new SimpleDoubleProperty(0);

        ///////////////////////////////////////

        text = new Text("asdaadwadawd");
        text.setTextAlignment(TextAlignment.CENTER);
        pane = new VBox(text);
        pane.getStyleClass().add("rectangle");

        //////////////////////////////////////////
        // WIDTH, HEIGHT ////////
        width.addListener((ob, ov, nv) ->
                pane.setPrefWidth(nv.doubleValue()));
        height.addListener((ob, ov, nv) ->
                pane.setPrefHeight(nv.doubleValue()));

        /////////////////////////////////////////

        // Left // TOP // Point
        pLeftTop.getX().addListener((ob, ov, nv) -> {
            double dx = ov.doubleValue() - nv.doubleValue();
            double w = pane.getWidth() + dx;
            getMinSize();
            setElementWidth(w);
            System.out.println("WWWW ="+w);
            if(minWidth<=w) pane.setLayoutX(pane.getLayoutX()-dx);
            else pLeftTop.getX().setValue(ov);
        });
        pLeftTop.getY().addListener((ob, ov, nv) -> {
            double dy = ov.doubleValue() - nv.doubleValue();
            double h = pane.getHeight()+dy;
            pane.setLayoutY(pane.getLayoutY() - dy);
            setElementHeight(h + dy);

        });
        // Right // TOP // Point

        // DOWN // Left // Point

        // Right // DOWN // Point


        //pane.setOnMousePressed(eP);
        //pane.setOnMouseDragged(eC);
        pLeftTop.setOnMouseDragged(eC);
        pLeftTop.setOnMousePressed(eP);
        pLeftDown.setOnMouseDragged(eC);
        pLeftDown.setOnMousePressed(eP);
        pRightTop.setOnMouseDragged(eC);
        pRightTop.setOnMousePressed(eP);
        pRightDown.setOnMouseDragged(eC);
        pRightDown.setOnMousePressed(eP);


        getChildren().addAll(pane, pLeftTop);
        width.set(pane.getWidth());
        height.set(pane.getHeight());
    }

    private void getMinSize(){
        minWidth = 0; minHeight = 0;
        for(Node n :pane.getChildren()){
            minWidth   += n.prefWidth(0);
            minHeight  += n.prefHeight(0);
        }
        System.out.println(minWidth);
    }

    @Override
    public double getElementWidth() {
        return width.get();
    }

    @Override
    public void setElementWidth(double width) {
        this.width.setValue(width);
    }

    @Override
    public double getElementHeight() {
        return height.get();
    }

    @Override
    public void setElementHeight(double height) {
        this.height.setValue(height);
    }

    @Override
    public void setElementX(double x) {
        pLeftTop.getX().setValue(x);
    }

    @Override
    public void setElementY(double y) {
        pLeftTop.getY().setValue(y);
    }
}
