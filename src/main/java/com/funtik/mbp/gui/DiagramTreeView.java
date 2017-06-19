package com.funtik.mbp.gui;

import com.funtik.mbp.PaneTool;
import com.funtik.mbp.controller.Window;
import com.funtik.mbp.element.Event;
import com.funtik.mbp.gui.elements.Rectangle;
import com.funtik.mbp.model.Diagram;
import com.funtik.mbp.util.Settings;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * Created by funtik on 11.06.17.
 */
public class DiagramTreeView extends TreeView<Rectangle> {

    private boolean expanded = false;
    private ContextMenu menu;
    private TreeItem<Rectangle> itemSel = null;

    public DiagramTreeView(){
        init();
    }

    private void init(){
        MenuItem rename     = new MenuItem("Rename");
        MenuItem proper     = new MenuItem("Properties");
        MenuItem decompose  = new MenuItem("Decompose");
        MenuItem delete     = new MenuItem("Delete");
        menu                = new ContextMenu(rename, proper, decompose, delete);

        rename.setOnAction(a -> edit(itemSel));
        proper.setOnAction(a -> {});
        decompose.setOnAction(a -> {
            try {
                Dialogs.decomposeElement(itemSel.getValue());
            } catch (IOException e) {
                e.printStackTrace(); // write LOG
            }
        });
        delete.setOnAction(a -> itemSel.getParent().getChildren().remove(itemSel));

        setCellFactory(p -> new DiagramTreeCell());
        rootProperty().addListener((ob, ov, nv) -> {
            if(nv == null) return;
            nv.setExpanded(expanded);
            nv.getChildren().addListener(this::updateExpanded);
        });
        setOnContextMenuRequested(e ->{
            itemSel = getSelectionModel().getSelectedItem();
            menu.show(this, e.getScreenX(), e.getScreenY());
        });
    }


    private void updateExpanded(ListChangeListener.Change<? extends TreeItem> c){
        while(c.next())
            if(c.wasAdded()) c.getAddedSubList().forEach(e -> {
                e.setExpanded(expanded);
                e.getChildren().addListener(this::updateExpanded);
            });
    }

    public void setExpanded(boolean b){
        expanded = b;
    }

    public boolean getExpanded(){
        return expanded;
    }





}
