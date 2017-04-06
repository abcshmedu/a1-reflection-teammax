package edu.hm.sa.reflection.renderer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by maxl on 27.03.17.
 *
 * Annotation RenderMe
 *
 * Targets the class variables and parameterless (fields, methods)
 * (Methods requiring parameters will throw exceptions but will partially render)
 *
 * RetentionPolicy is runtime (for the purpose of reflection)
 */

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RenderMe {

    //Base Class with a basic usage of toString for rendering an Object
    String DEFAULT_RENDER_CLASS = "edu.hm.sa.reflection.renderer.CustomRenderer";

    /**
     * Place to specify the rendering class
     * @return the String representation of the classname
     */
    String with() default DEFAULT_RENDER_CLASS;
}
