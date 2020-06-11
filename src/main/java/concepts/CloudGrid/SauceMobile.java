package concepts.CloudGrid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class SauceMobile {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platfrom","Windows 10");
        capabilities.setCapability("browserName","chrome");
        capabilities.setCapability("version","64");
        capabilities.setCapability("username","jitendra.jawale");
        capabilities.setCapability("accesskey","c42bc193-2124-493f-a9be-4c28aa8a7e88");

        WebDriver driver = new RemoteWebDriver(url,capabilities);
        driver.get("https://capdev408.uk.ba.com/");
        driver.quit();
        System.out.println("hellooo done");
    }
}
