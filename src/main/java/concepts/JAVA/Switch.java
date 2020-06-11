package concepts.JAVA;

import org.testng.annotations.Test;

public class Switch {

    @Test
    private void testSwitch(){
        System.out.println("Hello");
    String name = "B";
        switch (name){

            case "A":
                System.out.println("A");

                default:
                    System.out.println("default");
        }

    }
}
