package com.funtik.mbp.gui;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by funtik on 11.06.17.
 */
public class DiagramTreeCell extends TreeCell<String> {

    private TextField textField;

    public DiagramTreeCell(){
        textField = new TextField();
        textField.addEventHandler(KeyEvent.KEY_RELEASED, this::key);
    }

    @Override
    public void startEdit() {
        super.startEdit();
        textField.setText(getItem() == null ? "":getItem());
        setText(null);
        setGraphic(textField);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getItem() == null ? "":getItem());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getItem() == null ? "":getItem());
                setGraphic(getTreeItem().getGraphic());
            }
        }
    }

    private void key(KeyEvent key){
        if(key.getCode() == KeyCode.ENTER)  commitEdit(textField.getText());
        if(key.getCode() == KeyCode.ESCAPE) cancelEdit();
    }
}
