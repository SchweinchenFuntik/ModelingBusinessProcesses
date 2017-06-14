package com.funtik.mbp.event;

import com.funtik.mbp.builder.ArrowBuilder;
import com.funtik.mbp.element.Element;
import com.funtik.mbp.element.EventKey;
import com.funtik.mbp.element.EventMouse;
import com.funtik.mbp.gui.elements.RegionRotate;
import com.funtik.mbp.util.ref.ClassRef;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Created by funtik on 09.06.17.
 */
public class CreateArrow implements EventMouse, EventKey {
    private ArrowBuilder arrow = ArrowBuilder.getBuilder();
    private boolean start = false;


    @Override
    public void apply(Element el) {

    }

    @Override
    public void unApply(Element el) {

    }

    @Override
    public void released(KeyEvent e) {

    }

    @Override
    public void click(MouseEvent e) {
        if(!start && ClassRef.isClass(e.getSource().getClass(), RegionRotate.class)){

        }
    }
}
