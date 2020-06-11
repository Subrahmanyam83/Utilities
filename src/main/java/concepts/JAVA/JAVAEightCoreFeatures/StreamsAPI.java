package concepts.JAVA.JAVAEightCoreFeatures;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsAPI {

    @Test
    private void testJAVAStreams(){

        List<Product> productsList = new ArrayList<Product>();
        //Adding Products
        productsList.add(new Product(1,"HP ",25000f));
        productsList.add(new Product(2,"Dell Laptop",30000f));
        productsList.add(new Product(3,"Lenevo ",28000f));
        productsList.add(new Product(4,"Sony Laptop",28000f));
        productsList.add(new Product(5,"Apple ",90000f));
        productsList.add(new Product(6,"Lenovo Laptop",90000f));
        productsList.add(new Product(7,"Apple Laptop",90000f));
        productsList.add(new Product(8,"HP Laptop",90000f));

        productsList.
                stream().
                filter(p-> p.name.contains("Laptop")).
                forEach(System.out::println);

        productsList.stream().max((p1,p2)->p1.id>p2.id?1:-1).get();
    }
}
