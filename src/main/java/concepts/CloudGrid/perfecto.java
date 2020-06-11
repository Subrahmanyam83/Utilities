package concepts.CloudGrid;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.sun.webkit.network.URLs.newURL;

public class perfecto {

    public void perfectoWebTest() throws MalformedURLException {
        URL hubUrl = new URL("https://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub/fast");
        DesiredCapabilities capability = new DesiredCapabilities("chrome", "",
                Platform.ANY);
        capability.setCapability("securityToken", "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJzbFV4OFFBdjdVellIajd4YWstR0tTbE43UjFNSllDbC1TRVJiTlU1RFlFIn0.eyJqdGkiOiJkOGIzMTRlNC1kMWQwLTQ2ZWQtYmYzYy0yMjhiMmJiMjQ3MGEiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTY3MTE2MDcwLCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2RlbW8tcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzdWIiOiJiODYzYTI3MS1hNWY2LTQ1OTktYjkzNS1mNDQ3MGRiNmNhMzgiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6Ijc4YjI4YjUxLTc0YmEtNDIzYi04YjUwLTk2NTllMDc2ZGYzMSIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6Ijc5ZTE2YTU3LTUxMGYtNDQwZS05OTdlLTZkMGJmZjE1NzlhMCIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX19.MfrvsZWJ1i3A8JmdF--mSEldJ4QVpqqj2HsDbMezA-3QYcbTsGWQAOvbg2yT_rCemb0fxCbYgrlOveX8upjnjuzbAll_v4AIDu56w9nQoFj4zrB3TyViu4ZOMxYHbNVYM1u8_OPY-KM-BTTemtzbB9dSY3AhhT7wHoPwfv8Bxn9ewYV-N3Cdj5EafoG2BKEPvd5mnLFSsE7752KOvgB0XMRNjIfB2oVlP8zu48ogDJvg39XKifyU6UGo2_8fNpE3894mghJXk3kXTmWPg_n-bW6H1-z-HhBzA4GCXLsuEPP5KRvJJ8Jeyr-4RYo7zXVfq9fkMg2DKwUkOmtK-_AjdA");
        capability.setCapability("platformName", "Windows");
        capability.setCapability("platformVersion", "10");
        capability.setCapability("browserName", "Chrome");
        capability.setCapability("browserVersion", "beta");
        capability.setCapability("location", "EU Frankfurt");
        capability.setCapability("resolution", "1024x768");
        capability.setCapability("tunnelId", "fa1deb0a-0381-461a-a544-48d6965123b2");
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(hubUrl, capability);

        remoteWebDriver.get("https://www.google.com");

        Cookie cookie = new Cookie("Allow_BA_Cookies", "accepted");
        remoteWebDriver.manage().addCookie(cookie);

        remoteWebDriver.close();
        remoteWebDriver.quit();
        System.out.println("Done......");
    }

