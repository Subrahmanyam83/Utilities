package tests;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class testDelete {

    String url = "https://thetreasuredivine.com/";
    WebDriver driver = new ChromeDriver();

    @BeforeClass
    private void setup(){
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    }

    @Test
    private void test1(){
        System.out.println(new Date());
        driver.get(url);
        String newHitNum = driver.findElement(By.xpath("//*[@id=\"blog-stats-3\"]/ul/li")).getText().toString().replace(" visits","").trim();
        System.out.println(newHitNum);
    }

    @Test
    private void getHitCount() throws InterruptedException {
        driver.get(url);
        String hitNum = "1,632,161";

        while(true){
            String newHitNum = driver.findElement(By.xpath("//*[@id=\"blog-stats-3\"]/ul/li")).getText().toString().replace(" visits","").trim();
            driver.navigate().refresh();
            if(!newHitNum.equals(hitNum)){
                hitNum=newHitNum;
                System.out.println(newHitNum+"---"+new Date());
            }
            Thread.sleep(60000L);
        }
    }


    @Test
    public void hitUrl(){
        driver.get(url);
        int num=0;
        while (true) {
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
            num++;
            System.out.println(num);
        }
    }


    @AfterTest
    private void quit(){
        driver.quit();
    }

    @Test
    public void test() throws IOException {

        int initialHitCount = 1588419;

        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);

        while (true){
            client.executeMethod(getMethod);
        }
    }
}
