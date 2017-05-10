package com.funtik.mbp;

import com.funtik.mbp.gui.elements.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.xml.bind.annotation.XmlAnyElement;

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
        Scene scene = new Scene(t(), 700, 700);
        scene.getStylesheets().add("styles/style.css");
        stage.setScene(scene);
        stage.titleProperty().bind(nameApp);
        stage.show();
    }

    public static void setNameApp(String name){
        nameApp.setValue(name);
    }

    double dx, dy;
    private Parent t(){
        Pane pane = new Pane();


        LineElemetn l = new LineElemetn(10, 10, 100, 10);


        l.setOnMousePressed(e -> {
            dx = e.getX(); dy = e.getY();
        });
        l.setOnMouseDragged(e -> {
            l.setElementX(l.getLayoutX()+e.getX()-dx);
            l.setElementY(l.getLayoutY()+e.getY()-dy);
        });


        pane.getChildren().addAll(l, l.beg, l.end);

        return pane;
    }

}
