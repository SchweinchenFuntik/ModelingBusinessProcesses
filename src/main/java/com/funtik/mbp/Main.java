package com.funtik.mbp;

import com.funtik.mbp.gui.primitives.Point;
import com.funtik.mbp.gui.primitives.R;
import com.funtik.mbp.gui.primitives.Rectangle;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by funtik on 26.03.17.
 */
public class Main extends Application {
    public static final String nameApp = "ModelingBusinessProcesses";

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(t(), 700, 700);
        stage.setScene(scene);
        stage.setTitle(nameApp);
        stage.show();
    }

    private Parent t(){
        Pane pane = new Pane();
        pane.getStylesheets().add("styles/style.css");

        R r = new R();
        r.setLayoutY(50);
        r.setLayoutX(50);

        pane.getChildren().addAll(r);
        return pane;
    }
}
