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

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class testDelete {


    @Test
    private void test1(){

        System.out.println(getClass().getSimpleName());
        System.out.println(" Hi \033[1m      Exception stacktrace:    \033[0m  This is me");

        String bold= "\033[1mJanuary\033[0m";
        System.out.printf("%100s\n", bold);

    }

}
