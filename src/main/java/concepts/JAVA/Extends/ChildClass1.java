package concepts.JAVA.Extends;

import org.testng.annotations.Test;

public class ChildClass1 extends ParentClass {

    ChildClass1(){
        System.out.println("Calling Child class2\n");
    }

    ChildClass1(int a){
    }

    @Test
    private void myTest(){
        ChildClass1 c1 = new ChildClass1();
        ChildClass1 c3 = new ChildClass1();

        ChildClass1 c10 = new ChildClass1(10);
        ChildClass1 c11 = new ChildClass1(10);

        System.out.println("--"+c1.hashCode());
        System.out.println("--"+c3.hashCode());
        System.out.println(c10.equals(c11));

    }
}
