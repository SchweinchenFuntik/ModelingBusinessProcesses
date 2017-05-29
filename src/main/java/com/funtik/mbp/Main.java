package com.funtik.mbp;

import com.funtik.mbp.elements.Element;
import com.funtik.mbp.elements.FocusShell;
import com.funtik.mbp.gui.FocusShellElement;
import com.funtik.mbp.gui.elements.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import javax.swing.plaf.synth.Region;
import java.util.ArrayList;

/**
 * Created by funtik on 26.03.17.
 */
public class Main extends Application {
    public static final StringProperty nameApp = new SimpleStringProperty("ModelingBusinessProcesses");

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(t(), 700, 700);
        scene.getStylesheets().add("styles/style.css");
        stage.setScene(scene);
        stage.titleProperty().bind(nameApp);
        stage.show();
    }

    public static void setNameApp(String name){
        nameApp.setValue(name);
    }

    double dx, dy;
    private Parent t(){
        Pane pane = new Pane();

        LineElement l = new LineElement(100, 100, 100, 300);

        l.setElementY(50);
        l.setElementX(50);

        l.setOnMousePressed(e -> {
            System.out.println(l.getHeight());
            dx = e.getX(); dy = e.getY();
        });
        l.setOnMouseReleased(e -> {
            l.setElementX(l.getElementX()+e.getX() - dx);
            l.setElementY(l.getElementY()+e.getY() - dy);
        });

        l.setOnMouseClicked(e -> {
//            l.focus();
//            pane.getChildren().remove(l);
//            pane.getChildren().add(l.getFs());
        });


        //RectangleVBox rvb0 = new RectangleVBox();
       // rvb0.setElementX(100);
        //rvb0.setElementY(150);

        Rec r = new Rec();
        r.setElementX(100);
        r.setElementY(200);
        r.setElementWidth(150);
        r.setElementHeight(250);

        //r.setElementX(50);
        //r.setElementY(50);




//        pane.setOnMousePressed(e -> {
//            Element el = getChildPoint(pane, e.getX(), e.getY());
//            if(el == null) return;
//            dx = e.getX()-el.getElementX(); dy = e.getY()-el.getElementY();
//            System.out.println();
//        });
//
//        pane.setOnMouseDragged(e -> {
//            Element el = getChildPoint(pane, e.getX(), e.getY());
//            if(null==el) return;
//            el.setElementX(e.getX()-dx);
//            el.setElementY(e.getY()-dy);
//        });




        pane.getChildren().addAll(l);

        return pane;
    }

    private ArrayList<Node> getChildRect(Pane pane, double x, double y){
        ArrayList<Node> ls = new ArrayList<>();
        pane.getChildren().forEach(n -> {
            Element e = (Element) n;
            double lx = e.getElementX(), ly = e.getElementY(),
                    w = e.getElementWidth(), h = e.getElementHeight();
            if(lx<=x && w+lx>=x && ly<=y && h+ly>=y) ls.add(n);
        });
        return ls;
    }

    private Element getChildPoint(Pane pane, double x, double y){
        ArrayList<Element> ls = new ArrayList<>();
        pane.getChildren().forEach(n -> {
            Element e = (Element) n;
            double lx = e.getElementX(), ly = e.getElementY(),
                    w = e.getElementWidth(), h = e.getElementHeight();
            if(lx<=x && w+lx>=x && ly<=y && h+ly>=y){
                ls.add(e); return;
            }
        });
        return ls.size()==0 ? null:ls.get(0);
    }

}
