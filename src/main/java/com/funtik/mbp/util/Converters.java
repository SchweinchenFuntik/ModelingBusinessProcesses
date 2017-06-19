package com.funtik.mbp.util;

import com.funtik.mbp.element.Element;
import com.funtik.mbp.gui.elements.Point;
import com.funtik.mbp.model.ElementModel;
import com.funtik.mbp.util.ref.ClassRef;
import com.funtik.mbp.util.xml.XmlData;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * Created by funtik on 13.06.17.
 */
public class Converters {
    public static StringConverter getConverter(Class clazz){
        if(clazz == Integer.class || clazz == int.class)
            return new IntegerStringConverter();
        if(clazz == Double.class || clazz == double.class)
            return new DoubleStringConverter();
        if(clazz == Float.class || clazz == float.class)
            return new FloatStringConverter();
        if(ClassRef.isClass(clazz, Enum.class))
            return new EnumStringConverter(clazz);
        return null;
    }

    private static String [] path = {
            "com.funtik.mbp.gui.elements.", "com.funtik.mbp.model."
    };
    public static Class getClass(String className){
        for (String p:path)
            try {
                return Class.forName(p+className);
            } catch (ClassNotFoundException e) {
                // LOG
            }
        return null;
    }

    public static XmlData getXmlDataElement(Class clazz){
        if(ClassRef.isInterface2(clazz, Element.class))
            return ElementModel.getElementModel(clazz, cl ->  (Element) ClassRef.createObject(clazz));
        return null;
    }

}
