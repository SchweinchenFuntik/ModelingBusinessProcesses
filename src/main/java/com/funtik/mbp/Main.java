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
        Parent parent = PaneTool.loadPane("Window", "local");
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
        WorkSpace pane = new WorkSpace(null);


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

//        pane.getElements().add(and);
//        pane.getElements().add(or);
//        pane.getElements().add(xor);
//        pane.getElements().add(and_a);
//        pane.getElements().add(or_a);



        and.setApplyDirection(true);



        Arrow arrow = ArrowBuilder  .getBuilder(pane)
                                    .setElements(null, null)
                                    .moveToArrow(50, 50, 300, 200)
                                    .build();


       // pane.getChildren().addAll(arrow);

        RectangleDFD r1 = new RectangleDFD();
        RectangleDFD r2 = new RectangleDFD();

        r1.setLayoutX(50);
        r1.setLayoutY(50);
        r2.setLayoutX(250);
        r2.setLayoutY(200);
        r1.setElementWidth(200);
        r2.setElementWidth(300);
        r1.setElementHeight(70);
        r2.setElementHeight(70);

//        pane.getElements().add(r1);
//        pane.getElements().add(r2);

        r2.setApplyDirection(true);

        RectangleIDEF0 r = new RectangleIDEF0();
        r.setElementWidth(200);
        r.setElementHeight(100);
        r.setElementX(300);
        r.setElementY(300);

        RectangleIDEF3 idef3 = new RectangleIDEF3();
        idef3.setElementWidth(200);
        idef3.setElementHeight(100);
        idef3.setElementX(50);
        idef3.setElementY(50);

        DataStoreDFD data = new DataStoreDFD();
        data.setElementWidth(200);
        data.setElementHeight(100);
        data.setElementX(50);
        data.setElementY(50);

        ExternalReferenceDFD referenceDFD = new ExternalReferenceDFD();
        referenceDFD.setElementWidth(200);
        referenceDFD.setElementHeight(100);
        referenceDFD.setElementX(50);
        referenceDFD.setElementY(50);


        ReferentIDEF3 referentIDEF3 = new ReferentIDEF3();
        referentIDEF3.setElementWidth(200);
        referentIDEF3.setElementHeight(100);
        referentIDEF3.setElementX(50);
        referentIDEF3.setElementY(50);

        r.setApplyDirection(true);
        pane.getElements().add(r);
        pane.getElements().add(idef3);
//        pane.getElements().add(referentIDEF3);

//
        return pane;
    }


    public static Settings getSettings(){
        return settings;
    }

    public static Stage getStage(){
        return stage;
    }

}
