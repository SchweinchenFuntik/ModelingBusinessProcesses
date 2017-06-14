package com.funtik.mbp.gui;

import javafx.collections.ListChangeListener;
import javafx.scene.control.*;

/**
 * Created by funtik on 11.06.17.
 */
public class DiagramTreeView extends TreeView<String> {

    private boolean expanded = false;
    private ContextMenu menu;
    private TreeItem<String> itemSel = null;

    public DiagramTreeView(){
        init();
    }
    public DiagramTreeView(TreeItem root){
        super(root);
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
        decompose.setOnAction(a -> {});
        delete.setOnAction(a -> itemSel.getParent().getChildren().remove(itemSel));

        setCellFactory(p -> new DiagramTreeCell());
        rootProperty().addListener((ob, ov, nv) -> {
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
