package com.funtik.mbp;

import com.funtik.mbp.builder.PointBuilder;
import com.funtik.mbp.gui.primitives.LineElement;
import com.funtik.mbp.gui.primitives.Point;
import com.funtik.mbp.gui.primitives.Rectangle;
import com.sun.javafx.scene.control.skin.LabelSkin;
import com.sun.javafx.scene.control.skin.LabeledSkinBase;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.function.Consumer;

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

        Rectangle r = new Rectangle();
        r.setLayoutY(50);
        r.setLayoutX(50);
        r.setElementHeight(200);
        r.setElementWidth(100);

       // pane.getChildren().addAll(r);

        LineElement l, ll, right;
        l = new LineElement(50, 110, 50, 10);
        ll = new LineElement(100, 110, 100, 200);

         right = new LineElement(120, 110, 200, 110);

        Line lineL, lineR, lineT, lineD;

        lineL = new Line(150, 150, 150, 50);
        lineR = new Line(150, 150, 150, 50);
        lineT = new Line(150, 150, 150, 50);
        lineD = new Line(150, 150, 150, 50);

        lineL.setRotate(-90); lineR.setRotate(90); lineD.setRotate(180);


        Region rL, rR, rT, rD;
        rL = new LineT(150, 150, 150, 50);
        rR = new LineT(150, 150, 150, 50);
        rD = new LineT(150, 150, 150, 50);
        rT= new LineT(150, 150, 150, 50);


        pane.getChildren().addAll( rL, rR, rT, rD);
        return pane;
    }
}
