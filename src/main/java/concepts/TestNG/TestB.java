package concepts.TestNG;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestB {

    @Test
    private void test1(ITestContext iTestContext){

        System.out.println("Driver for :"+iTestContext.getCurrentXmlTest().getParameter("device")+"-------"+iTestContext.getAttribute("name"));

    }
}
