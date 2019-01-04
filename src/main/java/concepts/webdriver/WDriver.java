package concepts.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class WDriver {

    DesiredCapabilities capabilities=DesiredCapabilities.chrome();

    public void setLogging(){
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.OFF);
        logs.enable(LogType.SERVER, Level.OFF);
        logs.enable(LogType.DRIVER, Level.OFF);
        logs.enable(LogType.PROFILER, Level.OFF);
        logs.enable(LogType.CLIENT, Level.OFF);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS,logs);
        WebDriver driver = new ChromeDriver(capabilities);
    }

    @Test
    public void testOne(){
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver(capabilities);
        WebElement element = driver.findElement(By.id("jkhkjh"));
        boolean isDisplayed = element.isDisplayed();
        System.out.println(isDisplayed);
    }
}
