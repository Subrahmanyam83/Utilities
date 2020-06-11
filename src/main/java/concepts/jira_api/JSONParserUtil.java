package concepts.jira_api;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JSONParserUtil {

    @Test
    private void parseJSON() throws ParseException {
        String path = "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/java/concepts/jira_api/myjis.json";
        String stringToParse = getStringFromFileContent(path);

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(stringToParse);
        System.out.println(json);
    }

    private static String getStringFromFileContent(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}
