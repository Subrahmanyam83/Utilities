package concepts.regression_stability;

import org.testng.annotations.Test;

import java.util.*;

public class test {

    @Test
    private void testList(){
        List list = new LinkedList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list.get(0));
    }

    @Test
    private void testSet(){

        Set list = new HashSet();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list);
    }


    @Test
    private void testMap(){
        Map list = new HashMap<>();
        list.put(1,"one");
        list.put(2,"two");
        list.put(3,"three");
        list.put(4,"four");
        list.put(5,"five");
        System.out.println(list.get(3));

    }
}
