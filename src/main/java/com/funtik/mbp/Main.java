package com.funtik.mbp;

import com.funtik.mbp.gui.elements.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private Parent t(){
        Pane pane = new Pane();

        TextElement tx = new TextElement("ASDFSD\nZXCVXZ");
        tx.setLayoutX(100); tx.setLayoutY(100);
        tx.setOnMouseClicked(e -> tx.getConnectPoint(e.getX(), e.getY()));

        pane.getChildren().addAll(tx);

        return pane;
    }

}
