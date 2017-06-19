package com.funtik.mbp.gui.elements;

import com.funtik.mbp.annotacion.AddProperty;
import com.funtik.mbp.element.FocusShell;
import javafx.beans.property.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

/**
 * Created by funtik on 09.06.17.
 */
public class BaseRectangleElement extends AnchorPane implements NodeElement {
    protected Polygon top, left, bottom, right;
    protected FocusShell shell;
    private ArrayList<Polygon> directs;
    private boolean applyDirection = false;
    protected DoubleProperty sizeText = new SimpleDoubleProperty(10);
    protected StackPane textPane;
    @AddProperty(name="text", type = String.class, isCreate = false)
    protected StringProperty text;
    @AddProperty(name="id", type = Integer.class, isCreate = false)
    protected IntegerProperty id;
    protected double dx = 5, dy = 5;
    protected DoubleProperty dtText = new SimpleDoubleProperty();

    public BaseRectangleElement(){
        top         = createPolygon(false);
        left        = createPolygon(false);
        bottom      = createPolygon(true);
        right       = createPolygon(true);
        id          = new SimpleIntegerProperty();
        directs     = new ArrayList<>();
        text        = new SimpleStringProperty();
        Label label = new Label();
        textPane    = new StackPane(label);

        sizeText.addListener((ob, ov, nv) -> {
            if(nv.doubleValue() == ov.doubleValue()) return;
            label.setFont(new Font(nv.doubleValue()));
        });

        //label.setFont(new Font(20));
        label.setTextAlignment(TextAlignment.CENTER);
        label.textProperty().bindBidirectional(this.text);

        directs.add(top);       directs.add(left);
        directs.add(bottom);    directs.add(right);

        widthProperty().addListener((ob, ov, nv) ->{
            double d = nv.doubleValue() - dx;
            updatePoint(top, bottom, 2, d/2.0, d);
            updatePoint(left, right, 2, d/2.0, 0.0);
        });

        heightProperty().addListener((ob, ov,  nv) ->{
            double d = nv.doubleValue() - dy;
            updatePoint(top, bottom, 3, d/2.0, 0.0);
            updatePoint(left, right, 3, d/2.0, d);
        });

        dtText.addListener((ob, ov, nv) -> {
            AnchorPane.setTopAnchor(textPane, dtText.get());
            AnchorPane.setBottomAnchor(textPane, dtText.get());
            AnchorPane.setLeftAnchor(textPane, dtText.get());
            AnchorPane.setRightAnchor(textPane, dtText.get());
        });

        setAnchor(top,      0.0, 0.0, null, null);
        setAnchor(left,     0d, 0d, null, null);
        setAnchor(bottom,   null, 0.0, 0.0, null);
        setAnchor(right,    0d, null, null, 0d);
        getChildren().addAll(top, left, bottom, right, textPane);

        getStyleClass().add("border");
    }

    public void setApplyDirection(boolean isApply){
        if(isApply) {
            applyDirection = true;
            top.toFront();      left.toFront();
            bottom.toFront();   right.toFront();
        } else {
            applyDirection = false;
        }
    }

    public void setAnchor(Polygon n, Double top, Double  left, Double bottom, Double right){
        if(top      != null) AnchorPane.setTopAnchor     (n, top);
        if(left     != null) AnchorPane.setLeftAnchor    (n, left);
        if(bottom   != null) AnchorPane.setBottomAnchor  (n, bottom);
        if(right    != null) AnchorPane.setRightAnchor   (n, right);
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
        p.setOpacity(0.0);
        p.addEventFilter(MouseEvent.MOUSE_MOVED,    this::mouseMoved);
        p.addEventFilter(MouseEvent.MOUSE_EXITED,   this::mouseExited);
        if(rotate) p.setRotate(180);
        return  p;
    }

    private Object obj = null;
    private void mouseExited(MouseEvent e){
        if(!applyDirection) return;
        Polygon p = (Polygon) e.getSource();
        if(p == obj) obj = null;
        p.setStyle("-fx-fill: -fx-background;");
        p.setOpacity(0.0);
    }

    private void mouseMoved(MouseEvent e){
        if(!applyDirection) return;
        Polygon p = (Polygon) e.getSource();
        if(p == obj) return;
        p.setOpacity(0.7);
        p.setStyle("-fx-fill: -fx-focus-color;");
        obj = p;
    }

    public int getIdElement() {
        return id.get();
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    @Override
    public String toString() {
        return text.get();
    }
}
