package com.funtik.mbp.gui;

import com.funtik.mbp.gui.skin.MinimalPropertySheetSkin;
import javafx.collections.ObservableList;
import javafx.scene.control.Skin;
import org.controlsfx.control.PropertySheet;

/**
 * Created by funtik on 11.06.17.
 */
public class MinimalPropertySheet extends PropertySheet {

    public MinimalPropertySheet(){}

    public MinimalPropertySheet(ObservableList<Item> items){
        super(items);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new MinimalPropertySheetSkin(this);
    }
}
