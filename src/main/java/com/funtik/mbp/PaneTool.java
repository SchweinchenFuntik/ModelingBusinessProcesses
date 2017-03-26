package com.funtik.mbp;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author funtik
 */
public class PaneTool {
    public static final HashMap<String, Initializable> HASH = new HashMap<>();  

    private static String bundle;
    private static ResourceBundle rb;
    
    public static Parent loadPane(String name){
        return loadPane(name, bundle, null);
    }
    
    public static Parent loadPane(String name, String bundle){
        return loadPane(name, bundle, null, null);
    }
    
    public static Parent loadPane(String name, String bundle, String language, String country){
        return loadPane(name, bundle, (language!=null && country!=null) ? 
                new Locale(language, country) : Locale.getDefault());
    }
    
    public static Parent loadPane(String name, String bundle, Locale l){
        Parent pane = null; FXMLLoader load;
        rb = (bundle!=null) ? ResourceBundle.getBundle("bundle."+bundle, l):null;
        load = new FXMLLoader(PaneTool.class.getResource("/fxml/"+name+".fxml"), rb);
        try {
            pane = load.load();
            HASH.put(name, load.getController());
        } catch (IOException ex) {
            //MainApp.LOG.error(ex.getMessage());
        }    
        return pane;
    }
    
    public static void setBundle(String bundle){ PaneTool.bundle = bundle; }
    
    public static ResourceBundle getResourceBundle(){ return rb; }
    
    public static <K> K getController(String name){
        return (K)HASH.get(name);
    }
    
    public static void addContoller(String name, Initializable controller){
        HASH.put(name, controller);
    }
    
    public static void addContoller(URL url, Initializable controller){
        String [] t = url.getFile().split("/");
        String f = t[t.length-1];
        HASH.put(f.substring(0, f.length()-5), controller);
    }
    
    public static Dialog createDialogFXML(String name, ResourceBundle rb, ButtonType... b){
        return createDialogFXML(name, -1, -1, rb, b);
    }
    
    public static Dialog createDialogFXML(String name, double w, double h, ResourceBundle rb, ButtonType... b){
        Dialog d = new Dialog();
        //d.getDialogPane().setContent(PaneTool.loadPane(name, MainApp.getNameLocale()));
        if(w>0) d.setWidth(w); if(h>0) d.setHeight(h);
        d.setTitle(rb.getString("dialog."+name+".title"));
        d.getDialogPane().getButtonTypes().addAll(b);
        return d;
    }
    
}
