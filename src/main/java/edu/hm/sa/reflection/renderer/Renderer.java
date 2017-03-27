package edu.hm.sa.reflection.renderer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by maxl on 27.03.17.
 */
public class Renderer{

    private static final Class<RenderMe> ANNO_CLASS = RenderMe.class;

    private Object object;
    private Class objectClass;

    public Renderer(Object object){
        this.object = object;
        this.objectClass = object.getClass();
        //TODO complete this constructor
    }

    public String render(){
        Class objectClass = object.getClass();

        String returnStr = "Instance of " + objectClass + "\n";
        Field[] fields = objectClass.getDeclaredFields();
        for(Field field : fields){
            returnStr += field.getName() + "\n";
            returnStr += field.getAnnotations().getClass() + "\n";
            if(field.isAnnotationPresent(RenderMe.class)){
                returnStr += field.getName() + "\n";
            }
        }
        //TODO complete this method
        return returnStr;
    }
}
