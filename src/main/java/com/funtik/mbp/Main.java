package com.funtik.mbp;

import com.funtik.mbp.elements.Element;
import com.funtik.mbp.gui.elements.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
        Pane parent = t();
        Scene scene = new Scene(parent, 700, 700);
        scene.getStylesheets().add("styles/style.css");
        stage.setScene(scene);
        stage.titleProperty().bind(nameApp);
        stage.show();
    }

    public static void setNameApp(String name){
        nameApp.setValue(name);
    }

    double dx, dy;
    private Pane t(){
        Pane pane = new Pane();

        LineElement l = new LineElement(100, 100, 100, 300);

        //l.setElementY(50);
        //l.setElementX(50);

//        l.setOnMouseDragged(e -> {
//            l.setElementX(l.getElementX()+e.getX());
//            l.setElementY(l.getElementY()+e.getY());
//        });

//        l.setOnMouseClicked(e -> {
//            l.focus();
//            pane.getChildren().remove(l);
//            pane.getChildren().add(l.getFs());
//        });


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

        xor = new LogicalElement("X", false);
        xor.setLayoutX(100);
        xor.setLayoutY(150);

        anchor.setLayoutX(50); anchor.setLayoutY(50);
        anchor.setPrefSize(200, 100);
        anchor.getStyleClass().add("border");

        //System.out.println(anchor.getProperties().get("prefWidth"));

        pane.getChildren().addAll(anchor);

        return pane;
    }

    LogicalElement xor;
    AnchorElement2 anchor = new AnchorElement2();

    private Element getEl(){
        T t = new T();
        t.getStyleClass().add("border");
       // t.setLayoutX(500);
      //  t.setLayoutY(500);
      /// t.setPrefSize(100, 100);
       /// t.getPrefWidth();
        return t;
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
