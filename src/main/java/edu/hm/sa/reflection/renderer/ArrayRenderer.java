package edu.hm.sa.reflection.renderer;

import java.lang.reflect.Array;

/**
 * Created by maxl on 30.03.17.
 *
 * SubClass of CustomRenderer, ArrayRenderer
 *
 * Renders any array, primitive and Object types
 */
public class ArrayRenderer extends CustomRenderer{

    private static final int ZEROETH_INDEX = 0;

    /**
     * Each element will be rendered using toString
     * @param object the Object that should be an Array
     * @return all elements, comma separated, surrounded by brackets
     */
    public static String render(Object object) {
        Object[] objectArray = convertToArray(object);
        String objectString = "[";
        for(int i = 0; i < objectArray.length; i++){
            objectString += objectArray[i];
            if(i < objectArray.length){
                objectString += ", ";
            }
        }
        return objectString + "]";
    }

    /**
     * Converts an Object to an Object[]
     * @param object to Object to convert
     * @return a new Object[]
     */
    private static Object[] convertToArray(Object object){
        int length = Array.getLength(object);
        if(length == 0 || object == null){
            return new Object[0];
        }else {
            Class wrapperType = Array.get(object, ZEROETH_INDEX).getClass();
            Object[] objectArray = (Object[]) Array.newInstance(wrapperType, length);
            for (int i = 0; i < length; i++) {
                objectArray[i] = Array.get(object, i);
            }
            return objectArray;
        }
    }
}
