package edu.hm.sa.reflection.renderer;

/**
 * Created by maxl on 30.03.17.
 *
 * Abstract Class CustomRenderer
 *
 * Outline for any custom rendering for any RenderMe/Renderer Field
 */
public abstract class CustomRenderer {

    //All SubClasses will have a render method
    public static String render(Object object){
        return object.toString();
    }
}
