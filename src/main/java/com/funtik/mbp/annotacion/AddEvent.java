package com.funtik.mbp.annotacion;

import com.funtik.mbp.event.Event;

import java.lang.annotation.*;

/**
 *
 * @author funtik
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(AddEvents.class)
public @interface AddEvent {
    enum TypeEvent{FILTER, HANDLER}
    Class<? extends Event> value();
    TypeEvent type() default TypeEvent.FILTER;
    boolean isCreate() default false;
}
