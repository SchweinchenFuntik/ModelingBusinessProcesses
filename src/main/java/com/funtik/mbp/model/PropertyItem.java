package com.funtik.mbp.model;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.util.StringConverter;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by funtik on 11.06.17.
 */
public class PropertyItem<T> implements PropertySheet.Item {
    // переписать по Property
    protected String name;
    protected String category;
    protected String description;
    protected Property<T> value;
    protected T tmpVal;
    protected Class type;

    public static <P extends Properties> List buildList(P p, ResourceBundle rb){
        return buildList(p, null, rb, false, false);
    }

    public static <P extends Properties> List buildList(P p, ResourceBundle rb,
                                                        boolean category, boolean description){
        return buildList(p, null, rb, category, description);
    }

    public static <P extends Properties> List buildList(P p, Class classItem,
                                                        ResourceBundle rb, boolean category, boolean description){
        Map<String, Property> m = p.getProperties();
        ArrayList al = new ArrayList(m.size());
        StringBuilder sb = new StringBuilder("properties.");
        sb.append(p.getClass().getSimpleName());
        sb.append('.');
        int sz = sb.length();
        m.forEach((key, val) -> {
            String c = null, d = null, n = null;
            if(rb!=null){
                String [] res = getNameResourceBundle(rb, sb, key, category, description);
                c = res[0]; d = res[1]; n = res[2];
            } else n = key;

            PropertyItem item = null;
            if (classItem == null) item = new PropertyItem(n, c, d, val, p.getClasses().get(key));
            else {
                try {
                    Constructor<PropertyItem> cons = classItem.getConstructor(
                            String.class, String.class,
                            String.class, Properties.class);
                    if (cons != null) item = cons.newInstance(n, c, d, val);
                    else {
                        cons = classItem.getConstructor(
                                String.class, Properties.class);
                        item = cons.newInstance(n, val);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PropertyItem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            al.add(item); sb.setLength(sz);
        });
        return al;
    }

    private static String[] getNameResourceBundle(ResourceBundle rb, StringBuilder sb,
                                                  String key, boolean category, boolean description){
        sb.append(key);
        String c = null, d = null, n = rb.getString(sb.toString());
        sb.append('.');
        int l = sb.length();
        if (category) {
            sb.append("category");
            c = rb.getString(sb.toString());
        }
        if (description) {
            sb.setLength(l);
            sb.append("description");
            d = rb.getString(sb.toString());
        } return new String[]{c,d,n};
    }


    protected StringConverter<Properties> convert = new StringConverter<Properties>() {
        @Override
        public String toString(Properties p) { return p==null ? "<none>":p.toString(); }
        @Override
        public Properties fromString(String s) {
            try {
                return (Properties) type.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
               Logger.getLogger(PropertyItem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return new PropertiesBase() {};
        }
    };

    public PropertyItem(String name, Property<T> value, Class c){
        init(name, null, null, value, c);
    }

    public PropertyItem(String name, String category, String description, Property<T> value, Class c){
        init(name, category, description, value, c);
    }

    private void init(String name, String category, String description, Property<T> value, Class c){
        this.name = name;
        this.category = category;
        this.description = description;
        this.value = value;
        this.tmpVal = value.getValue();
        this.type = c;
    }

    @Override
    public Class<?> getType() { return type; }

    @Override
    public String getCategory() { return category; }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Object getValue() { return tmpVal; }

    @Override
    public  void setValue(Object value) { tmpVal = (T)value; }

    @Override
    public Optional<ObservableValue<? extends Object>> getObservableValue() {
        return Optional.of(value);
    }

    @Override
    public Optional<Class<? extends PropertyEditor<?>>> getPropertyEditorClass() {
        return  PropertySheet.Item.super.getPropertyEditorClass();
    }

    public <T extends Properties> StringConverter getStringConverter() {
        return convert;
    }

    public Property<T> getProperty(){ return value; }

    public void updateProperty(){
        value.setValue(tmpVal);
    }

    public void updateItem(){
        tmpVal = value.getValue();
    }
}
