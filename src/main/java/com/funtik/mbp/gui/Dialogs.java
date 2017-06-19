package com.funtik.mbp.gui;

import com.funtik.mbp.Main;
import com.funtik.mbp.PaneTool;
import com.funtik.mbp.controller.Decompose;
import com.funtik.mbp.controller.Window;
import com.funtik.mbp.element.Element;
import com.funtik.mbp.gui.elements.Rectangle;
import com.funtik.mbp.gui.elements.RectangleDFD;
import com.funtik.mbp.gui.elements.RectangleIDEF0;
import com.funtik.mbp.gui.elements.RectangleIDEF3;
import com.funtik.mbp.model.Diagram;
import com.funtik.mbp.model.Project;
import com.funtik.mbp.util.Notations;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.Observable;
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
import java.util.List;
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
        else if(btn.get() == cancel) return false;
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
            if(con.rbIDEF0.isSelected()) {
                diagram.setValue("type", Notations.IDEF0);
                RectangleIDEF0 rect = new RectangleIDEF0();
                rect.setElementX(100);
                rect.setElementY(100);
                rect.setText("NULL");
                ((ObservableListWrapper) diagram.getValue("elements")).add(rect);
                rect.setParentDiagram(diagram);
            }
            else if(con.rbDFD.isSelected()) {
                diagram.setValue("type", Notations.DFD);
                RectangleDFD rect = new RectangleDFD();
                rect.setElementX(100);
                rect.setElementY(100);
                rect.setText("NULL");
                ((ObservableListWrapper) diagram.getValue("elements")).add(rect);
                rect.setParentDiagram(diagram);
            }
            else if(con.rbIDEF3.isSelected()) {
                RectangleIDEF3 rect = new RectangleIDEF3();
                rect.setElementX(100);
                rect.setElementY(100);
                rect.setText("NULL");
                ((ObservableListWrapper) diagram.getValue("elements")).add(rect);
                diagram.setValue("type", Notations.IDEF3);
                rect.setParentDiagram(diagram);
            }
            p.setValue("Diagram", diagram);
        }
        return p;
    }

    public static void decomposeElement(Rectangle rect) throws IOException {
        Dialog<ButtonType> d = new Dialog<>();
        d.setTitle("Декомпозиция");
        ButtonType create = ButtonType.YES, cancel = ButtonType.CANCEL;
        DialogPane dPane = d.getDialogPane();
        dPane.getButtonTypes().addAll(create, cancel);
        FXMLLoader load = new FXMLLoader(Dialogs.class.getResource("/fxml/Decompose.fxml"));
        Parent pane = load.load();
        Decompose con = load.getController();
        dPane.setContent(pane);

        if(d.showAndWait().get() == create){
            Diagram diagram = new Diagram();
            Notations type = con.getDiagramType();
            diagram.setValue("type", type);
            int sz = con.getAmountElements();
            double x = 50, y = 50;
            Rectangle r;
            for(int i=1; i<=sz; i++){
                switch (type){
                    case IDEF0:
                        r = new RectangleIDEF0();
                        createRectangle("NULL", x+(i*200), y+(i*70), r, diagram);
                        break;
                    case DFD:
                        r = new RectangleDFD();
                        createRectangle("NULL", x+(i*200), y+(i*70), r, diagram);
                        break;
                    case IDEF3:
                        r = new RectangleIDEF3();
                        createRectangle("NULL", x+(i*200), y+(i*70), r, diagram);
                        break;
                }
            }
            Window win = PaneTool.getController("Window");
            win.openTree(win.getRoot(rect), (ObservableListWrapper)diagram.getValue("elements"));
            rect.setDecompose(diagram);
            Main.getSettings().project.get().setEdit(true);
        }
    }

    private static void createRectangle(String text, double x, double y, Rectangle r, Diagram d){
        r.setElementX(x);
        r.setElementY(y);
        r.setText(text);
        r.setParentDiagram(d);
        ((List)d.getValue("elements")).add(r);
    }
}
