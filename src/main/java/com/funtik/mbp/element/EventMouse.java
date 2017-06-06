package com.funtik.mbp.element;

import javafx.scene.input.MouseEvent;

/**
 * Интерфейс служит для заметы поведению событий, проще говоря заменить события 
 * по умолчанию на специальные события, например нажата кномка рисования елемента.
 * Интерфейс заменяе все типа события кроме ANY, так как нельзя полностю отказатся 
 * от всех событий по умолчанию. Также даный интерфейс служит для определения событий 
 * отдельных елементов.
 * @author funtik
 */
public interface EventMouse<Obj> extends Event<Obj>{
    enum Type{CLICK, DRAGGED, PRESSED, RELEASSED, ENTERED, EXITED}
    
    default void click(MouseEvent e){}
    /**
     * Когда мышка нажата и находися над обектом
     * @param e 
     */
    default void dragged(MouseEvent e){}
    default void pressed(MouseEvent e){}
    default void released(MouseEvent e){}
    default void entered(MouseEvent e){}
    default void exited(MouseEvent e){}
    /**
     * Когда перемещается мышка внутри елемента и приэтом кнопки не нажаты
     * @param e 
     */
    default void moved(MouseEvent e){}
    /**
     * Перетаскивание обекта
     * @param e 
     */
     default void draggedDetected(MouseEvent e){}

    
}
