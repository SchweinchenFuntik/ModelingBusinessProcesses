package com.funtik.mbp.gui;

import com.funtik.mbp.PaneTool;
import com.funtik.mbp.controller.Window;
import com.funtik.mbp.gui.elements.Rectangle;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Created by funtik on 11.06.17.
 */
public class DiagramTreeCell extends TreeCell<Rectangle> {

    private TextField textField;

    public DiagramTreeCell(){
        textField = new TextField();
        textField.addEventHandler(KeyEvent.KEY_RELEASED, this::key);
        addEventFilter(MouseEvent.MOUSE_CLICKED, e ->{
            if(e.getClickCount() == 1 && e.getButton() == MouseButton.PRIMARY){
                System.out.println(getTreeItem());
                if(isEmpty()) return;
                TreeItem<Rectangle> o = getTreeItem();
                Window win = PaneTool.getController("Window");
                win.openDiagram(o.getValue().getParentDiagram());
                e.consume();
            }
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        textField.setText(getItem() == null ? "":getItem().toString());
        setText(null);
        setGraphic(textField);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem().toString());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    protected void updateItem(Rectangle item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
           // setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getItem() == null ? "":getItem().toString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getItem() == null ? "":getItem().toString());
                setGraphic(getTreeItem().getGraphic());
            }
        }
    }

    private void key(KeyEvent key){
        if(key.getCode() == KeyCode.ENTER){
            getItem().setText(textField.getText());
            commitEdit(getItem());
        }
        if(key.getCode() == KeyCode.ESCAPE) cancelEdit();
    }
}
