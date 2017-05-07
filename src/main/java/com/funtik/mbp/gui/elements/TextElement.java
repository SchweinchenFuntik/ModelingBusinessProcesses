package com.funtik.mbp.gui.elements;

import com.funtik.mbp.elements.ConnectPoint;
import com.funtik.mbp.elements.Element;
import com.funtik.mbp.util.elements.LogicalConnectPoint;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Created by funtik on 07.05.17.
 * @author funtik
 * @version 0.01
 */
public class TextElement extends StackPane implements Element {
    private Text text;
    private LogicalConnectPoint point = new LogicalConnectPoint();

    @Override
    public ConnectPoint getConnectPoint(double x, double y) {
        double  tx = getElementX(),     ty = getElementY(),
                tw = getElementWidth(), th = getElementHeight();
        if(x<tx || y<ty || x>tx+tw || y>ty+th) return null;
        // dopisat
        return point;
    }

    @Override
    public double getElementX() {
        return getLayoutX();
    }

    @Override
    public double getElementY() {
        return getLayoutY();
    }

    @Override
    public double getElementWidth() {
        return getWidth();
    }

    @Override
    public double getElementHeight() {
        return getHeight();
    }
}
