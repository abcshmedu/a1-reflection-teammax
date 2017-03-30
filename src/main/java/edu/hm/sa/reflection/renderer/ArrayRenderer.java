package edu.hm.sa.reflection.renderer;

/**
 * Created by maxl on 30.03.17.
 */
public class ArrayRenderer extends CustomRenderer{

    public static String render(Object[] objectArray) {
        return objectArray.toString();
    }
}
