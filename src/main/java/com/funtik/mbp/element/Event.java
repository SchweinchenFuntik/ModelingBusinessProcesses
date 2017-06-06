package com.funtik.mbp.element;

/**
 * Created by funtik on 26.03.17.
 */
public interface Event<Obj> {
    void apply(Element el);
    void unApply(Element el);
    default boolean isApplyToElement(Element el){
        return el != null;
    }

    default void applyAll(Element... elements){
        for (Element el:elements) apply(el);
    }

    default void unApplyAll(Element... elements){
        for (Element el:elements) unApply(el);
    }
}