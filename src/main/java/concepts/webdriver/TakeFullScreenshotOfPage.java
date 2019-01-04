package concepts.webdriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TakeFullScreenshotOfPage {

    //protected BrowserManagement browserManagement;

    @Test
    public void takeScreenshot() throws IOException, InterruptedException, AWTException {
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                Arrays.asList("--start-maximized", "allow-running-insecure-content", "ignore-certificate-errors"));
        capability.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(options);
        String url = "https://stackoverflow.com/questions/42973508/how-to-take-full-page-screenshot-of-a-scrollable-webpage-using-selenium-webdrive";
        driver.get(url);
        driver.manage().window().maximize();

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "png", new File("mypng"));

        driver.close();

    }
}
