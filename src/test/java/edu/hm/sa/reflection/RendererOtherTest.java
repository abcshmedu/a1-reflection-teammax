package edu.hm.sa.reflection;

import edu.hm.sa.reflection.renderer.Renderer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by maxl on 06.04.17.
 */
public class RendererOtherTest {
    private SomeOtherClass toRender;
    private Renderer renderer;

    @Before
    public void setUp() {
        toRender = new SomeOtherClass("hello");
        renderer = new Renderer(toRender);
    }

    //Package structure is a little different: edu.hm.SomeClass -> edu.hm.sa.reflection.SomeClass
    @Test
    public void testRendering() throws Exception {
        assertEquals("Instance of edu.hm.sa.reflection.SomeOtherClass:\n" +
                "aString (Type java.lang.String): hello\n" +
                "aLong (Type java.lang.Long): 11\n" +
                "anArray (Type double[]): [0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, ]\n" +
                "getaString (Return java.lang.String): hello\n", renderer.render());

    }
}
