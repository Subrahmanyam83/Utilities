package steps;

import com.google.inject.Inject;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.googlePage;

public class GoogleSteps {

    @Inject
    private googlePage gpage;

    @Given("I load a google page and quit")
    public void navigateToDPResponsivePage() {
        System.out.println("HIII");
        gpage.navigate();
    }

    @Given("^I open google and search for$")
    public void iOpenGoogleAndSearchForData(DataTable data) throws Throwable {
        System.getProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        Thread.sleep(4000L);
        for (String d:data.asList(String.class)
                ) {
            driver.findElement(By.id("lst-ib")).clear();
            driver.findElement(By.id("lst-ib")).sendKeys(d);
        }
    }

    @Given("^I open google and search for (.*)$")
    public void iOpenGoogleAndSearchForData(String data) throws Throwable {
        System.getProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        Thread.sleep(4000L);
        driver.findElement(By.id("lst-ib")).sendKeys(data);
    }
}
