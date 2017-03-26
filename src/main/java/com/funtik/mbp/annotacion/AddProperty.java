package com.funtik.mbp.annotacion;

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
    public String name();
    public Class type() default Object.class;
    public boolean isCreate() default true;
}
