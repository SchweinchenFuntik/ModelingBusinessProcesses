package com.funtik.mbp;

import com.funtik.mbp.gui.elements.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

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

        LineElement l = new LineElement(100, 100, 100, 300);

        l.setOnMousePressed(e -> {
            dx = e.getX(); dy = e.getY();
        });
        l.setOnMouseDragged(e -> {
            l.setElementX(l.getElementX()+e.getX()-dx);
            l.setElementY(l.getElementY()+e.getY()-dy);
        });


        //RectangleVBox rvb0 = new RectangleVBox();
       // rvb0.setElementX(100);
        //rvb0.setElementY(150);

        Rec r = new Rec();
        r.setElementX(100);
        r.setElementY(200);
        r.setElementWidth(150);
        r.setElementHeight(250);


        pane.getChildren().addAll(r);

        return pane;
    }

}
