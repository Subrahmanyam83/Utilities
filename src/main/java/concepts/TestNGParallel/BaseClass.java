package concepts.TestNGParallel;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass {

    public String getTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("hh-mm-ss");
        String strDate= formatter.format(date);
        return (strDate);
    }
}
