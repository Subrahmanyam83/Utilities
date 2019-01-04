package concepts.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClient {

    private String URL = "http://blx42av01-wl01.baplc.com:7041/wctools/StuboIntegratorSession.jsp";
    private String URL1 = "http://lxvirt538-wl01.baplc.com:7041/test/StuboIntegratorSession.jsp?stuboServerPort=8001&stuboServerIpAddress=stubo-ba-com39.baplc.com&action=createAndBeginSession&sessionId=215641197&scenarioName=OLCI81_CMTest_olci&playbackIfScenarioExists=true";
    private String URL2 = "http://lxvirt512-wl01.baplc.com:7041/wctools/StuboIntegratorSession.jsp?" +
            "stuboServerPort=8001&stuboServerIpAddress=stubo-ba-com39.baplc.com";
           /* + "&action=createAndBeginSession"
            + "&sessionId=215641193"
            + "&scenarioName=OLCI81_CMTest_olci"
            + "&playbackIfScenarioExists=true";*/
    CloseableHttpClient client;
    HttpPost httpPost;
    HttpContext context;

    @BeforeMethod
    public void setup() throws URISyntaxException {
        client = HttpClientBuilder.create().build();
        httpPost = new HttpPost();
        httpPost.setURI(new URI(URL1));
        context = getHttpClientContext();
    }

    @Test
    public void getResponse() throws IOException {
        HttpResponse response = client.execute(httpPost,context);
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    public HttpContext getHttpClientContext() {
        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("testblsuser","testblsuser"));
        HttpContext httpContext = new HttpClientContext();
        ((HttpClientContext)httpContext).setCredentialsProvider(credsProvider);
        return httpContext;
    }
}