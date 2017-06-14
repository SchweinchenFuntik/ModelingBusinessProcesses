package com.funtik.mbp.model;

import com.funtik.mbp.Main;
import com.funtik.mbp.annotacion.AddProperty;
import com.funtik.mbp.gui.Dialogs;
import com.funtik.mbp.util.xml.XmlData;
import com.funtik.mbp.util.xml.XmlWorker;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jdom2.JDOMException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by funtik on 11.06.17.
 */
public class Project extends PropertiesBase<Project> {
    @AddProperty(name="name")
    private SimpleStringProperty name;

    @AddProperty(name="author")
    private SimpleStringProperty author;

    @AddProperty(name="Diagram", type = Diagram.class, XmlData = Type.CONTENT)
    private SimpleObjectProperty<Diagram> diagram;

    @AddProperty(name="costs", isCreate = false, type = ArrayList.class, XmlData = Type.CONTENT )
    private ObservableList costs = FXCollections.observableArrayList();

    private File file;
    private boolean isEdit = false;

    public boolean Save() throws IOException {
        if(file == null) file = Dialogs.getFileChooser().showSaveDialog(Main.getStage());
        System.out.println(file.getAbsoluteFile());
        if(!file.getName().endsWith(".fxml"))
            file = new File(file.getAbsoluteFile()+".fbpm");
        System.out.println(file.getAbsoluteFile());
        return XmlWorker.saveProject(this);
    }

    public boolean SaveAs(String file) throws IOException {
        if(file == null) return false;
        return SaveAs(new File(file));
    }

    public boolean SaveAs(File f) throws IOException {
        if(f == null) return false;
        File tmpFile = file;
        file = f;
        boolean b = XmlWorker.saveProject(this);
        file = tmpFile;
        return b;
    }

    public boolean Load() throws JDOMException, IOException {
        if(file == null) return false;
        return XmlWorker.loadProject(this);
    }

    public boolean Load(String file) throws JDOMException, IOException {
        return Load(new File(file));
    }

    public boolean Load(File file) throws JDOMException, IOException {
        if(file == null || file.isDirectory() || !file.exists()) return false;
        this.file = file;
        return Load();
    }

    public void setEdit(boolean edit){
        isEdit = edit;
    }

    public boolean isEdit(){
        return isEdit;
    }

    public void setFile(String file){
        this.file = new File(file);
    }

    public void setFile(File file){
        this.file = file;
    }

    public File getFile(){
        return file;
    }

    @Override
    public String toString() {
        return name.get();
    }

}
