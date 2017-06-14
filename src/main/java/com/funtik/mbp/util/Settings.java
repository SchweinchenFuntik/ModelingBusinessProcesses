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
        project.addListener((ob, ov, nv) -> {

        });
    }
}
