package com.funtik.mbp.annotacion;

import com.funtik.mbp.event.EventMouse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author funtik
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultEvent {
    Class<? extends EventMouse> value();
}
