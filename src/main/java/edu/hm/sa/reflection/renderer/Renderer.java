package edu.hm.sa.reflection.renderer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    private static final String RENDER_METHOD_NAME = "render";

    private static Object object;
    private static Class objectClass;

    /**
     * Creates a new Renderer to render this Object
     * @param objectToRender the Object to render
     */
    public Renderer(Object objectToRender){
        object = objectToRender;
        objectClass = object.getClass();
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
        Method[] methods = objectClass.getDeclaredMethods();
        for(Method method: methods){
            if(method.isAnnotationPresent(ANNO_CLASS)){
                returnStr += buildMethodStr(method);
            }
        }
        return returnStr;
    }

    /**
     * Helper method for returning the className of the instance with formatting
     * @return formatted buildInstance String
     */
    private String buildInstanceOfStr(){
        String instanceOf = "Instance of ";
        String objectClassName = objectClass.getName();
        String endFormat = ":\n";
        return instanceOf + objectClassName + endFormat;
    }

    /**
     * Helper method to build a formatted String for a single Field
     * @param field the Field to build
     * @return returns String with name, type, and value
     */
    private String buildFieldStr(Field field){
        field.setAccessible(true);
        String fieldName = field.getName();
        String typeName = field.getType().getCanonicalName();
        String typeNameFormatted = " (Type " + typeName + "): ";
        String objectToString = fieldObjectToString(field);
        String newLine = "\n";
        return fieldName + typeNameFormatted + objectToString + newLine;
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

            //Use reflection to call the custom render method (default toString)
            Class rendererClass = Class.forName(fieldWithStr);
            Method renderMethod = rendererClass.getMethod(RENDER_METHOD_NAME, Object.class);
            return (String) renderMethod.invoke(rendererClass, fieldObject);
        } catch (IllegalAccessException | ClassNotFoundException  | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    /**
     * Helper method to build a String representation of a Method
     * @param method the Method to represent
     * @return a formatted String
     */
    private String buildMethodStr(Method method){
        method.setAccessible(true);
        String fieldName = method.getName();
        String typeName = method.getReturnType().getCanonicalName();
        String typeNameFormatted = " (Return " + typeName + "): ";
        String objectToString = methodReturnToString(method);
        String newLine = "\n";
        return fieldName + typeNameFormatted + objectToString + newLine;
    }

    /**
     * Helper method to return a String representation of what a method returns
     * @param method the Method to visualize
     * @return the "toString" for the Method return
     */
    private String methodReturnToString(Method method){
        Object methodReturn;
        try {
            //Get the object for the field
            methodReturn = method.invoke(object);

            //Get the with field from the RenderMe Annotation
            RenderMe fieldAnno = (RenderMe) method.getAnnotation(ANNO_CLASS);
            String fieldWithStr = fieldAnno.with();

            //Use reflection to call the custom render method (default toString)
            Class rendererClass = Class.forName(fieldWithStr);
            Method renderMethod = rendererClass.getMethod(RENDER_METHOD_NAME, Object.class);
            return (String) renderMethod.invoke(rendererClass, methodReturn);
        } catch (IllegalAccessException | ClassNotFoundException  | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
