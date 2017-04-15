package com.funtik.mbp.event;

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
    public enum Type{CLICK, DRAGGED, PRESSED, RELEASSED, ENTERED, EXITED}
    
    public default void click(MouseEvent e){}
    /**
     * Когда мышка нажата и находися над обектом
     * @param e 
     */
    public default void dragged(MouseEvent e){}
    public default void pressed(MouseEvent e){}
    public default void released(MouseEvent e){}
    public default void entered(MouseEvent e){}
    public default void exited(MouseEvent e){}
    /**
     * Когда перемещается мышка внутри елемента и приэтом кнопки не нажаты
     * @param e 
     */
    public default void moved(MouseEvent e){}
    /**
     * Перетаскивание обекта
     * @param e 
     */
    public default void draggedDetected(MouseEvent e){}

    
}
