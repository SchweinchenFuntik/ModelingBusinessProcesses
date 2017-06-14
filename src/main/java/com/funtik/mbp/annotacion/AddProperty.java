package com.funtik.mbp.annotacion;

import com.funtik.mbp.util.xml.XmlData;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author funtik
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Repeatable(AddProperties.class)
public @interface AddProperty {
    String name();
    Class type() default Object.class;
    boolean isCreate() default true;
    XmlData.Type XmlData() default XmlData.Type.ATTRIBUTES;
}
