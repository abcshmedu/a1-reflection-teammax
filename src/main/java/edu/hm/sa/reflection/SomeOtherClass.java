package edu.hm.sa.reflection;

import edu.hm.sa.reflection.renderer.RenderMe;

import java.util.Date;

/**
 * Created by maxl on 06.04.17.
 *
 * Additional TestClass SomeOtherClass
 */
public class SomeOtherClass {

    //Shouldn't render this
    private String aString;

    @RenderMe
    public Long aLong = new Long(11);

    @RenderMe(with="edu.hm.sa.reflection.renderer.ArrayRenderer")
    double[] anArray = { 0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, };

    //Shouldn't render this
    private Date aDate = new Date(123456789);

    public SomeOtherClass(String string) {
        aString = string;
    }

    //Should render the method name, return type and the value
    @RenderMe
    public String getaString(){
        return aString;
    }
}
