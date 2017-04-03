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
 * Targets the class variables (fields)
 * RetentionPolicy is runtime (for the prupose of reflection)
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RenderMe {

    static final String DEFAULT_RENDER_CLASS = "N/A";

    /**
     * Place to specify the rendering class
     * @return the String representation of the classname
     */
    String with() default DEFAULT_RENDER_CLASS;
}