    @Test
    public void perfectoAndroidTest() throws MalformedURLException {

        DesiredCapabilities capabilities=new DesiredCapabilities("","",Platform.ANY);

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("location", "EU-UK-LON");
        capabilities.setCapability("resolution", "1080x2160");
        capabilities.setCapability("manufacturer", "Google");
        capabilities.setCapability("model", "Pixel 3");
        capabilities.setCapability("app","PRIVATE:ApplicationInstall/android/AndroidAppBA.apk");
        capabilities.setCapability("securityToken","eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJzbFV4OFFBdjdVellIajd4YWstR0tTbE43UjFNSllDbC1TRVJiTlU1RFlFIn0.eyJqdGkiOiJkOGIzMTRlNC1kMWQwLTQ2ZWQtYmYzYy0yMjhiMmJiMjQ3MGEiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTY3MTE2MDcwLCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2RlbW8tcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzdWIiOiJiODYzYTI3MS1hNWY2LTQ1OTktYjkzNS1mNDQ3MGRiNmNhMzgiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6Ijc4YjI4YjUxLTc0YmEtNDIzYi04YjUwLTk2NTllMDc2ZGYzMSIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6Ijc5ZTE2YTU3LTUxMGYtNDQwZS05OTdlLTZkMGJmZjE1NzlhMCIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX19.MfrvsZWJ1i3A8JmdF--mSEldJ4QVpqqj2HsDbMezA-3QYcbTsGWQAOvbg2yT_rCemb0fxCbYgrlOveX8upjnjuzbAll_v4AIDu56w9nQoFj4zrB3TyViu4ZOMxYHbNVYM1u8_OPY-KM-BTTemtzbB9dSY3AhhT7wHoPwfv8Bxn9ewYV-N3Cdj5EafoG2BKEPvd5mnLFSsE7752KOvgB0XMRNjIfB2oVlP8zu48ogDJvg39XKifyU6UGo2_8fNpE3894mghJXk3kXTmWPg_n-bW6H1-z-HhBzA4GCXLsuEPP5KRvJJ8Jeyr-4RYo7zXVfq9fkMg2DKwUkOmtK-_AjdA");
        capabilities.setCapability("appPackage","com.ba.mobile");

        AppiumDriver appiumDriver=new AndroidDriver(new URL("https://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub/fast"),capabilities);
        appiumDriver.findElement(By.id("topDestinationsShowMore")).click();
        appiumDriver.quit();
    }

    @Test
    private void perfectoIOSTest() throws MalformedURLException {

        DesiredCapabilities capabilities=new DesiredCapabilities("","",Platform.ANY);
        capabilities.setCapability("securityToken","eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJzbFV4OFFBdjdVellIajd4YWstR0tTbE43UjFNSllDbC1TRVJiTlU1RFlFIn0.eyJqdGkiOiJkOGIzMTRlNC1kMWQwLTQ2ZWQtYmYzYy0yMjhiMmJiMjQ3MGEiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTY3MTE2MDcwLCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2RlbW8tcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzdWIiOiJiODYzYTI3MS1hNWY2LTQ1OTktYjkzNS1mNDQ3MGRiNmNhMzgiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6Ijc4YjI4YjUxLTc0YmEtNDIzYi04YjUwLTk2NTllMDc2ZGYzMSIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6Ijc5ZTE2YTU3LTUxMGYtNDQwZS05OTdlLTZkMGJmZjE1NzlhMCIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX19.MfrvsZWJ1i3A8JmdF--mSEldJ4QVpqqj2HsDbMezA-3QYcbTsGWQAOvbg2yT_rCemb0fxCbYgrlOveX8upjnjuzbAll_v4AIDu56w9nQoFj4zrB3TyViu4ZOMxYHbNVYM1u8_OPY-KM-BTTemtzbB9dSY3AhhT7wHoPwfv8Bxn9ewYV-N3Cdj5EafoG2BKEPvd5mnLFSsE7752KOvgB0XMRNjIfB2oVlP8zu48ogDJvg39XKifyU6UGo2_8fNpE3894mghJXk3kXTmWPg_n-bW6H1-z-HhBzA4GCXLsuEPP5KRvJJ8Jeyr-4RYo7zXVfq9fkMg2DKwUkOmtK-_AjdA");
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("platformVersion","12.0");
        capabilities.setCapability("location","EU-UK-LON");
        capabilities.setCapability("manufacturer","Apple");
        capabilities.setCapability("model","iPhone-XSMax");
        capabilities.setCapability("deviceName","00008020-001130A13A90003A");
        capabilities.setCapability("app","PRIVATE:ApplicationInstall/iOS/BAFlights_2.1.200.ipa");
        capabilities.setCapability("bundleId","com.britishairways.BAFlights");

        capabilities.setCapability("tunnelIdentifier","fd5b82e8-4e4e-4c3c-afa4-8e7258ce567f");

        AppiumDriver appiumDriver=new IOSDriver(newURL("https://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub/fast"),capabilities);
        appiumDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Later\"]")).click();
        appiumDriver.quit();
    }



}
