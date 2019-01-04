package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class googlePage {

    WebDriver driver;
    private final String driverPath = "/usr/local/bin/";


    public void navigate(){
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
        driver= new ChromeDriver();
        driver.navigate().to("http://www.google.com");
        System.out.println("hii");
        driver.quit();
    }
}
