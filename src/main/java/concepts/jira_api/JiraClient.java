package concepts.jira_api;

import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import java.io.IOException;
import java.util.Base64;

public class JiraClient {

    private static final String GET_ISSSUE_END_POINT = "/rest/api/latest/issue/";
    private String jiraServer = "https://jira.baplc.com";
    private String jiraid = "BTATWO-1877";
    private String response;
    private Gson gson = new Gson();
    private int state;

    @Test
    public void testCaseOne() throws IOException {
        JiraTicket jiraTicket = getResponse();
        System.out.println("*******************************");
        System.out.println("*******************************");
        System.out.println("*******************************");
        System.out.println("*******************************");
        System.out.println("*******************************");
        System.out.println(jiraTicket.getAssignee());
    }

    private JiraTicket getResponse() {
        HttpClient httpClient = new HttpClient();
        String method = jiraServer + GET_ISSSUE_END_POINT + jiraid;
        GetMethod getMethod = new GetMethod(method);
        getMethod.addRequestHeader("Cookie", "OBBasicAuth=fromDialog");
        getMethod.addRequestHeader("Authorization", "Basic " + getBasicAuthentication());
        try {
            state = httpClient.executeMethod(getMethod);
            response = getMethod.getResponseBodyAsString();
            if (state == 200) {
                return gson.fromJson(response, JiraTicket.class);
            } else if (state == 401) {
                System.out.println(("The supplied credentials did not work, please confirm JIRA_USERNAME and JIRA_PASSWORD environment variables"));
            }
        } catch (Exception e) {
            //InputStream is = getMethod.getResponseBodyAsStream();
            //System.out.println(IOUtils.toString(is,StandardCharsets.UTF_8.name()));
        }
        return null;
    }

    private String getBasicAuthentication() {
        String username = System.getenv("JENKINS_USERNAME");
        String password = System.getenv("JENKINS_TOKEN");
        return new String(Base64.getEncoder().encode((username + ":" + password).getBytes()));
    }

}
