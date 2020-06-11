package concepts.JSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import gherkin.deps.com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.lang.Exception;
import java.io.File;
import java.io.*;
import org.json.simple.parser.*;
import org.testng.annotations.Test;

public class MyJsonParserCopy {

    JSONArray jsonData;

    public MyJsonParserCopy(){}

    @Test
    public void createConsolidatedJSON( ) throws Exception{
        String rerunCombinedJsonFilePath= "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/BDD/cucumber-runner-maven-plugin/test-project/target/cucumber/rerun-threads/combined.json";
        File outputFolder = new File("/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/BDD/cucumber-runner-maven-plugin/test-project/target/cucumber");


        jsonData = (JSONArray) readJsonSimpleDemo(rerunCombinedJsonFilePath);
        int numberOfFeatureFiles = jsonData.size();

        for (int i=0;i<numberOfFeatureFiles;i++){
            JSONArray elements = (JSONArray) ((JSONObject) jsonData.get(i)).get("elements");
            String rerunfeatureFileName =  (String) ((JSONObject) jsonData.get(i)).get("uri");
            for(int j=0;j<elements.size();j++){
                long lineNumber=(long) (((JSONObject)elements.get(j)).get("line"));
                JSONObject element = (JSONObject)elements.get(j);
                replaceWithOriginal(element,rerunfeatureFileName,lineNumber,outputFolder);
            }
        }
    }

    public static Object readJsonSimpleDemo(String filename) throws Exception {
        Object jsonObject=null;
        if(new File(filename).exists()) {
            FileReader reader = new FileReader(filename);
            JSONParser jsonParser = new JSONParser();
            jsonObject = jsonParser.parse(reader);
            reader.close();
        }
        if(jsonObject.equals(null)){
            throw new Exception("File name: "+filename+" does not exists. Hence quitting.");
        }
        return jsonObject;
    }

    private void replaceWithOriginal(JSONObject replacingElement,String rerunfeaturefileName,long rerunlineNumber,File opFolder) throws Exception {
        String originalRunCombinedJsonFilePath = opFolder.getAbsolutePath()+"/consolidated-html/combined.json";
        JSONArray jData = (JSONArray) readJsonSimpleDemo(originalRunCombinedJsonFilePath);

        int numberOfFeatureFiles = jData.size();

        for (int i=0;i<numberOfFeatureFiles;i++){
            JSONArray elements = (JSONArray) ((JSONObject) jData.get(i)).get("elements");
            String featureFileName=  (String) ((JSONObject) jData.get(i)).get("uri");
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
        writeIntoJSONFile(jData,opFolder);
    }

    private void writeIntoJSONFile (JSONArray jsonArray, File outputFolder) throws IOException,ParseException{
        String combinedReportPath = outputFolder+"/consolidated-html";
        FileWriter fileWriter = new FileWriter(combinedReportPath+"/combined.json");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String formattedJSONString = formatJSONString(jsonArray.toString());
        bufferedWriter.write(formattedJSONString);
        bufferedWriter.close();
        fileWriter.close();
    }

    private String formatJSONString(String jsonString) throws ParseException {
        com.google.gson.Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}
