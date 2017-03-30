package edu.hm.sa.reflection;

import static org.junit.Assert.*;
import org.junit.*;
import edu.hm.sa.reflection.renderer.*;

public class RendererTest {
    private SomeClass toRender;
    private Renderer renderer;
    @Before
    public void setUp() {
        toRender = new SomeClass(5);
        renderer = new Renderer(toRender);
    }

    //Package structure is a little different: edu.hm.SomeClass -> edu.hm.sa.reflection.SomeClass
    @Test
    public void testRendering() throws Exception {
        assertEquals("Instance of edu.hm.sa.reflection.SomeClass:\n" +
                "foo (Type int): 5\narray (Type int[]): [1, 2, 3, ]\ndate (Type java" +
                ".util.Date): Fri Jan 02 11:17:36 CET 1970\n", renderer.render());
    }
}