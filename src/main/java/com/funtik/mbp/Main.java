package com.funtik.mbp;

import javafx.application.Application;
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

        stage.setTitle(nameApp);
        stage.show();
    }
}
