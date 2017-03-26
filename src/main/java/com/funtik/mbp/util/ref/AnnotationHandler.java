package com.funtik.mbp.util.ref;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author funtik
 */
public class AnnotationHandler<A extends Annotation, Obj> {
    private Class<A> a;
    private FuncHandler<A,Obj> f;
    private ElementType[] type;
    
    public  static Collection<AnnotationHandler> 
        getHandler(FuncHandler<? extends Annotation,?> f, Class<? extends Annotation>... a){
            
        ArrayList ahs = new ArrayList(a.length);
        for(Class c:a) ahs.add(new AnnotationHandler(c, f));
        return ahs;
    }
    
    public AnnotationHandler(){ intit(null, null); }
    public AnnotationHandler(Class<A> a){ intit(a, null); }
    public AnnotationHandler(Class<A> a, FuncHandler<A,Obj> f){
        intit(a, f); 
    }
    
    private void intit(Class<A> a,  FuncHandler<A,Obj> f){
        this.a = a; this.f = f;
        type = a.getAnnotation(Target.class).value();
    }
    
    public boolean isElementType(ElementType et){
        for(ElementType e:type) if(e==et) return true;
        return false;
    }
    
    public void setTypeAnnotation(Class<A> a){
        this.a = a; type = a.getAnnotation(Target.class).value();
    }
    public Class<A> getTypeAnnotation(){ return a; }
    public void setFucntion( FuncHandler<A,Obj> f){ this.f = f; }
    public FuncHandler<A,Obj> getFucntion(){ return f; }
}
