package com.funtik.mbp.util.elements;

import com.funtik.mbp.element.ConnectElment;
import com.funtik.mbp.element.Element;

/**
 * Created by funtik on 18.05.17.
 */
public class DefaultConnectElement<IN extends Element, OUT extends Element> implements ConnectElment<IN, OUT> {
    public boolean connect(IN in, OUT out){
        return connect(in, out, 0, 0, false);
    }
}
