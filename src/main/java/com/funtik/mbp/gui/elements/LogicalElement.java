package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.Element;
import com.funtik.mbp.elements.FocusShell;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Created by funtik on 31.05.17.
 */
public class LogicalElement extends AnchorPane implements Element<Node, ContextMenu>{

    public enum Operators{AND, OR, XOR, AND_A, OR_A}

    private StringProperty symbol;
    private FocusShell shell = new FocusShellElement();

    public LogicalElement(String symbol, boolean asynchronous){
        init(symbol, asynchronous);
    }

    private void init(String symbol, boolean asynchronous){
        this.symbol = new SimpleStringProperty(symbol);
        Line line   = new Line();
        Label label = new Label();

        line.setStrokeWidth(1.5);
        label.setFont(new Font(20));
        label.setTextAlignment(TextAlignment.CENTER);
        label.textProperty().bindBidirectional(this.symbol);

        StackPane st = new StackPane(label);

        AnchorPane.setLeftAnchor(line, 3.0);
        AnchorPane.setTopAnchor(line, 0.0);

        AnchorPane.setTopAnchor(st, 0.0);
        AnchorPane.setBottomAnchor(st, 0.0);

        getStyleClass().add("border");

        if(asynchronous || symbol.equals("X")) {
            AnchorPane.setLeftAnchor(st, 7.5);
            AnchorPane.setRightAnchor(st, 3.0);
            heightProperty().addListener((observable, ov, nv) ->
                    line.setEndY(nv.doubleValue()-6));
            getChildren().addAll(line, st);
        } else {
            Line l = new Line();
            l.setStrokeWidth(1.5);
            AnchorPane.setTopAnchor(l, 0.0);
            AnchorPane.setRightAnchor(l, 3.0);
            AnchorPane.setLeftAnchor(st, 7.5);
            AnchorPane.setRightAnchor(st, 7.5);
            heightProperty().addListener((observable, ov, nv) -> {
                line.setEndY(nv.doubleValue()-5.5);
                l.setEndY(nv.doubleValue()-5.5);
            });
            getChildren().addAll(line, st, l);
        }
        createShell();
    }

    private void createShell(){

    }

    @Override
    public FocusShell getFocusShell() {
        return shell;
    }

    public final static LogicalElement createLogicalElement(Operators o){
        switch (o){
            case AND:   return new LogicalElement("&",  false);
            case OR:    return new LogicalElement("O",  false);
            case XOR:   return new LogicalElement("X", true);
            case AND_A: return new LogicalElement("&", true);
            case OR_A:  return new LogicalElement("O", true);
        }
        return null;
    }

}
