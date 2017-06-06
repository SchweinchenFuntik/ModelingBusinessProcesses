package com.funtik.mbp;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.WorkSpace;
import com.funtik.mbp.gui.elements.*;
import com.funtik.mbp.util.ref.ClassRef;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by funtik on 26.03.17.
 */
public class Main extends Application {
    public static final StringProperty nameApp = new SimpleStringProperty("ModelingBusinessProcesses");

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane parent = t();
        Scene scene = new Scene(parent, 700, 700);
        scene.getStylesheets().add("styles/style.css");
        stage.setScene(scene);
        stage.titleProperty().bind(nameApp);
        stage.show();
    }

    public static void setNameApp(String name){
        nameApp.setValue(name);
    }

    double dx, dy;
    boolean focus = false;
    private Pane t(){
        Pane pane = new WorkSpace();

        LineElement l = new LineElement(100, 100, 100, 300);
        TestLineElement tl = new TestLineElement(100, 100, 100, 300);

//        l.setElementY(50);
//        l.setElementX(50);

//        l.setOnMouseDragged(e -> {
//            l.setElementX(l.getElementX()+e.getX());
//            l.setElementY(l.getElementY()+e.getY());
//        });

        l.setOnMouseClicked(e -> {
            l.focus();
            pane.getChildren().remove(l);
            pane.getChildren().add(l.getFs());
        });

//        tl.setOnMouseClicked(e -> {
//            tl.focus();
//            e.consume();
//        });

        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if(e.getTarget() == tl && !focus){
                focus = true;
                tl.focus();
            } else if(focus && e.getTarget() != tl){
                focus = false;
                tl.focusNot();
            }
        });

        //RectangleVBox rvb0 = new RectangleVBox();
       // rvb0.setElementX(100);
        //rvb0.setElementY(150);

        Rec r = new Rec();
        r.setElementX(100);
        r.setElementY(200);
        r.setElementWidth(150);
        r.setElementHeight(250);

        //r.setElementX(50);
        //r.setElementY(50);

//        pane.setOnMousePressed(e -> {
//            Element el = getChildPoint(pane, e.getX(), e.getY());
//            if(el == null) return;
//            dx = e.getX()-el.getElementX(); dy = e.getY()-el.getElementY();
//            System.out.println();
//        });
//
//        pane.setOnMouseDragged(e -> {
//            Element el = getChildPoint(pane, e.getX(), e.getY());
//            if(null==el) return;
//            el.setElementX(e.getX()-dx);
//            el.setElementY(e.getY()-dy);
//        });

        xor = new LogicalElement("X", false);
        xor.setLayoutX(100);
        xor.setLayoutY(150);

        LogicalElement and = LogicalElement.createLogicalElement(LogicalElement.Operators.AND);
        and.setElementX(300);
        and.setElementY(400);

        ArrowPoint arrowPoint = new ArrowPoint(100, 100, ArrowPoint.Type.DOUBLE_ARROW);
        ArrowPoint arrowPoint1 = new ArrowPoint(120, 100, ArrowPoint.Type.ARROW);

        Arc a = new Arc(250, 250, 10, 10, 0, 180);
        a.setStroke(Color.BLACK);
        a.setStyle("-fx-fill: -fx-background;");
        a.setStrokeWidth(1.5);

        pane.getChildren().addAll(xor, and, arrowPoint, arrowPoint1, a);

        return pane;
    }

    LogicalElement xor;
    AnchorElement2 anchor = new AnchorElement2();

    private Element getEl(){
        T t = new T();
        t.getStyleClass().add("border");
       // t.setLayoutX(500);
      //  t.setLayoutY(500);
      /// t.setPrefSize(100, 100);
       /// t.getPrefWidth();
        return t;
    }

    private ArrayList<Node> getChildRect(Pane pane, double x, double y){
        ArrayList<Node> ls = new ArrayList<>();
        pane.getChildren().forEach(n -> {
            Element e = (Element) n;
            double lx = e.getElementX(), ly = e.getElementY(),
                    w = e.getElementWidth(), h = e.getElementHeight();
            if(lx<=x && w+lx>=x && ly<=y && h+ly>=y) ls.add(n);
        });
        return ls;
    }

    private Element getChildPoint(Pane pane, double x, double y){
        ArrayList<Element> ls = new ArrayList<>();
        pane.getChildren().forEach(n -> {
            Element e = (Element) n;
            double lx = e.getElementX(), ly = e.getElementY(),
                    w = e.getElementWidth(), h = e.getElementHeight();
            if(lx<=x && w+lx>=x && ly<=y && h+ly>=y){
                ls.add(e); return;
            }
        });
        return ls.size()==0 ? null:ls.get(0);
    }

}
