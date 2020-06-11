package concepts.WebServices.SOAP;


import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestProperty;
import com.eviware.soapui.settings.HttpSettings;
import com.eviware.soapui.support.SoapUIException;
import org.apache.xmlbeans.XmlException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/*
CURL COMMANDS TO USE GET AND POST
*/

//        POST CALL
//        curl -w "%{http_code}" -X POST -d "@request.json" -H "accept-encoding: gzip, deflate" -H "content-type:application/json" -H "user-agent:Apache-HttpClient/4.1.1 (java 1.5)" -H "ba_sec_sessionId:123456" -H \ "ba_client_applicationName:soapUi" 'http://caws07-prl.baplc.com/sse-sbkm/rs/v2/customers/baskets'
//
//        GET CALL
//        curl -w "\n %{http_code}\n" -X GET -H "ba_sec_sessionId:123456" -H "ba_client_applicationName:soapUi" 'https://caws08-prl.baplc.com/badotcomadapter-paa/rs/v1/flights/fares;outboundOrigin=LHR;outboundDestination=ATH;outboundDate=2020-01-13;adultCount=2;youthCount=0;childCount=0;infantCount=0;cabins=Economy;ticketFlexibility=RESTRICTED;maxPointsEarningFare=false;language=EN;includeCalendar=true'

public class TestSOAPService {


    String wsdlFilePath = System.getProperty("user.dir") + "/soap-ui-tests/test-data-generator-soapui-project.xml";
    String requiredPNRType = "AB_Booking";
    String suitNameValue = "Normal Booking+JBR";

    @Test
    public void createPNR() throws XmlException, IOException, SoapUIException, InterruptedException {
        WsdlProject project = new WsdlProject(wsdlFilePath);
        WsdlTestSuite testSuite = project.getTestSuiteAt(1);

        testSuite.setTimeout(300000);
        List<TestCase> testCases = testSuite.getTestCaseList();

        for (int i = 0; i < testCases.size(); i++) {
            WsdlTestCase testCase = testSuite.getTestCaseAt(i);

            if (testCase.getName().equals(requiredPNRType)) {
                PropertiesMap propertiesMap = new PropertiesMap();
                propertiesMap.put(HttpSettings.SOCKET_TIMEOUT, 5400000);

                WsdlTestCaseRunner tcRunner = testCase.run(propertiesMap, false);
                Map<String, TestProperty> testPropertyMap = tcRunner.getTestRunnable().getProperties();
                for (TestProperty tp : testPropertyMap.values()) {
                    if (tp.getName().equalsIgnoreCase("pnr")) {
                        System.out.println("PNR IS: " + tp.getValue());
                    }
                }
            }
        }
    }
}