package concepts.TestNGParallel;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestC extends BaseClass{

    @BeforeMethod
    private void setup(){
        System.out.println("STARTING TEST C"+getTime());
    }

    @Test
    private void testI() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test I");
    }

    @Test
    private void testJ() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test J");
    }

    @Test
    private void testK() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test K");
    }

    @Test
    private void testL() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test L");
    }

    @AfterMethod
    private void teardown(){
        System.out.println("ENDING TEST C"+getTime());
    }
}
