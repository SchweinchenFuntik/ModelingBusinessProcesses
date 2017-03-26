package com.funtik.mbp.event;

import javafx.scene.input.KeyEvent;

/**
 *
 * @author funtik
 */
public interface EventKey<Obj> extends Event<Obj> {
    public default void pressed(KeyEvent e){}
    public default void realesed(KeyEvent e){}
}
