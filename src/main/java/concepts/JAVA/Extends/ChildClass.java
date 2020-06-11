package concepts.JAVA.Extends;

import org.testng.annotations.Test;

public class ChildClass extends ParentClass {

    ChildClass(){
        System.out.println("Calling Child class\n");
    }

    @Test
    private void myTest(){
        //System.out.println(platform);
    }
}
