package concepts.JAVA;


import gherkin.formatter.JSONFormatter;
import gherkin.formatter.model.Feature;
import gherkin.util.FixJava;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.*;
import gherkin.parser.Parser;

public class FileUtils {

    String[] myDirectoryPath = {"/src/main/java/concepts/",
                                "/src/test/",
                                "/target"
    };

    @Test
    public void getFiles() {
        for (String filePath:myDirectoryPath) {
            File files = new File(System.getProperty("user.dir"),filePath);
            iterateOverDirectoriesToFindAFileOfPattern(files);
        }
    }

    private void iterateOverDirectoriesToFindAFileOfPattern(File myDirectoryPath) {

        File[] files = myDirectoryPath.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isFile()) {
                    System.out.println("File ");
                } else {
                    System.out.println("Directory");
                    iterateOverDirectoriesToFindAFileOfPattern(f);
                }
            }
        else {
            throw new RuntimeException("Files Path is wrong :" + files);
        }
    }

    public Feature[] getFeatures(String filepath) throws Exception {

        //Step One: Parse feature into JSON using Gherkin
        String featureText = FixJava.readReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"));
        StringBuilder json = new StringBuilder();
        JSONFormatter formatter = new JSONFormatter(json);
        Parser parser = new Parser(formatter);
        parser.parse(featureText, filepath, 0);
        formatter.done();
        formatter.close();

        JSONObject jsonObject = new JSONObject(json.substring(1,json.length()-1).trim());


        /*json = json.substring(json.indexOf("{"));

        json.setLength(json.length() - 1);
*/
        //Step Two: Convert the Features to plain old java object, and return an array of features
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json.substring(1,json.length()-1).trim(),Feature[].class);
        //return mapper.readValue(json.substring(json.indexOf("{")),Feature[].class);
    }

    @Test
    public void myTest() throws Exception {
        String path = System.getProperty("user.dir")+"/src/test/resources/features/myfeature.feature";
        getFeatures(path);
    }

}
