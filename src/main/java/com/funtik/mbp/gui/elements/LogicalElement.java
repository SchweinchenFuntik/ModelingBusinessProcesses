package com.funtik.mbp.gui.elements;

import com.funtik.mbp.annotacion.AddProperty;
import com.funtik.mbp.element.FocusShell;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Created by funtik on 31.05.17.
 */
public class LogicalElement extends BaseRectangleElement {

    public enum Operators{AND, OR, XOR, AND_A, OR_A}

    private Point beg = new Point(layoutXProperty(), layoutYProperty());
    @AddProperty(name="asynchronous", type = boolean.class, isCreate = false)
    private BooleanProperty asynchronous;

    public LogicalElement(String symbol, boolean asynchronous){
        init(symbol, asynchronous);
    }

    private void init(String symbol, boolean asynchronous){
        this.asynchronous = new SimpleBooleanProperty(asynchronous);
        text.setValue(symbol);
        Line line   = new Line();

        line.setStrokeWidth(1.5);

        AnchorPane.setLeftAnchor(line, 3.0);
        AnchorPane.setTopAnchor(line, 0.0);

        AnchorPane.setTopAnchor(textPane, 0.0);
        AnchorPane.setBottomAnchor(textPane, 0.0);

        if(asynchronous || symbol.equals("X")) {
            AnchorPane.setLeftAnchor(textPane, 7.5);
            AnchorPane.setRightAnchor(textPane, 3.0);
            heightProperty().addListener((observable, ov, nv) ->
                    line.setEndY(nv.doubleValue()-6));
            getChildren().addAll(line, textPane);
        } else {
            Line l = new Line();
            l.setStrokeWidth(1.5);
            AnchorPane.setTopAnchor(l, 0.0);
            AnchorPane.setRightAnchor(l, 3.0);
            AnchorPane.setLeftAnchor(textPane, 7.5);
            AnchorPane.setRightAnchor(textPane, 7.5);
            heightProperty().addListener((observable, ov, nv) -> {
                line.setEndY(nv.doubleValue()-5.5);
                l.setEndY(nv.doubleValue()-5.5);
            });
            getChildren().addAll(line, textPane, l);
        }
        createShell();
    }

    private void createShell(){
        shell = new FocusShellElement(this);
        shell.createFocusShell(this, false, beg);
    }

    @Override
    public FocusShell getFocusShell() {
        return shell;
    }

    public final static LogicalElement createLogicalElement(Operators o){
        switch (o){
            case AND:   return new LogicalElement("&", false);
            case OR:    return new LogicalElement("O", false);
            case XOR:   return new LogicalElement("X", true);
            case AND_A: return new LogicalElement("&", true);
            case OR_A:  return new LogicalElement("O", true);
        }
        return null;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName()+" x="+getLayoutX() + "\ty="+getLayoutY();
    }
}
