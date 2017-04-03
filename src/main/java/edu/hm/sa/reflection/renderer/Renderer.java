package edu.hm.sa.reflection.renderer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by maxl on 27.03.17.
 *
 * Class Renderer
 *
 * Renders a target Object, by default this will be equivalent to a more verbose toString
 */
public class Renderer{

    private static final Class ANNO_CLASS = RenderMe.class;

    private static Object object;
    private static Class objectClass;

    /**
     * Creates a new Renderer to render this Object
     * @param object the Object to render
     */
    public Renderer(Object object){
        this.object = object;
        this.objectClass = object.getClass();
    }

    /**
     * Essentially toString but customized to include fieldName and fieldType
     * @return verbose String representation of the Object
     */
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

    /**
     * Helper method for returning the className of the instance with formatting
     * @return formatted buildInstance String
     */
    private String buildInstanceOfStr(){
        return "Instance of " + objectClass.getName() + ":\n";
    }

    /**
     * Helper method to build a formatted String for a single Field
     * @param field the Field to build
     * @return returns String with name, type, and value
     */
    private String buildFieldStr(Field field){
        field.setAccessible(true);
        return field.getName() +
                " (Type " + field.getType().getCanonicalName() + "): " +
                fieldObjectToString(field) + "\n";
    }

    /**
     * Helper method to return a string representation of an Object
     * @param field the Field to visualize
     * @return the "toString" for the Object
     */
    private String fieldObjectToString(Field field){
        Object fieldObject;
        try {
            //Get the object for the field
            fieldObject = field.get(object);

            //Get the with field from the RenderMe Annotation
            RenderMe fieldAnno = (RenderMe) field.getAnnotation(ANNO_CLASS);
            String fieldWithStr = fieldAnno.with();

            //If it is not the default, use reflection to call the custom render method
            if(!fieldWithStr.equals(RenderMe.DEFAULT_RENDER_CLASS)){
                Class rendererClass = Class.forName(fieldWithStr);
                Method renderMethod = rendererClass.getMethod("render", Object.class);
                String renderReturn = (String) renderMethod.invoke(rendererClass, fieldObject);
                return renderReturn;
            }
        } catch (Exception e) {
            e.printStackTrace();
            fieldObject = e;
        }

        //Call the default toString because there was an error or no specified with field
        return fieldObject.toString();
    }
}
