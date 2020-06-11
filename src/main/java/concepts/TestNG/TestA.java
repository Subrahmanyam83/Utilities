package concepts.TestNG;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;

public class TestA {

    String driver;

    @BeforeTest
    public void setup(ITestContext iTestContext) {
        System.out.println("IN SETUP");
        System.out.println(iTestContext.getCurrentXmlTest().getParameter("device"));
        if(iTestContext.getCurrentXmlTest().getParameter("device").equalsIgnoreCase("ios")){
            iTestContext.setAttribute("name","subu");
        }else{
            iTestContext.setAttribute("name","jitu");
        }
        System.out.println(iTestContext.getCurrentXmlTest().getParameter("age"));
    }
//
//    @Test
//    private void test1(ITestContext iTestContext) throws Exception {
//        System.out.println("IN TEST");
//    }
}
