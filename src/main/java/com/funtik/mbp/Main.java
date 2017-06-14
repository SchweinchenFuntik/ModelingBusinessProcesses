package com.funtik.mbp;

import com.funtik.mbp.builder.ArrowBuilder;
import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.WorkSpace;
import com.funtik.mbp.gui.elements.*;
import com.funtik.mbp.gui.skin.MinimalPropertySheetSkin;
import com.funtik.mbp.model.Project;
import com.funtik.mbp.util.Settings;
import com.funtik.mbp.util.xml.XmlWorker;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;

import java.util.ArrayList;

/**
 * Created by funtik on 26.03.17.
 */
public class Main extends Application {
    private static final StringProperty nameApp = new SimpleStringProperty("ModelingBusinessProcesses");
    private static Settings settings = new Settings();
    private static Stage stage;

    private static String bundle = "local", language = "ru", country = "RU";

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Project p = new Project();
       // XmlWorker.createXml(p);//
        Parent parent = t();//PaneTool.loadPane("Window", "local");//t();
        Scene scene = new Scene(parent, 700, 600);
        scene.getStylesheets().add("styles/style.css");
        stage.setScene(scene);
        stage.titleProperty().bind(nameApp);
        stage.show();
        this.stage = stage;
    }

    public static void setNameApp(String name){
        nameApp.setValue(name);
    }

    double dx, dy;
    boolean focus = false;

    private Pane t(){
        WorkSpace pane = new WorkSpace();

        //RectangleVBox rvb0 = new RectangleVBox();
       // rvb0.setElementX(100);
        //rvb0.setElementY(150);

//        Rec r = new Rec();
//        r.setElementX(100);
//        r.setElementY(200);
//        r.setElementWidth(150);
//        r.setElementHeight(250);

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

        EventHandler<MouseEvent> evFocus = e -> {
            Object o = e.getSource();
            if(o instanceof WorkSpace){
                WorkSpace wp = (WorkSpace) o;
                wp.getElements().forEach(el -> el.focusNot());
            } else if(o instanceof Element) {
                Element el = (Element) o;
                el.focus();
            }
        };

        LogicalElement and = LogicalElement.createLogicalElement(LogicalElement.Operators.AND);
        and.setElementX(75);
        and.setElementY(50);
        and.setApplyDirection(true);
        LogicalElement or = LogicalElement.createLogicalElement(LogicalElement.Operators.OR);
        or.setElementX(125);
        or.setElementY(100);
        or.setApplyDirection(true);
        LogicalElement xor = LogicalElement.createLogicalElement(LogicalElement.Operators.XOR);
        xor.setElementX(175);
        xor.setElementY(150);
        LogicalElement and_a = LogicalElement.createLogicalElement(LogicalElement.Operators.AND_A);
        and_a.setElementX(75);
        and_a.setElementY(200);

        LogicalElement or_a = LogicalElement.createLogicalElement(LogicalElement.Operators.OR_A);
        or_a.setElementX(300);
        or_a.setElementY(300);

        BaseRectangleElement rec = new BaseRectangleElement();
        rec.setApplyDirection(true);

        rec.setElementHeight(100);
        rec.setElementWidth(500);
        rec.setElementX(10);
        rec.setElementY(400);
        rec.getStyleClass().add("t");

        Arc a = new Arc(250, 250, 10, 10, 0, 180);
        a.setStroke(Color.BLACK);
        a.setStyle("-fx-fill: -fx-background;");
        a.setStrokeWidth(1.5);

//        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, evFocus);
//        xor.addEventFilter(MouseEvent.MOUSE_CLICKED, evFocus);
//        or.addEventFilter(MouseEvent.MOUSE_CLICKED, evFocus);
//        or_a.addEventFilter(MouseEvent.MOUSE_CLICKED, evFocus);
//        and_a.addEventFilter(MouseEvent.MOUSE_CLICKED, evFocus);
//        and.addEventFilter(MouseEvent.MOUSE_CLICKED, evFocus);

        pane.getElements().add(and);
        pane.getElements().add(or);
        pane.getElements().add(xor);
        pane.getElements().add(and_a);
        pane.getElements().add(or_a);
        pane.getElements().add(rec);



        Arrow arrow = ArrowBuilder  .getBuilder(pane)
                                    .setElements(null, null)
                                    .moveToArrow(50, 50, 300, 200)
                                    .build();
//        pane.getChildren().addAll(arrow);
//        pane.getChildren().addAll(new ArrowLine(100, 100, 100, 300));

//
        return pane;
    }

    /**
     * Опрпеделяет есть ли обекты в данной точке
     * @param pane
     * @param x
     * @param y
     * @return
     */
    private ArrayList<Node> getChildRegion(Pane pane, double x, double y){
        ArrayList<Node> ls = new ArrayList<>();
        pane.getChildren().forEach(n -> {
            Element e = (Element) n;
            double lx = e.getElementX(), ly = e.getElementY(),
                    w = e.getElementWidth(), h = e.getElementHeight();
            if(lx<=x && w+lx>=x && ly<=y && h+ly>=y) ls.add(n);
        });
        return ls;
    }

    private Node getChildRegion(WorkSpace pane, double x, double y){
        ObservableList<Node> ls = pane.getChildren();
        for(int i=ls.size()-1; i>-1; i++) {
            Element e = (Element) ls.get(i);
            double lx = e.getElementX(), ly = e.getElementY(),
                    w = e.getElementWidth(), h = e.getElementHeight();
            if(lx<=x && w+lx>=x && ly<=y && h+ly>=y) return (Node) e;
        }
        return null;
    }

    public static Settings getSettings(){
        return settings;
    }

    public static Stage getStage(){
        return stage;
    }

}
