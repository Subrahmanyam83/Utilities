package concepts.JAVA;

import com.github.opendevl.JFlat;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JavaToCSV {

    @Test
    private void test() {
        DateFormat dateFormat = new SimpleDateFormat("d-MMM-YYYY HH:mm");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }


}
