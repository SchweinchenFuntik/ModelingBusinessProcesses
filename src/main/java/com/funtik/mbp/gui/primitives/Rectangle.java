package com.funtik.mbp.gui.primitives;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Created by funtik on 06.04.17.
 */
public class Rectangle extends Group implements Element {
    private static String baseText = "Rectangle";
    private static int ID = 0;

    private double x, y; // не тут

    private Label text;
    private TextArea readerText;

    private VBox        pane        = new VBox();
    private Point       pLeftTop    = new Point(),
                        pLeftDown   = new Point(),
                        pRightTop   = new Point(),
                        pRightDown  = new Point();

    //// не тут
    EventHandler<MouseEvent> eC = e ->{
        Point p = (Point) e.getSource();
        p.setLayoutX(p.getLayoutX()+e.getX()-x);
        p.setLayoutY(p.getLayoutY()+e.getY()-y);
    };
    EventHandler<MouseEvent> eP = e ->{
        x = e.getX(); y = e.getY();
    };//

    public Rectangle(){
        pane.setPadding(new Insets(5));
        pane.setSpacing(3);
        pane.getStyleClass().add("rectangle");

        //rect.getStyleClass().add("rectangle");

        text = new Label();
        readerText = new TextArea(baseText+" "+ID++);
        readerText.setVisible(false);
        readerText.setWrapText(true);
        text.textProperty().bind(readerText.textProperty());
        text.minHeightProperty().bind(text.heightProperty());

        text.addEventFilter(MouseEvent.MOUSE_CLICKED, e ->{
            System.out.println(text.getHeight());
            if(e.getButton()== MouseButton.PRIMARY && e.getClickCount()>=2){
                //readerText.setMaxSize(text.getWidth()+4, text.getHeight()+2);
                readerText.setVisible(true);
            }
        });

        readerText.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            KeyCode key = e.getCode();
            if(key==KeyCode.ESCAPE || ((e.isAltDown() || e.isControlDown()) && key==KeyCode.ENTER)){
                readerText.setVisible(false);
                e.consume();
            }
        });

        pLeftTop.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);
        pLeftDown.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);
        pRightDown.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);
        pRightTop.addEventFilter(MouseEvent.MOUSE_DRAGGED, eC);

        pLeftTop.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pLeftDown.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pRightDown.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);
        pRightTop.addEventFilter(MouseEvent.MOUSE_PRESSED, eP);

        pLeftTop.getX().bindBidirectional(pLeftDown.getX());
        pLeftTop.getY().bindBidirectional(pRightTop.getY());
        pLeftDown.getY().bindBidirectional(pRightDown.getY());
        pRightTop.getX().bindBidirectional(pRightDown.getX());

        NumberBinding bindHeight = Bindings.subtract(pLeftDown.getY(), pLeftTop.getY());
        NumberBinding bindWidth = Bindings.subtract(pRightTop.getX(), pLeftTop.getX());
        pane.layoutXProperty().bind(pLeftTop.getX());
        pane.layoutYProperty().bind(pLeftTop.getY());
        pane.prefHeightProperty().bind(bindHeight);
        pane.prefWidthProperty().bind(bindWidth);

        StackPane paneText = new StackPane(text, readerText);
        StackPane.setAlignment(text, Pos.CENTER);
        paneText.getStyleClass().add("cell-rectangle");

        pane.getChildren().addAll(paneText, new Button("asd"));

        getChildren().addAll(pane, pLeftTop, pLeftDown, pRightTop, pRightDown);
    }

    @Override
    public void setElementHeight(double height) {
        pLeftDown.getY().setValue(pLeftTop.getY().getValue() + height);
    }

    @Override
    public void setElementWidth(double width) {
        pRightTop.getX().setValue(pLeftTop.getX().getValue() + width);
    }

    @Override
    public double getElementHeight() {
        return pLeftDown.getY().getValue() - pLeftTop.getY().getValue();
    }

    @Override
    public double getElementWidth() {
        return pRightTop.getX().getValue() - pLeftTop.getX().getValue();
    }
}
