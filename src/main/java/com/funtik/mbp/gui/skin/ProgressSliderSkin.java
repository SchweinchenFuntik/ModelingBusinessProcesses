package com.funtik.mbp.gui.skin;

import com.funtik.mbp.gui.ProgressSlider;
import com.funtik.mbp.util.ref.ClassRef;
import com.sun.javafx.scene.control.skin.SliderSkin;
import javafx.scene.layout.StackPane;

/**
 *
 * @author funtik
 */
public class ProgressSliderSkin extends SliderSkin{
    
    protected StackPane progress;
    protected ClassRef val;
    ProgressSlider s;
    
    public ProgressSliderSkin(ProgressSlider slider) {
        super(slider);
        val = new ClassRef(this);
        val.addField("thumb");
        val.addField("track");
        s = slider;
        progress = new StackPane();
        progress.getStyleClass().setAll("slider_zoom");
        slider.requestLayout();
        StackPane sp = val.<StackPane>getValueField("thumb");
        StackPane sp2 = val.<StackPane>getValueField("track");
        progress.layoutXProperty().bind(sp2.layoutXProperty());
        sp2.layoutYProperty().addListener((ob, ov, nv) -> 
            progress.setLayoutY(nv.doubleValue()+1.5)
        );
        sp.layoutXProperty().addListener((ob, ov, nv) -> {
            double h = sp2.getHeight()-3;
            progress.resize(nv.doubleValue(), h<0 ? 4:h);
        });
         
        getChildren().add(1, progress);
    }
  
}
