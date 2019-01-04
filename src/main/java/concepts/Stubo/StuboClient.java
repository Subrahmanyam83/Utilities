package concepts.Stubo;


import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;


public class StuboClient {

    private final HttpClient client;

    public StuboClient(URL baseUrl) {
        client = getClientWithDefaultTimeout();
        HostConfiguration hostConfiguration = new HostConfiguration();
        hostConfiguration.setHost(baseUrl.getHost(), baseUrl.getPort(), baseUrl.getProtocol());
        client.setHostConfiguration(hostConfiguration);
    }

    public JSONObject getVersion() {
        return getResponse("get/version");
    }

    public JSONObject getScenarios() {
        return getResponse("get/scenarios");
    }

    private JSONObject getResponse(String relativePath) {
        GetMethod getMethod = new GetMethod(relativePath);
        String body = execute(getMethod);

        return new JSONObject(body);
    }

    private String execute(HttpMethod method) {
        try {
            method.setPath(String.format("/stubo/api/%s", method.getPath()));
            int i = client.executeMethod(method);
            return method.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject getStatus(String scenarioName) {
        return getResponse(String.format("get/status?scenario=%s", scenarioName));
    }

    public static HttpClient getClientWithDefaultTimeout() {
        return getClient(10000, 60000);
    }

    public static HttpClient getClient(int connectTimeout, int socketTimeout) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().
                getParams().setConnectionTimeout(connectTimeout);
        httpClient.getHttpConnectionManager().
                getParams().setSoTimeout(socketTimeout);

        return httpClient;
    }
}
