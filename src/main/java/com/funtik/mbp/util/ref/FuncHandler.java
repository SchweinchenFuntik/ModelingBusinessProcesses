package com.funtik.mbp.util.ref;

import java.lang.annotation.Annotation;

/**
 *
 * @author funtik
 * @param <A> Тип аннотации
 * @param <T> Тип обекта класса над котором используется анотация
 */

@FunctionalInterface
public interface FuncHandler<A extends Annotation, T> {
    /**
     * Данный метод будет вызван если над обектом будет аннотация этой функции,
     * котороя определяется в классе AnnotationWorker
     * @param obj Объкт над которым была применена анотация (Class, Field, Method)
     * @param o Объект где была использована анотация 
     * @param a Анотация котороя использовалась 
     */
    void call(T obj, A a, Object o);
}
