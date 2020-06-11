package concepts.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class MyJsonParser {

    JSONArray jsonData;

//    String rerunJsonFilePath = System.getProperty("user.dir")+"/src/main/java/concepts/JSON/json/jenkins-rerun.json";
//    String rerunReprtPath="/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/java/concepts/JSON/json/rerunReport";
//
//    String originalJsonFilePath = System.getProperty("user.dir") + "/src/main/java/concepts/JSON/json/jenkins.json";
//    List <String> originalJsonFilePathsList  = Arrays.asList(originalJsonFilePath);
//    String originalReprtPath="/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/java/concepts/JSON/json/originalReport";

    String rerunJsonFilePath = "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/BDD/cucumber-runner-maven-plugin/test-project/target/cucumber/rerun-threads/combined.json";
    String rerunReprtPath="/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/java/concepts/JSON/json/rerunReport";

    String originalJsonFilePath = "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/BDD/cucumber-runner-maven-plugin/test-project/target/cucumber/consolidated-html/combined.json";
    List <String> originalJsonFilePathsList  = Arrays.asList(originalJsonFilePath);
    String originalReprtPath="/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/java/concepts/JSON/json/originalReport";

    @Test
    public void myTest() throws Exception {

        jsonData = (JSONArray) readJsonSimpleDemo(rerunJsonFilePath);
        //generateReportForJsonFiles(new File(originalReprtPath),originalJsonFilePathsList);

        int numberOfFeatureFiles = jsonData.size();
        HashMap mymap = new HashMap<String, JSONArray>();

        for (int i=0;i<numberOfFeatureFiles;i++){
            JSONArray elements = (JSONArray) ((JSONObject) jsonData.get(i)).get("elements");
            String rerunfeatureFileName=  (String) ((JSONObject) jsonData.get(i)).get("uri");
            mymap.put(rerunfeatureFileName,elements);
            for(int j=0;j<elements.size();j++){
                long lineNumber=(long) (((JSONObject)elements.get(j)).get("line"));
                JSONObject element = (JSONObject)elements.get(j);
                replaceWithOriginal(element,rerunfeatureFileName,lineNumber);

                    System.out.println(rerunfeatureFileName+":"+((JSONObject)elements.get(j)).get("line"));
                    System.out.println((elements.get(j)));
                }
        }

        //generateReportForJsonFiles(new File(rerunReprtPath),originalJsonFilePathsList);
    }

    private void replaceWithOriginal(JSONObject replacingElement,String rerunfeaturefileName,long rerunlineNumber) throws Exception {

        JSONArray jsonData = (JSONArray) readJsonSimpleDemo(originalJsonFilePath);

        int numberOfFeatureFiles = jsonData.size();
        HashMap mymap = new HashMap<String, JSONArray>();

        for (int i=0;i<numberOfFeatureFiles;i++){
            JSONArray elements = (JSONArray) ((JSONObject) jsonData.get(i)).get("elements");
            String featureFileName=  (String) ((JSONObject) jsonData.get(i)).get("uri");
            mymap.put(featureFileName,elements);
            if(featureFileName.equalsIgnoreCase(rerunfeaturefileName)) {
                for (int j = 0; j < elements.size(); j++) {
                        long lineNumber = (long) (((JSONObject) elements.get(j)).get("line"));
                        if(lineNumber==rerunlineNumber){
                            elements.remove(j);
                            elements.add(replacingElement);


                        }
                }
            }
        }
        FileWriter fileWriter = new FileWriter(originalJsonFilePath);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String formattedJSONString = formatJSONString(jsonData.toString());
        bufferedWriter.append(formattedJSONString);
        bufferedWriter.close();
        fileWriter.close();
    }

    private String formatJSONString(String jsonString) throws ParseException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

    public static Object readJsonSimpleDemo(String filename) throws Exception {
        Object jsonObject=null;
        if(new File(filename).exists()) {
            FileReader reader = new FileReader(filename);
            JSONParser jsonParser = new JSONParser();
            jsonObject = jsonParser.parse(reader);
            reader.close();
        }
        return jsonObject;
    }



    private void generateReportForJsonFiles(File reportOutputDirectory,
                                            List<String> jsonFiles) {
        String jenkinsBasePath = "";
        String buildNumber = "1";
        String projectName = "sample-project";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setParallelTesting(false);
        configuration.setJenkinsBasePath(jenkinsBasePath);
        configuration.setRunWithJenkins(false);
        configuration.setBuildNumber(buildNumber);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();

        File threadedReportFile = new File("", "reports/");

    }

    private File getThreadFolder()  {


        File threadFolder = new File("cucumber/rerun-threads");

        return threadFolder;
    }

    @Test
    public void test1() throws IOException {
        File originalCombinedJSONFile = new File("/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/BDD/cucumber-runner-maven-plugin/test-project/target/cucumber/combined-html/cucumber-html-reports/overview-failures.html");
        File consolidatedReportOutputDirectory = new File("/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/BDD/cucumber-runner-maven-plugin/test-project/target/cucumber/subu");
        FileUtils.copyFile(originalCombinedJSONFile, new File(consolidatedReportOutputDirectory + "/abc.html"));

    }
}
