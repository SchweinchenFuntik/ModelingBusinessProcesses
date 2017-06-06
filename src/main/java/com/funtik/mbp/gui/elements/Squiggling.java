package com.funtik.mbp.gui.elements;

import com.funtik.mbp.element.ConnectPoint;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polyline;

/**
 * Created by funtik on 08.05.17.
 */
public class Squiggling extends RegionRotate {

    public Squiggling(ConnectPoint begCon, ConnectPoint endCon){
        Polyline pl = new Polyline(0, 0, 0, 0, 0, 0, 0, 0); // init 4 points
        pl.setStrokeWidth(1.5);

        begX.bind(begCon.getX());
        begY.bind(begCon.getY());
        endX.bind(endCon.getX());
        endY.bind(endCon.getY());

        begX.addListener((ob, ov, nv) -> {
            double n = nv.doubleValue();
            ObservableList<Double> p = pl.getPoints();
            p.set(0, n); p.set(6, n);
        });

        prefHeightProperty().addListener((ob, ov, nv) -> {
            double n = nv.doubleValue();
            double d = n/2;
            double delta = d/10;
            double len = d + delta;

            ObservableList<Double> p = pl.getPoints();
            p.set(3, len);
            p.set(5, len - delta * 2);
            p.set(7, n);
        });

        init(begCon.getX().get(), begCon.getY().get(), endCon.getX().get(), endCon.getY().get(), 10, pl);
    }

}
