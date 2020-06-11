package concepts.JAVA.JAVAEightCoreFeatures;

import org.testng.annotations.Test;

import java.util.*;

public class Lambdas {

    @Test
    /*  It provides a clear and concise way to represent one method interface using an expression.
    It is very useful in collection library. It helps to iterate, filter and extract data from collection.
    The Lambda expression is used to provide the implementation of an interface which has functional interface.
    It saves a lot of code. In case of lambda expression, we don't need to define the method again for providing the implementation.
    Here, we just write the implementation code. */

    /* Reference: https://www.javatpoint.com/java-lambda-expressions*/

    private void lambdas(){
        List<Product> list=new ArrayList<Product>();

        //Adding Products
        list.add(new Product(3,"HP Laptop",25000f));
        list.add(new Product(2,"Keyboard",300f));
        list.add(new Product(1,"Dell Mouse",150f));

        Comparator<Product> mycomparator1 = Comparator.comparing(Product::getName); // This is a comparator with a sort key
        Comparator<Product> mycomparator2 = Comparator.comparing(Product::getName,(s1, s2)-> s2.compareTo(s1)); // This is a comparator with a sort key with a Comparator Variant
        Comparator<Product> mycomparator3 = Comparator.<Product>reverseOrder(); // This is a comparator with a sort key


        list.sort(mycomparator1);
        // OR
        list.sort(mycomparator2);


        List<Integer> numList = Arrays.asList(12, 10, 15, 8, 11);
        Collections.sort(numList, Comparator.naturalOrder());
        numList.forEach(System.out::println);

        list.forEach(n-> System.out.println(n.name));
    }
}


class Product implements Comparable<Product> {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    int id;
    String name;
    float price;
    public Product(int id, String name, float price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Product o) {
        return 0;
    }
}
