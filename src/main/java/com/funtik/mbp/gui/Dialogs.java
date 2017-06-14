package com.funtik.mbp.gui;

import com.funtik.mbp.model.Diagram;
import com.funtik.mbp.model.Project;
import com.funtik.mbp.util.Notations;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by funtik on 11.06.17.
 */
public class Dialogs {

    @FXML private ToggleGroup rgGp1;
    @FXML private RadioButton rbIDEF0, rbDFD, rbIDEF3;
    @FXML private TextField tfProjectName, tfAuthor;

    public static boolean createCloseIsSave(Project project) throws IOException {
        ButtonType  save    = ButtonType.YES,
                    cancel  = ButtonType.CANCEL,
                    close   = ButtonType.NO;
        String text = "Открытый проект не сохранен. Сохранить?";
        Alert dialog = new Alert(Alert.AlertType.WARNING, text, save, cancel, close);
        dialog.setTitle("Внимание!");
        String head = "Возможна потеря данных";
        dialog.setHeaderText(head);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.get() == save) project.Save();
        else if(btn.get() == cancel);
        else return false;
        return true;
    }

    public static FileChooser getFileChooser(){
        FileChooser winFile = new FileChooser();
        String name = "Funtik Modeling Business Processes(*.fbpm)", format = "*.fbpm";
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(name, format);
        winFile.getExtensionFilters().add(filter);
        return winFile;
    }

    public static Project createNewProject(ResourceBundle rb) throws IOException {
        Project p = null;
        ButtonType create = ButtonType.YES, cancel = ButtonType.CANCEL;
        Dialog<ButtonType> d = new Dialog<>();
        d.setTitle(rb.getString("dialog.CreateProject.title"));
        DialogPane dPane = d.getDialogPane();
        dPane.getButtonTypes().addAll(create, cancel);
        FXMLLoader load = new FXMLLoader(Dialogs.class.getResource("/fxml/CreateProject.fxml"), rb);
        Parent pane = load.load();
        Dialogs con = load.getController();
        dPane.setContent(pane);
        Node btCreate = dPane.lookupButton(create);

        BooleanProperty bA = new SimpleBooleanProperty(true), bP = new SimpleBooleanProperty(true);
        con.tfProjectName.textProperty().addListener((ob, ov, nv) -> bP.setValue(nv.trim().isEmpty()));
        con.tfAuthor.textProperty().addListener((ob, ov, nv) ->  bA.setValue(nv.trim().isEmpty()));
        btCreate.disableProperty().bind(Bindings.or(bP, bA));

        Optional<ButtonType> result = d.showAndWait();

        if(result.get() == create){
            p = new Project();
            p.setValue("name", con.tfProjectName.getText());
            p.setValue("author", con.tfAuthor.getText());
            Diagram diagram = new Diagram();
            diagram.setValue("name", con.tfProjectName.getText());
            diagram.setValue("author", con.tfAuthor.getText());
            if(con.rbIDEF0.isSelected()) diagram.setValue("type", Notations.IDEF0);
            else if(con.rbIDEF0.isSelected()) diagram.setValue("type", Notations.DFD);
            else if(con.rbIDEF0.isSelected()) diagram.setValue("type", Notations.IDEF3);
            p.setValue("diagram", diagram);
        }
        return p;
    }
}
