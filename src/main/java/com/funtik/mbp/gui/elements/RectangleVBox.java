package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.Element;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by funtik on 03.05.17.
 * много чего перепичывать
 */
public class RectangleVBox extends Group implements Element {
    private static final String baseText = "Rectangle";
    private static int ID = 0;

    private double x, y; // не тут
    private VBox pane;
    private Text text = new Text();
    private TextArea readerText;
    private Point       pLeftTop    = new Point(),
                        pLeftDown   = new Point(),
                        pRightTop   = new Point(),
                        pRightDown  = new Point();

    //// не тут
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


    public RectangleVBox(){
        pane = new VBox();
        pane.getStyleClass().add("rectangle");
        pLeftTop.getX().bindBidirectional(pane.layoutXProperty());
        pLeftTop.getY().bindBidirectional(pane.layoutYProperty());
        pRightTop.getX().bind(Bindings.add(pane.layoutXProperty(), pane.widthProperty()));
        pRightTop.getY().bindBidirectional(pane.layoutYProperty());
        pane.getChildren().addAll(new Label("adw", new Text("ASDA")));
        getChildren().addAll(pane, pLeftTop, pRightTop);

        pane.setOnMousePressed(eP);
        pane.setOnMouseDragged(eC);
        pLeftTop.setOnMouseDragged(eC);
        pLeftTop.setOnMousePressed(eP);
        pLeftDown.setOnMouseDragged(eC);
        pLeftDown.setOnMousePressed(eP);
        pRightTop.setOnMouseDragged(eC);
        pRightTop.setOnMousePressed(eP);
        pRightDown.setOnMouseDragged(eC);
        pRightDown.setOnMousePressed(eP);
    }

    @Override
    public void setElementX(double x) {
        setLayoutX(x);
    }

    @Override
    public void setElementY(double y) {
        setLayoutY(y);
    }

    @Override
    public double getElementX() {
        return layoutXProperty().get();
    }

    @Override
    public double getElementY() {
        return layoutYProperty().get();
    }
}
