package com.funtik.mbp.model;

import com.funtik.mbp.annotacion.AddProperties;
import com.funtik.mbp.annotacion.AddProperty;
import com.funtik.mbp.element.Element;
import com.funtik.mbp.util.functions.Func;
import com.funtik.mbp.util.ref.ClassRef;
import com.funtik.mbp.util.xml.XmlData;
import javafx.beans.property.*;
import javafx.util.Callback;

import java.lang.reflect.Field;

/**
 * Created by funtik on 11.06.17.
 * @author funtik
 * @version 1.0
 * от него наследоватся в елементах
 */
public class ElementModel extends PropertiesBase<Element> {
//    @AddProperty(name="id")
//    private SimpleIntegerProperty id;// ??подумать нужен ли он, нам нужно будет вносить изменения,
//    // также возможно добавить к Diagram?

    @AddProperty(name="elementX")
    private SimpleDoubleProperty elementX;

    @AddProperty(name="elementY")
    private SimpleDoubleProperty elementY;

    @AddProperty(name="elementWidth")
    private SimpleDoubleProperty elementWidth;

    @AddProperty(name="elementHeight")
    private SimpleDoubleProperty elementHeight;

    private Element element;

    public void setElement(Element el){
        element = el;
        buildMap(el);
    }

    @Override
    public Element getObject() {
        return element;
    }

    public final void buildMap(Element el)  {
        Class clazz = el.getClass();
        AddProperty[] mp;
        AddProperty ap = (AddProperty) clazz.getAnnotation(AddProperty.class);
        AddProperties as = (AddProperties) clazz.getAnnotation(AddProperties.class);

        mp = (ap!=null ? new AddProperty[]{ap}:(as!=null ? as.value():null));

        if(mp!=null) for(AddProperty a:mp){
            Class type = a.type();
            Class c = getProperty(type);
            try {
                Property p = (Property) c.getConstructor(Object.class, String.class).newInstance(el, a.name());

                this.getClasses().put(a.name(), type!=Object.class ? type:getField(p.getClass()).getType());
                this.getProperties().put(a.name(), p);
                p.setValue(type.getConstructor().newInstance());
                if(a.XmlData() != Type.NONE) {
                    if(a.XmlData() == Type.ATTRIBUTES)
                        getAttributes().add(a.name());
                    if(a.XmlData() == Type.CONTENT)
                        getContents().add(a.name());
                }
            } catch (Exception ex) {
                //Logger.getLogger(PropertiesBase.class.getName()).log(Level.SEVERE, null, ex);
                // MainApp.LOG.info("Property '" + a.name() + "' not create");
            }
        }

        while (clazz != null){
            Field[] fields = clazz.getDeclaredFields();
            buildFields(fields, el);
            clazz = clazz.getSuperclass();
        }
    }

    private void buildFields(Field[] fields, Element el){
        for(Field f:fields) {
            AddProperty a = f.getAnnotation(AddProperty.class);
            if(a!=null){
                f.setAccessible(true);
                try {
                    if(a.isCreate()){
                        Property p = (Property) f.getType()
                                .getConstructor(Object.class, String.class)
                                .newInstance(this, a.name());
                        f.set(el, p);
                        Field field = getField(p.getClass());
                        Class type = field.getType();
                        if (a.type() != Object.class) type = a.type();
                        this.getClasses().put(a.name(), type);
                        this.getProperties().put(a.name(), p);

                    } else {
                        this.getClasses().put(a.name(), a.type());

                        if(ClassRef.isInterface2(f.getType(), Property.class)) {
                            Object o = f.get(el);
                            this.getProperties().put(a.name(), (Property) o);
                        }
                    }
                    if(a.XmlData() != Type.NONE) {
                        if(a.XmlData() == Type.ATTRIBUTES)
                            getAttributes().add(a.name());
                        if(a.XmlData() == Type.CONTENT)
                            getContents().add(a.name());
                    }

                } catch (Exception ex) {
                    // Logger.getLogger(PropertiesBase.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    f.setAccessible(false);
                }
            }
        }
    }





    /**
     *
     * @param func функция
     * @return
     */
    public static ElementModel getElementModel(Class<Element> clazzEl,  Callback<Class<Element>, Element>  func){
        if(func == null) return null;
        Element el = func.call(clazzEl);
        ElementModel model = new ElementModel();
        model.setElement(el);
        return model;
    }

}
