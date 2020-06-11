package concepts.TestNGParallel;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestB extends BaseClass{

    @BeforeMethod
    private void setup(){
        System.out.println("STARTING TEST B"+getTime());
    }

    @Test
    private void testE() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test E");

    }

    @Test
    private void testF() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test F");
    }

    @Test
    private void testG() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test ~G");
    }

    @Test
    private void testH() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test H");
    }

    @AfterMethod
    private void teardown(){
        System.out.println("ENDING TEST B"+getTime());
    }
}
