package edu.hm.sa.reflection.renderer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        return "Instance of " + objectClass.getName() + ":\n";
    }

    private String buildFieldStr(Field field){
        field.setAccessible(true);
        return field.getName() +
                " (Type " + field.getType().getCanonicalName() + "): " +
                fieldObjectToString(field) + "\n";
    }

    protected String fieldObjectToString(Field field){
        Object fieldObject;
        try {
            fieldObject = field.get(object);
            RenderMe fieldAnno = (RenderMe) field.getAnnotation(ANNO_CLASS);
            String fieldWithStr = fieldAnno.with();
            if(!fieldWithStr.equals("N/A")){
                Class rendererClass = Class.forName(fieldWithStr);
                Method renderMethod = rendererClass.getMethod("render", Object.class);
                String renderReturn = (String) renderMethod.invoke(rendererClass, fieldObject);
                return renderReturn;
            }
        } catch (Exception e) {
            e.printStackTrace();
            fieldObject = e;
        }
        return fieldObject.toString();
    }
}
