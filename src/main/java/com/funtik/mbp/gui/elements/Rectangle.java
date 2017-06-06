package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.Element;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Created by funtik on 06.04.17.
 */
// Region and layoutBounds поробывать
// написать что бы Anchor проходил по своим елеентам и вычислялль width and height
public class Rectangle extends Group implements Element {
    private static final String baseText = "Rectangle";
    private static int ID = 0;

    private double x, y; // не тут

    private Text text;
    private TextArea readerText;

    private StackPane   pane        = new StackPane();
    private Point       pLeftTop    = new Point(),
                        pLeftDown   = new Point(),
                        pRightTop   = new Point(),
                        pRightDown  = new Point();

    AnchorPane a = new AnchorPane();

    //// не тут
    EventHandler<MouseEvent> eC = e ->{
        //Point p = (Point) e.getSource();
        //p.getX().set(p.getLayoutX()+e.getX());
        //p.getY().set(p.getLayoutY()+e.getY());
        pane.setLayoutX(pane.getLayoutX()+e.getX()-x);
        pane.setLayoutY(pane.getLayoutY()+e.getY()-y);
    };
    EventHandler<MouseEvent> eP = e ->{
        x = e.getX(); y = e.getY();
    };//

    public Rectangle(){
        pane.setPadding(new Insets(5));
        pane.getStyleClass().add("rectangle");

        //pLeftTop.setVisible(false); pLeftDown.setVisible(false);
        //pRightTop.setVisible(false); pRightDown.setVisible(false);

        text = new Text();
        readerText = new TextArea(baseText+" "+ID++);
        readerText.setVisible(false);
        readerText.setWrapText(true);
        text.textProperty().bind(readerText.textProperty());

        addEventFilter(MouseEvent.MOUSE_CLICKED, e ->{
            if(e.getButton()== MouseButton.PRIMARY && e.getClickCount()>=2){
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
        pane.translateXProperty().bind(pLeftTop.getX());
        pane.layoutYProperty().bind(pLeftTop.getY());

        pane.prefHeightProperty().bind(bindHeight);
        pane.prefWidthProperty().bind(bindWidth);

        StackPane paneText = new StackPane(text, readerText);
        paneText.getStyleClass().add("cell-rectangle");

        pLeftTop.getStyleClass().add("pointLeftTop");
        pLeftDown.getStyleClass().add("pointLeftDown");
        pRightTop.getStyleClass().add("pointRightTop");
        pRightDown.getStyleClass().add("pointRightDown");

        Button bt = new Button("Bt");
        Label lb = new Label("Label");

        bt.setOnAction(e -> System.out.println("Bt"));

        text.getLayoutBounds();// поробывать это
        NumberBinding bindMinWidth = Bindings.add(pLeftTop.getX(), text.getLayoutBounds().getWidth()+12);
        NumberBinding bindMinHeight = Bindings.add(pLeftTop.getY(), text.getLayoutBounds().getHeight()+12);
       // pRightTop.getX().minProperty().bind(bindMinWidth);
       // pRightDown.getX().minProperty().bind(bindMinWidth);
        //pRightDown.getY().minProperty().bind(bindMinHeight);
        //pLeftDown.getY().minProperty().bind(bindMinHeight);

        AnchorPane.setBottomAnchor(bt, 0.0);
        AnchorPane.setBottomAnchor(lb, 0.0);
        AnchorPane.setLeftAnchor(bt, 10.0);
        AnchorPane.setRightAnchor(lb, 10.0);
        a.getChildren().addAll(bt, lb);

        StackPane.setAlignment(text, Pos.TOP_CENTER);
        pane.getChildren().addAll( paneText, a);

        getChildren().addAll(pane, pLeftTop, pLeftDown, pRightTop, pRightDown);
    }

    @Override
    public void setElementX(double x) {
        pLeftTop.getX().set(x);
    }

    @Override
    public void setElementY(double y) {
        pLeftTop.getY().set(y);
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

    @Override
    public void focus() {
        pLeftTop.setVisible(true); pLeftDown.setVisible(true);
        pRightTop.setVisible(true); pRightDown.setVisible(true);
    }
}
