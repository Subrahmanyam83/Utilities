package concepts.Stubo;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Stubo {


    String stuboInstanceURL = "http://stubo-ba-com39.baplc.com:8001";
    StuboClient stuboClient = new StuboClient(new URL(stuboInstanceURL));
    private static final String USERNAME = "testblsuser";
    private static final String PASSWORD = "testblsuser";
    String baseURLS[] = {
            "http://capdev408.uk.ba.com:8001/testbls/StuboIntegratorSession.jsp",
            "http://capdev408.uk.ba.com:7001/captools/StuboIntegratorSession.jsp"
    };
    String blsurl = "http://stubo-ba-com31.baplc.com:8001/api/v2/scenarios";
    String wcurl = "http://capdev408.uk.ba.com:7001/captools/StuboIntegratorSession.jsp"; // for this it ends
    HttpClient client;

    public Stubo() throws MalformedURLException {
    }

    @BeforeClass
    public void testSetup() {
        client = new HttpClient();
        client.getState().setCredentials(
                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                new UsernamePasswordCredentials(USERNAME, PASSWORD));
    }
    @Test
    public void abc(){
        System.out.println(stuboClient.getVersion());

        System.out.println("-------");
        //System.out.println(stuboClient.getScenarios().getJSONObject("data").getJSONArray("scenarios"));
        JSONArray json = stuboClient.getScenarios().getJSONObject("data").getJSONArray("scenarios");
        //JSONArray array = new JSONArray(json);
        ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < json.length();i++) {
            list.add(json.get(i++).toString().replace(stuboInstanceURL.split(":")[1].replace("//","")+":",""));
        }

        System.out.println(list);
    }

    @Test()
    public void endSessionBLS() throws IOException {

        String blsurl1 = "http://stubo-ba-com39.baplc.com:8001/api/v2/scenarios/objects/324767546/action";
        PostMethod postMethod = new PostMethod(blsurl1);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("end","null"));
        params.add(new BasicNameValuePair("session","SelectAddNewPassengerHhaMob-bacom_executiveClubSellingMobileWeb"));
        postMethod.setRequestBody("{\n" +
                "  \"end\": null,\n" +
                "  \"session\": \"SelectAddNewPassengerHhaMob-bacom_executiveClubSellingMobileWeb\"\n" +
                "}");
        int state = client.executeMethod(postMethod);
        System.out.println(state);
    }

    @Test
    public void getStuboMaticServerVersion() throws IOException {
        GetMethod getMethod = new GetMethod("http://stubo-ba-com21.baplc.com:8001/stubo/api/get/version");
        System.out.println(client.executeMethod(getMethod));
        String response= StringUtils.toEncodedString(getMethod.getResponseBody(), Charset.defaultCharset());
        System.out.println(response);
    }

    /*GET CALL: http://stubo-ba-com39.baplc.com:8001/stubo/api/get/scenarios*/
    @Test
    public void getScenarios() throws IOException {
        GetMethod getMethod = new GetMethod("http://stubo-ba-com39.baplc.com:8001/stubo/api/get/scenarios");
        System.out.println(client.executeMethod(getMethod));
        String response= StringUtils.toEncodedString(getMethod.getResponseBody(), Charset.defaultCharset());
        JSONObject jsonObject = new JSONObject(response);
        System.out.println();

        System.out.println();
        System.out.println();

        JSONArray jsonArray = (JSONArray) jsonObject.getJSONObject("data").get("scenarios");
        System.out.println(jsonArray.length());
       for (int i =0;i<jsonArray.length();i++){
           System.out.println(jsonArray.get(i));
           String url ="http://stubo-support5.baplc.com:8001/stubo/api/get/status?scenario=/api/v2/scenarios/objects/"+jsonArray.get(i);
           System.out.println( url);
           GetMethod getScenarioStatus = new GetMethod(url);
           System.out.println();
           System.out.println();
           System.out.println("---------- STATUS: "+ client.executeMethod(getScenarioStatus)+"------"+StringUtils.toEncodedString(getScenarioStatus.getResponseBody(), Charset.defaultCharset()));
       }
    }

    @Test
    public void getScenarioAndSessionDetail() throws IOException {
        GetMethod getMethod = new GetMethod("http://stubo-ba-com39.baplc.com:8001/api/v2/scenarios/detail");
        client.executeMethod(getMethod);
        String response = StringUtils.toEncodedString(getMethod.getResponseBody(),Charset.defaultCharset());
        JSONArray jsonArray = (JSONArray) new JSONObject(response).get("data");

        for(int i=0;i<jsonArray.length();i++){
           String name=  (String)((JSONObject)jsonArray.get(i)).get("name");
           JSONArray session = (JSONArray) ((JSONObject)jsonArray.get(i)).get("sessions");
           System.out.println(name);

           for(int j=0;j<session.length();j++){
               System.out.println(session.getJSONObject(j).get("name"));
               System.out.println(session.getJSONObject(j).get("status"));
           }
           System.out.println();
        }
        System.out.println(jsonArray.length());
    }

    @Test
    public void getSessionIDStatus() throws IOException {
        System.out.println(getSessionIDStatus("317168113"));
    }


//    316661141, 312185483
    public String getSessionIDStatus(String sessionID) throws IOException {
        String status = "No Status";
        GetMethod getMethod = new GetMethod(String.format("%s/api/v2/scenarios/detail",stuboInstanceURL));
        client.executeMethod(getMethod);
        String response = StringUtils.toEncodedString(getMethod.getResponseBody(),Charset.defaultCharset());
        JSONArray jsonArray = (JSONArray) new JSONObject(response).get("data");

        for(int i=0;i<jsonArray.length();i++){
            String name=  (String)((JSONObject)jsonArray.get(i)).get("name");
            JSONArray session = (JSONArray) ((JSONObject)jsonArray.get(i)).get("sessions");
            System.out.println(name);

            for(int j=0;j<session.length();j++){
               if(session.getJSONObject(j).get("name").equals(sessionID)){
                   status = session.getJSONObject(j).get("status").toString();
               }
            }
        }
        return status;
    }
}
