package concepts.XML;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import java.lang.RuntimeException;
import java.util.ArrayList;

public class XMPParserCopy {

    Document originalRootNode;
    Document rerunRootNode;

    public XMPParserCopy(){

    }

    public Document createXMLBuilder(String filepath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.parse(filepath);
        return document;
    }

    @Test
    public void myTest() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        String rPath = "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/BDD/cucumber-runner-maven-plugin/test-project/target/cucumber/rerun-threads/combined.xml";
        String oPath = "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/BDD/cucumber-runner-maven-plugin/test-project/target/cucumber/threads/combined.xml";
        String consolidatePath = "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/BDD/cucumber-runner-maven-plugin/test-project/target/cucumber/consolidated-html/combined.xml";
        createConsolidatedXML(rPath,oPath,consolidatePath);
    }

    public void createConsolidatedXML(String rerunFilePath, String originalRunFilePath, String consolidatedFilePath) throws ParserConfigurationException,IOException,SAXException,TransformerException {
        System.out.println("******************** 1");
        if (!(new File(rerunFilePath).exists())) {
            throw new RuntimeException("Rerun combined.xml does not exist at  location: "+rerunFilePath+". Exiting....");
        }
        rerunRootNode = createXMLBuilder(rerunFilePath);

        if (!(new File(originalRunFilePath).exists())) {
            throw new RuntimeException("Original combined.xml does not exist at  location: "+originalRunFilePath+". Exiting....");
        }
        originalRootNode = createXMLBuilder(originalRunFilePath);

        NodeList testcaseNode = rerunRootNode.getElementsByTagName("testcase");
        int numberOfTestCases = testcaseNode.getLength();

        System.out.println("******************** 2");

        for (int i = 0; i < numberOfTestCases; i++) {
            System.out.println("******************** 3");

            Element testCaseNode = (Element) testcaseNode.item(i);
            String className = testCaseNode.getAttributes().getNamedItem("classname").getNodeValue();
            String name = testCaseNode.getAttributes().getNamedItem("name").getNodeValue();
            Node copiedNode = originalRootNode.importNode(testCaseNode, true);

            replaceOriginalXML(consolidatedFilePath, copiedNode, className, name);
            System.out.println("******************** 4");

        }
    }

    public void replaceOriginalXML(String consolidatedXMLFilePath, Node nodeToBeReplaced,String cName, String name) throws IOException, SAXException, ParserConfigurationException, TransformerException {

        NodeList originalTestcaseNode = originalRootNode.getElementsByTagName("testcase");
        int numberOfOriginalTestCases= originalTestcaseNode.getLength();
        String status;

        System.out.println("******************** 5");


        for(int i =0;i<numberOfOriginalTestCases;i++){

            //Element originalTestCaseNode = (Element) originalTestcaseNode.item(i);

            System.out.println("******************** 6");


           // Element originalNode = (Element) originalTestcaseNode.item(i);
            String originalClassName = originalTestcaseNode.item(i).getAttributes().getNamedItem("classname").getNodeValue();
            String originalName = originalTestcaseNode.item(i).getAttributes().getNamedItem("name").getNodeValue();
            boolean isTestCaseFailed=false;

            NodeList testCaseNodeSChildNodes = originalTestcaseNode.item(i).getChildNodes();
            for (int j=0;j<testCaseNodeSChildNodes.getLength();j++) {
                if(testCaseNodeSChildNodes.item(j).getNodeName().equalsIgnoreCase("failure")){
                    isTestCaseFailed =true;
                }
            }

            //boolean isTestCaseFailed = (originalTestcaseNode.item(i).getOwnerDocument().getElementsByTagName("failure").getLength() )>0;
            //System.out.println("-------------------------------------------------"+originalTestcaseNode.getOwnerDocument().getElementsByTagName("failure").getLength());
            //System.out.println("-------------------------------------------------"+isTestCaseFailed);




            if (originalClassName.equals(cName) && originalName.equals(name) && isTestCaseFailed) {

                originalRootNode.getDocumentElement().removeChild(originalTestcaseNode.item(i));
                originalRootNode.getDocumentElement().appendChild(nodeToBeReplaced);
                break;

            }
        }
        writeIntoXMLFile(consolidatedXMLFilePath,originalRootNode);
    }

    public void writeIntoXMLFile(String filepath, Document doc) throws TransformerException {
        System.out.println("******************** 7" +
                "");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
    }
}
