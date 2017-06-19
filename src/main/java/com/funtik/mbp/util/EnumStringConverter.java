package com.funtik.mbp.util;

import javafx.util.StringConverter;

/**
 * Created by funtik on 19.06.17.
 */
public class EnumStringConverter extends StringConverter<Enum> {
    private Class clazz;

    public EnumStringConverter(Class c){
        clazz = c;
    }

    @Override
    public String toString(Enum o) {
        System.out.println(o);
        return o.toString();
    }

    @Override
    public Enum fromString(String name) {
        System.out.println(Enum.valueOf(clazz, name));
        return Enum.valueOf(clazz, name);
    }
}
