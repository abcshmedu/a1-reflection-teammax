package edu.hm.sa.reflection;

import edu.hm.sa.reflection.renderer.Renderer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by maxl on 06.04.17.
 */
@RunWith(Parameterized.class)
public class ParametrizedRunner {

    private static final int someClassIntVal = 5;
    private static final String someOtherClassStrVal = "hello";

    private static final String someClassString = "Instance of edu.hm.sa.reflection.SomeClass:\n" +
            "foo (Type int): " + someClassIntVal + "\narray (Type int[]): [1, 2, 3, ]\ndate (Type java" +
            ".util.Date): Fri Jan 02 11:17:36 CET 1970\n";

    private static final String someOtherClassString = "Instance of edu.hm.sa.reflection.SomeOtherClass:\n" +
            "aLong (Type java.lang.Long): 11\n" +
            "anArray (Type double[]): [0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, ]\n" +
            "getaString (Return java.lang.String): " + someOtherClassStrVal + "\n";
    
    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new SomeClass(someClassIntVal), someClassString },
                { new SomeOtherClass(someOtherClassStrVal), someOtherClassString }
        });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ Object testClass;

    @Parameter(1)
    public /* NOT private */ String expectedOutput;

    //Package structure is a little different: edu.hm.SomeClass -> edu.hm.sa.reflection.SomeClass
    @Test
    public void test(){
        Renderer renderer = new Renderer(testClass);
        String actualString = renderer.render();
        assertEquals(expectedOutput, actualString);
    }
}
