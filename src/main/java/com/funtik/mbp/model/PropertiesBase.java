package com.funtik.mbp.model;

import com.funtik.mbp.annotacion.AddProperties;
import com.funtik.mbp.annotacion.AddProperty;
import com.funtik.mbp.util.Converters;
import com.funtik.mbp.util.ref.ClassRef;
import com.funtik.mbp.util.xml.XmlData;
import javafx.beans.property.*;
import javafx.util.StringConverter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by funtik on 11.06.17.
 */
public abstract class PropertiesBase<T> implements Properties, XmlData<T> {
    protected HashMap<String, Property> properties  = new HashMap<>();
    protected HashMap<String, Class>    classes     = new HashMap<>();
    protected ArrayList<String>         attributes  = new ArrayList<>(7);
    protected ArrayList<String>         contents    = new ArrayList<>(7);

    public final static <T extends Properties> void buildMap(T t)  {
        Class clazz = t.getClass();
        AddProperty[] mp;
        AddProperty ap = (AddProperty) clazz.getAnnotation(AddProperty.class);
        AddProperties as = (AddProperties) clazz.getAnnotation(AddProperties.class);

        mp = (ap!=null ? new AddProperty[]{ap}:(as!=null ? as.value():null));

        if(mp!=null) for(AddProperty a:mp){
            Class type = a.type();
            Class c = getProperty(type);
            try {
                Property p = (Property) c.getConstructor(Object.class, String.class).newInstance(t, a.name());

                t.getClasses().put(a.name(), type!=Object.class ? type:getField(p.getClass()).getType());
                t.getProperties().put(a.name(), p);
                if(a.isCreate()) p.setValue(type.getConstructor().newInstance());
                if(a.XmlData() != Type.NONE && t instanceof XmlData) {
                    if(a.XmlData() == Type.ATTRIBUTES)
                        ((XmlData)t).getAttributes().add(a.name());
                    if(a.XmlData() == Type.CONTENT)
                        ((XmlData)t).getContents().add(a.name());
                }
            } catch (Exception ex) {
            }
        }

        Field[] fields = clazz.getDeclaredFields();
        for(Field f:fields) {
            AddProperty a = f.getAnnotation(AddProperty.class);
            if(a!=null){
                f.setAccessible(true);
                try {
                    if(a.isCreate()){
                        Property p = (Property) f.getType()
                                .getConstructor(Object.class, String.class)
                                .newInstance(t, a.name());
                        f.set(t, p);
                        Field field = getField(p.getClass());
                        Class type = field.getType();
                        if (a.type() != Object.class) type = a.type();
                        t.getClasses().put(a.name(), a.type() != Object.class ? a.type():type);
                        t.getProperties().put(a.name(), p);

                    } else {
                        t.getClasses().put(a.name(), a.type());
                        if(ClassRef.isInterface2(f.getType(), Property.class)) {
                            Object o = f.get(t);
                            t.getProperties().put(a.name(), (Property) o);
                        }
                    }
                } catch (Exception ex) {
                   // Logger.getLogger(PropertiesBase.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    f.setAccessible(false);
                }
                if(a.XmlData() != Type.NONE && t instanceof XmlData) {
                    if(a.XmlData() == Type.ATTRIBUTES)
                        ((XmlData)t).getAttributes().add(a.name());
                    if(a.XmlData() == Type.CONTENT)
                        ((XmlData)t).getContents().add(a.name());
                }
            }

        }
    }

    public static Class<? extends Property> getProperty(Class c){
        if(c==int.class || c==Integer.class)
            return SimpleIntegerProperty.class;
        if(c==float.class || c==Float.class)
            return SimpleFloatProperty.class;
        if(c==double.class || c==Double.class)
            return SimpleDoubleProperty.class;
        if(c==long.class || c==Long.class)
            return SimpleLongProperty.class;
        if(c==boolean.class || c==Boolean.class)
            return SimpleBooleanProperty.class;
        if(c==List.class) return SimpleListProperty.class;
        if(c==Map.class) return SimpleMapProperty.class;
        if(c==Set.class) return SimpleSetProperty.class;
        return SimpleObjectProperty.class;
    }

    public static void createCollection(){}

    public static Field getField(Class c){
        Field [] mf = c.getDeclaredFields();
        for (Field f : mf) if (f.getName().equals("value")) return f;
        c = c.getSuperclass();
        if(c!=null) return getField(c);
        return null;
    }

    public PropertiesBase() { buildMap(this); }

    @Override
    public List<String> getAttributes(){
        return attributes;
    }

    @Override
    public List<String> getContents() {
        return contents;
    }

    @Override
    public Object getValueAttributes(String attr) {
        Property p = properties.get(attr);
        return p == null ? null:p.getValue();
    }

    @Override
    public Object getValueContent(String content) {
        Property p = properties.get(content);
        return p == null ? null:p.getValue();
    }

    @Override
    public <T extends Property> T getProperty(String name) {
        return (T)properties.get(name);
    }

    @Override
    public Map<String, Property> getProperties() { return properties; }

    @Override
    public Map<String, Class> getClasses() { return classes; }

    @Override
    public <T> T getValue(String name) {
        Property p = properties.get(name);
        return p == null ? null:(T)p.getValue();
    }

    @Override
    public void setValue(String name, Object val) {
        Property p = properties.get(name);
        Class clazz = getClasses().get(name);
        if(p != null){
            if(val.getClass() == clazz) p.setValue(val);
            else if(val instanceof String){
                StringConverter converter = Converters.getConverter(clazz);
                if(converter == null) {
                    System.out.println("Not converter"); // #пишем_в_лог
                    return;
                }
                p.setValue(converter.fromString((String) val));
            }
        }
//        Class c = getClasses().get(name);
        //else ;// #пишем_в_лог
    }

    @Override
    public void setValueAttributes(String attr, Object value) {
        setValue(attr, value);
    }

    @Override
    public void setValueContent(String content, Object value) {
        setValue(content, value);
    }

    @Override
    public Class getClass(String attr) {
        return getClasses().get(attr);
    }
}
