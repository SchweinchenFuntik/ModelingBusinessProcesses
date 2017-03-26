package com.funtik.mbp.gui;

import com.funtik.mbp.gui.skin.ProgressSliderSkin;
import javafx.scene.control.Skin;
import javafx.scene.control.Slider;

/**
 *
 * @author funtik
 */
public class ProgressSlider extends Slider{
    @Override
    protected Skin<?> createDefaultSkin() {
        return new ProgressSliderSkin(this);
    }

}
