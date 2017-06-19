package com.funtik.mbp.gui.elements;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 * Created by funtik on 15.06.17.
 */
public class DataStoreDFD extends Rectangle {

    public DataStoreDFD(){
        Line line = new Line();
        line.setStrokeWidth(2d);

        AnchorPane.setLeftAnchor(line, 15.0);
        AnchorPane.setTopAnchor(line, 0.0);

        AnchorPane.setTopAnchor(idPane, (getHeight()-4)/2 - idPane.getHeight()/2);
        AnchorPane.setLeftAnchor(idPane, 2d);

        idPane.prefWidthProperty().addListener((observable, ov, nv) ->System.out.println("idPane W="+nv.doubleValue()));

        prefHeightProperty().addListener((observable, ov, nv) ->{
            double d = nv.doubleValue()-4;
            line.setEndY(d);
            double top = d/2 - idPane.getHeight()/2;
            AnchorPane.setTopAnchor(idPane, top);
        });


        getChildren().add(line);
        setStyle("-fx-border-width: 2 0.3 2 2;");

    }
}
