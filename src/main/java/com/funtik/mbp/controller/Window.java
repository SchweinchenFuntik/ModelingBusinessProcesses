package com.funtik.mbp.controller;

import com.funtik.mbp.PaneTool;
import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.WorkSpace;
import com.funtik.mbp.gui.DiagramTreeView;
import com.funtik.mbp.model.Diagram;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by funtik on 11.06.17.
 */
public class Window implements Initializable{
    @FXML private DiagramTreeView diagrams;
    @FXML private WorkSpace workSpace;
    @FXML private ScrollPane scroll;
    @FXML private VBox top;

    private Scale zoom;

    private HashMap<Diagram, Element> works = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Parent tool = PaneTool.loadPane("ToolBar", "local");

        top.getChildren().add(tool);

        zoom = new Scale();
        workSpace.getTransforms().add(zoom);

        Label l = new Label("TEST");
        l.setLayoutX(100);
        l.setLayoutY(250);
        workSpace.getChildren().add(l);

        workSpace.setOnScroll(e -> {
            if(e.isControlDown()){
                ToolBar c = PaneTool.getController("ToolBar");
                if(e.getDeltaY()<0) c.zoomDecrement();
                else c.zoomIncrement();

                e.consume();
            }

        });

        TreeItem root = new TreeItem("adwada");
        diagrams.setRoot(root);
        root.getChildren().add(new TreeItem<>("adwa"));
        TreeItem ti1 = new TreeItem<>("dwadadwda");
        TreeItem ti2 = new TreeItem<>("zxcscsc");
        root.getChildren().addAll(ti1, ti2);
        ti1.getChildren().add(new TreeItem<>("dtbtbtbt"));
        ti1.getChildren().add(new TreeItem<>("zxcdvdt"));
        ti2.getChildren().add(new TreeItem<>("WDWDDzxcdvdt"));


    }


    public void setZoom(double z){
        zoom.setX(z); zoom.setY(z);
    }

    public Scale getScale(){ return zoom; }

}
