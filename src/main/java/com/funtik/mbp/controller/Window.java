package com.funtik.mbp.controller;

import com.funtik.mbp.Main;
import com.funtik.mbp.PaneTool;
import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.WorkSpace;
import com.funtik.mbp.gui.DiagramTreeView;
import com.funtik.mbp.gui.elements.Rectangle;
import com.funtik.mbp.model.Diagram;
import com.funtik.mbp.model.Project;
import com.funtik.mbp.util.Notations;
import com.funtik.mbp.util.xml.XmlData;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

import java.net.URL;
import java.util.*;

/**
 * Created by funtik on 11.06.17.
 */
public class Window implements Initializable{
    @FXML private DiagramTreeView diagrams;
    @FXML private ScrollPane scroll;
    @FXML private VBox top;

    private ObjectProperty<Project> project = Main.getSettings().project;
    private ObjectProperty<Diagram> diagram = new SimpleObjectProperty<>();
    private ObjectProperty<Notations> diagramType = new SimpleObjectProperty<>();
    private HashMap<Diagram, WorkSpace> works = new HashMap<>();
    private HashMap<Diagram, Scale> zooms = new HashMap<>();

    private ToolBar toolBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent tool = PaneTool.loadPane("ToolBar", "local");
        toolBar = PaneTool.getController("ToolBar");
        top.getChildren().add(tool);

        project.addListener((ob, ov, nv) -> {
            if(ov != nv) openProject(nv);
        });
        diagram.addListener((ob, ov, nv) ->{
            if(ov != nv && nv != null)
                diagramType.setValue((Notations) nv.getValue("type"));
            else if(nv == null) diagramType.setValue(null);
        });
        diagramType.addListener((ob, ov, nv) -> {
            if(nv != ov) toolBar.setDiagramType(nv);
        });

    }

    public void openProject(Project p){
        works.clear(); zooms.clear();
        Diagram d = p.getValue("Diagram");
        if(d == null) return;
        WorkSpace wp = openDiagram(d);
        if(wp == null) return;
        ObservableList<Element<Node, ?>> el = (ObservableList) d.getValue("elements");
       // el.forEach(c -> wp.getChildren().add(c.getNode()));
        openTree(null, el);
    }

    public void openTree(TreeItem root, List<Element<Node, ?>> els){
        if(els == null || els.size() == 0) {
            diagrams.setRoot(null);
        } else if(root == null && els.size() == 1)
            diagrams.setRoot(new TreeItem(els.get(0)));
        else if(root != null)
            for(Element el:els)
                if(el != null) root.getChildren().add(new TreeItem<>(el));
    }

    public TreeItem<Rectangle> getRoot(Rectangle r){
        return getRoot(r, diagrams.getRoot());
    }
    public TreeItem<Rectangle> getRoot(Rectangle r, TreeItem<Rectangle> root){
        if(root == null) return null;
        if(root.getValue() == r) return root;
        for(TreeItem<Rectangle> ti:root.getChildren()){
            TreeItem<Rectangle> tmp = getRoot(r, ti);
            if(tmp != null) return tmp;
        }
        return null;
    }

    public WorkSpace openDiagram(Diagram d){
        if(d == null) return null;
        if(d == diagram.get()) return null;
        diagram.setValue(d);
        WorkSpace wp = works.get(d);
        if(wp == null){
            wp = new WorkSpace(d);
            wp.setOnScroll(this::eventScroll);
            Scale zoom = new Scale();
            wp.getTransforms().add(zoom);
            works.put(d, wp);
            zooms.put(d, zoom);
        }
        scroll.setContent(wp);
        return  wp;
    }

    private void eventScroll(ScrollEvent e){
        if(e.isControlDown()){
            if(e.getDeltaY()<0) toolBar.zoomDecrement();
            else toolBar.zoomIncrement();
            e.consume();
        }
    }

    public void setZoom(double z){
        Scale zoom = zooms.get(diagram.get());
        if(zoom != null){ zoom.setX(z); zoom.setY(z); }
    }

    public void setEvent(String event){

    }

    public Diagram getDiagram() {
        return diagram.get();
    }

    public ObjectProperty<Diagram> diagramProperty() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram.set(diagram);
    }

    public Project getProject() {
        return project.get();
    }
    public ObjectProperty<Project> projectProperty() {
        return project;
    }
    public Scale getScale(){
        return zooms.get(diagram.get());
    }

}
