package com.funtik.mbp.gui.skin;

import com.funtik.mbp.util.ref.ClassRef;
import impl.org.controlsfx.skin.PropertySheetSkin;
import javafx.scene.control.ToolBar;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.control.SegmentedButton;

/**
 * Created by funtik on 11.06.17.
 */
public class MinimalPropertySheetSkin extends PropertySheetSkin {

    private ClassRef val;
    /**
     * @param control*/
    public MinimalPropertySheetSkin(PropertySheet control) {
        super(control);
        val = new ClassRef(this);
        val.addField("toolbar");
        val.addField("modeButton");

        ToolBar tb = val.getValueField("toolbar");
        SegmentedButton bt = val.getValueField("modeButton");

        tb.getItems().remove(bt);
    }
}
