package edu.hm.sa.reflection.renderer;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by maxl on 30.03.17.
 */
public class ArrayRenderer extends CustomRenderer{

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

    private static Object[] convertToArray(Object object){
        Class wrapperType = Array.get(object, 0).getClass();
        int length = Array.getLength(object);
        Object[] objectArray = (Object[]) Array.newInstance(wrapperType, length);
        for (int i = 0; i < length; i++) {
            objectArray[i] = Array.get(object, i);
        }
        return objectArray;
    }
}
