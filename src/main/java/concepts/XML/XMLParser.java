package concepts.XML;

import org.testng.annotations.Test;
import org.w3c.dom.CharacterData;
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
import java.util.ArrayList;


public class XMLParser {

    Document originalRootNode;
    ArrayList replacedTestCaseList = new ArrayList();

    private Document createXMLBuilder(String filepath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.parse(filepath);
        return document;
    }

    @Test
    private void xmlParsingTest() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        String rerunFilePath = "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/java/concepts/XML/xmls/re-run.xml";
        Document rerunRootNode = createXMLBuilder(rerunFilePath);

        String originalRunFilePath = "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/java/concepts/XML/xmls/original-run.xml";
        originalRootNode = createXMLBuilder(originalRunFilePath);

        NodeList testcaseNode = rerunRootNode.getElementsByTagName("testcase");
        int numberOfTestCases = testcaseNode.getLength();

        for (int i = 0; i < numberOfTestCases; i++) {
            Element testCaseNode = (Element) testcaseNode.item(i);
            String className = testCaseNode.getAttributes().getNamedItem("classname").getNodeValue();
            String name = testCaseNode.getAttributes().getNamedItem("name").getNodeValue();
            Node copiedNode = originalRootNode.importNode(testCaseNode, true);
            replaceOriginalXML(copiedNode, className, name);
            NodeList childNodes = testCaseNode.getChildNodes();

            replacedTestCaseList.add(className+"_"+name);
            if(replacedTestCaseList.contains(className+"_"+name)){
                System.out.println("Hellooooo");
            }


            for (int j = 0; j < childNodes.getLength(); j++) {

                System.out.println(childNodes.item(j).getNodeName());






//                if (childNodes.item(j).getFirstChild() instanceof CharacterData) {
//                    CharacterData child = (CharacterData) childNodes.item(j).getFirstChild();
//                    String data = child.getData();
//                    System.out.println(data.toString());
//
//                }




//                if (childNodes.item(j).getNodeName().equals("system-out") || childNodes.item(j).getNodeName().equals("failure")) {
//                    System.out.println("*******");
//                    System.out.println(childNodes.item(j).getTextContent());
//                    for (int x = 0; x < childNodes.item(j).getChildNodes().getLength(); x++) {
////                        if(childNodes.item(j).getChildNodes().item(x).getNodeName().equals("#cdata-section")){
////                            //System.out.println(childNodes.item(j).getChildNodes().item(x).getNodeValue().);
////
////                        }
////                    }
//
//                        System.out.println("*******");
//
//                    }
//
//                }






            }
        }
    }


    private void replaceOriginalXML(Node nodeToBeReplaced,String cName, String name) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String newRunFilePath = "/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/java/concepts/XML/xmls/new.xml";

        NodeList originalTestcaseNode = originalRootNode.getElementsByTagName("testcase");
        int numberOfOriginalTestCases= originalTestcaseNode.getLength();

        for(int i =0;i<numberOfOriginalTestCases;i++){
            Node testCaseNode = originalTestcaseNode.item(i);
            String originalClassName= testCaseNode.getAttributes().getNamedItem("classname").getNodeValue();
            String originalName= testCaseNode.getAttributes().getNamedItem("name").getNodeValue();
            if(originalClassName.equals(cName) && originalName.equals(name)){

                boolean isTestCaseFailed = testCaseNode.getOwnerDocument().getElementsByTagName("failure").getLength()>0;

                originalRootNode.getDocumentElement().removeChild(testCaseNode);
                originalRootNode.getDocumentElement().appendChild(nodeToBeReplaced);

                System.out.println("BOLLENA"+isTestCaseFailed);




                //originalRootNode.getDocumentElement().replaceChild(nodeToBeReplaced,testCaseNode);
            }
        }
        writeIntoXMLFile(newRunFilePath,originalRootNode);
    }

    private void writeIntoXMLFile(String filepath, Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
    }

}
