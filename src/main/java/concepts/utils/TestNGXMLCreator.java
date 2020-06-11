package concepts.utils;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by E002183 on 5/7/2016.
 */
public class TestNGXMLCreator {

    @Test
    public void mytest2(){
        System.out.println("Hiii");
    }

    @Test
    public void myTest() {

        String testngXMLFilePath = System.getProperty("user.dir")+"/test.xml";
        TestNG myTestNG = new TestNG();
        Map<String,String> testngParams = new HashMap<>();
        testngParams.put("platform","Android");
        testngParams.put("platformVersion","9");
        testngParams.put("testDevice","Samsung Galaxy S10");

        //Create an instance of XML Suite and assign a name for it.
        XmlSuite mySuite = new XmlSuite();
        mySuite.setName("BA-Mobile-Ops Test Automation Suite");
        mySuite.setVerbose(1);
        mySuite.setParallel(XmlSuite.ParallelMode.TESTS);
        mySuite.setConfigFailurePolicy(XmlSuite.FailurePolicy.CONTINUE);


        /*CREATE A TEST*/
        //Create an instance of XmlTest and assign a name for it.
        XmlTest myTest = new XmlTest(mySuite);
        myTest.setName("ANDROID_TEST");

        //Add any parameters that you want to set to the Test.
        myTest.setParameters(testngParams);


        //Create a list which can contain the classes that you want to run.
        List<org.testng.xml.XmlClass> myClasses = new ArrayList<XmlClass>();
        XmlClass xmlClass = new XmlClass("concepts.JAVA.FileUtils");
        myClasses.add(xmlClass);

        //Assign that to the XmlTest Object created earlier.
        myTest.setXmlClasses(myClasses);

        //Create a list of XmlTests and add the Xmltest you created earlier to it.
        List<XmlTest> myTests = new ArrayList<XmlTest>();
        myTests.add(myTest);






        //add the list of tests to your Suite.
        mySuite.setTests(myTests);

        //Add the suite to the list of suites.
        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
        mySuites.add(mySuite);

        //Set the list of Suites to the testNG object you created earlier.
        myTestNG.setXmlSuites(mySuites);
        mySuite.setFileName("testng.xml");
        mySuite.setThreadCount(3);

        //myTestNG.run();

        //Create physical XML file based on the virtual XML content
        for(XmlSuite suite : mySuites)
        {
            createXmlFile(suite);
        }
        System.out.println("File generated successfully.");
    }

    private void createXmlFile(XmlSuite mSuite)
    {
        FileWriter writer;
        try {
            writer = new FileWriter(new File("testng.xml"));
            writer.write(mSuite.toXml());
            writer.flush();
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}