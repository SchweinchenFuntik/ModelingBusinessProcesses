package com.funtik.mbp.util;

import com.funtik.mbp.element.WorkSpace;
import com.funtik.mbp.model.Project;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.util.HashMap;

/**
 * Created by funtik on 11.06.17.
 */
public class Settings {
    public ObjectProperty<Project> project = new SimpleObjectProperty<>();
    public ObjectProperty<WorkSpace> currentWorkSpace = new SimpleObjectProperty<>();
    public HashMap<String, WorkSpace> workSpaces = new HashMap<>();

    public Settings(){
    }

    public static boolean isRegion(double x, double y, double lx, double ly, double w, double h){
        double tx = lx + w, ty = ly + h;
        return x >= lx && x <= tx && y >= ly && y <= ty;
    }
}
