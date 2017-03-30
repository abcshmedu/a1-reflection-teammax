package edu.hm.sa.reflection.renderer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by maxl on 27.03.17.
 */
public class Renderer{

    private static final Class ANNO_CLASS = RenderMe.class;

    private Object object;
    private Class objectClass;

    public Renderer(Object object){
        this.object = object;
        this.objectClass = object.getClass();
    }

    public String render(){
        String returnStr = buildInstanceOfStr();
        Field[] fields = objectClass.getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(ANNO_CLASS)){
                returnStr += buildFieldStr(field);
            }
        }
        return returnStr;
    }

    private String buildInstanceOfStr(){
        return "Instance of " + objectClass.getName() + "\n";
    }

    private String buildFieldStr(Field field){
        field.setAccessible(true);
        return field.getName() +
                " (Type " + field.getType().getName() + ") " +
                fieldObjectToString(field) + "\n";
    }

    protected String fieldObjectToString(Field field){
        Object fieldObject;
        try {
            fieldObject = field.get(object);
        } catch (Exception e) {
            fieldObject = e;
        }
        return fieldObject.toString();
    }
}
