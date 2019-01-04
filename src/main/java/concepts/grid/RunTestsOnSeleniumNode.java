package concepts.grid;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class RunTestsOnSeleniumNode {

    WebDriver remoteDriver=null;
    DesiredCapabilities caps = null;
//    String hubHostAndPort="http://selenium.baplc.com:4444/";
    String hubHostAndPort="http://lhrvms-aplqv008.uk.ad.ba.com:5555/";
    String hubUrl=hubHostAndPort+"wd/hub";
    String sessionID=null;
    String hostname=null;

    @BeforeClass
    public WebDriver getDriver() throws MalformedURLException {

        caps = DesiredCapabilities.chrome();
        caps.setPlatform(Platform.WINDOWS);

        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.CLIENT, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        logs.enable(LogType.SERVER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logs);
        caps.setCapability("webdriver.chrome.logfile","logs.log");
        remoteDriver = new RemoteWebDriver(new URL(hubUrl),caps);
        ((RemoteWebDriver) remoteDriver).setLogLevel(Level.ALL);
        return remoteDriver;
    }

    @Test
    public void runTests() throws IOException, InterruptedException {
        System.out.println(remoteDriver.manage().logs().getAvailableLogTypes());
        remoteDriver.get("http://www.google.com");

//        sessionID=((RemoteWebDriver) remoteDriver).getSessionId().toString();
//        String response=getNodeHostName();
//        JsonParser jsonParser = new JsonParser();
//        JsonElement jsonElement = jsonParser.parse(response);
//        String hostUrl = ((JsonObject) jsonElement).get("proxyId").toString();
//        Pattern pattern = Pattern.compile("\\//(.+?):");
//        Matcher m = pattern.matcher(hostUrl);
//
//        if(m.find()){
//            hostname= m.group(1);
//        }
//
//        String s1 = getOutputOfCommand(hostname);
//        System.out.println("IP is: "+s1);
//        System.setProperty("NODEIP",s1);

    }

    public String getOutputOfCommand(String hostname) throws IOException {
        String s;
        String IP=null;
        Process process = Runtime.getRuntime().exec("host "+hostname);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (( s = stdInput.readLine()) != null) {
            IP=s.split("has address ")[1].trim();
            System.out.println("HiL "+IP);
        }

        InetAddress address = InetAddress.getByName(hostname);
        IP=address.getHostAddress();

        System.out.println("HiL "+IP);
        return IP;
    }

    public String getNodeHostName() throws IOException {

        String responseBody=null;
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(hubHostAndPort+"grid/api/testsession?session="+sessionID);
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);
        responseBody = EntityUtils.toString(response.getEntity());
        return responseBody;
    }

    //26407870-b4df-4948-be3b-d49dd04cec94

    @AfterClass
    public void closeDriver(){
        remoteDriver.quit();
    }
}
