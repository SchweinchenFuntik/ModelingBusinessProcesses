package com.funtik.mbp.util.xml;

import java.util.List;

/**
 * Created by funtik on 08.05.17.
 */
public interface XmlData<T> {
    enum Type{ATTRIBUTES, CONTENT, ELEMENT, NONE}

    default T getObject(){
        return (T)this;
    }
    List <String> getAttributes();
    List <String> getContents();
    Class getClass(String attr);
    Object getValueAttributes(String attr);
    Object getValueContent(String content);
    void setValueAttributes(String attr, Object value);
    void setValueContent(String content, Object value);

    // ??????
    default String getName(){
        return getObject().getClass().getSimpleName();
    }

}
