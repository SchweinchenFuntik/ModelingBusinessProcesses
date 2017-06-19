package com.funtik.mbp.controller;

import com.funtik.mbp.Main;
import com.funtik.mbp.model.Project;
import com.funtik.mbp.gui.Dialogs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by funtik on 26.03.17.
 */
public class MenuController implements Initializable {
    @FXML private MenuBar menuBar;
    @FXML private Menu menu;

    private ResourceBundle rb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rb = resources;
    }

    public void menuActionNew() throws IOException {
        Project p = Main.getSettings().project.get();
        if(p != null && p.isEdit()) if(!Dialogs.createCloseIsSave(p)) return;
        Project tmpP = Dialogs.createNewProject(rb);
        if(tmpP == null) return;
        tmpP.setEdit(true);
        Main.getSettings().project.setValue(tmpP);
    }

    public void menuActionOpen() throws JDOMException, IOException {
        File f = Dialogs.getFileChooser().showOpenDialog(Main.getStage());
        if(f == null) return;
        Project p = Main.getSettings().project.get();
        if(p != null && p.isEdit()) if(!Dialogs.createCloseIsSave(p)) return;
        p = new Project();
        if(p.Load(f));
        else ;// LOG""
    }

    public void menuActionSave() throws IOException {
        Project project = Main.getSettings().project.get();
        if(project != null && project.isEdit()) project.Save();
    }

    public void menuActionClose() throws IOException {
        Project p = Main.getSettings().project.get();
        if(p != null && p.isEdit()) Dialogs.createCloseIsSave(p);
    }
}
