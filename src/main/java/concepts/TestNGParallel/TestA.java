package concepts.TestNGParallel;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestA extends BaseClass{

    @BeforeMethod
    private void setup(){
        System.out.println("STARTING TEST A"+getTime());
    }

    @Test(description = " As a user I a need to select a outboundf and inbound flight")
    private void testA(ITestContext iTestContext) throws InterruptedException {
        System.out.println("**********");
        System.out.println(iTestContext.getSuite().getXmlSuite().getThreadCount());
        getTime();
        Thread.sleep(2000L);
        System.out.println("Test A");
    }

    @Test
    private void testB() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test B");
    }

    @Test
    private void testC() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test C");
    }

    @Test
    private void testD() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("Test D");
    }

    @AfterMethod
    private void teardown(){
        System.out.println("ENDING TEST A"+getTime());
    }

}
