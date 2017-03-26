package com.funtik.mbp.util.ref;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author funtik
 */
public class ClassRef {
    private static final Set<AnnotationHandler> WORKER = new HashSet<>();
    
    private final HashMap<String, Field> field = new HashMap<>();
    private final Object obj;
   
    public ClassRef(Object o){
        obj = o;
    }
   
    public <T> T addField(String name){
        Field f = getField(obj.getClass(), name);
        f.setAccessible(true);
        field.put(name, f);
        try {
            return (T)f.get(obj);
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(ClassRef.class.getName()).log(Level.SEVERE, null, ex);
        } return null;
    }
    
    public <T> T getValueField(String name) {
        try {
            return (T)field.get(name).get(obj);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ClassRef.class.getName()).log(Level.SEVERE, null, ex);
        } return (T)null;
    }
    
    public <T> void setValueField(String name, T t){
        try {
            field.get(name).set(obj, t);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(ClassRef.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public static Field getField(Class c, String name){
        Field [] mf = c.getDeclaredFields();
        for (Field f : mf) if (f.getName().equals(name)) return f; 
        c = c.getSuperclass();
        if(c!=null) return getField(c, name);
        return null;
    }
    
    public static void init(Object o){
        Class c = o.getClass();
        ArrayList<Field> fields = new ArrayList(Arrays.asList(c.getDeclaredFields()));
        ArrayList<Method> methods = new ArrayList(Arrays.asList(c.getDeclaredMethods()));
        // подумать, идея 
        WORKER.forEach(w -> {
            if(w.isElementType(ElementType.TYPE)) apply(c, w, o);
            if(w.isElementType(ElementType.METHOD))
                methods.forEach(m -> apply(m, w, o));
            if(w.isElementType(ElementType.FIELD)) 
                fields.forEach(f -> apply(f, w, o));
        });
    }
    // подумать и разделить на разніе методы
    public static <A extends Annotation> A sheartAnnotacion(Class c, Class<A> a){
        A an = (A)c.getAnnotation(a);
        if(an!=null) return an;
        Class superClass = c.getSuperclass();
        if(superClass==null) return null;
        return sheartAnnotacion(superClass, a);
    }
    //????
    public static void isAnClass(Object o, AnnotationHandler w){
        Annotation a = sheartAnnotacion(o.getClass(), w.getTypeAnnotation());
        FuncHandler func = w.getFucntion();
        if(a!=null && func!=null) func.call(o, a, o);
    } 
    
    public static  void apply(AnnotatedElement ae, AnnotationHandler w, Object o){
        Annotation a = ae.getAnnotation(w.getTypeAnnotation());
        FuncHandler func = w.getFucntion();
        if(a!=null && func!=null) func.call(ae, a, o);
    }
    
    public static boolean isInterface(Class c, Class i){
        Class [] interf = c.getInterfaces();
        for(Class cl:interf) if(cl==i) return true;
        Class s = c.getSuperclass();
        return s==Object.class ? false:isInterface(s, i);
    }
    public static boolean isClass(Class c, Class sheart){
        if(c == sheart) return true;
        Class sp = c.getSuperclass();
        if(sp == sheart) return true;
        return sp==null ? false:isClass(sp, sheart);
    } 
    
    public static Set<AnnotationHandler> getAnnotations(){ return WORKER; }

}
