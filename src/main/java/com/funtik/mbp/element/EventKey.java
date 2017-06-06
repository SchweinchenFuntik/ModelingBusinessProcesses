package com.funtik.mbp.element;

import javafx.scene.input.KeyEvent;

/**
 *
 * @author funtik
 */
public interface EventKey<Obj> extends Event<Obj> {
    default void pressed(KeyEvent e){}
    default void realesed(KeyEvent e){}
}
