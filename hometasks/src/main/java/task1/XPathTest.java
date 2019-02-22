package task1;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XPathTest {
    private static final String XML_URI   = "https://www.w3schools.com/xml/books.xml";
    private static final String EXPRESSION = "/bookstore/book[year>2004][title[@lang='en']]/@category";

    private Document xmlDoc;
    private XPathExpression expr;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            XPathTest xPathTest = new XPathTest();
            xPathTest.loadDocumetFromUrl(XML_URI);
            xPathTest.loadExpression(EXPRESSION);
//            xPathTest.nodeSetProccesing();
            xPathTest.processHtml();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void loadDocumetFromUrl(String url) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new Exception("Can't crate DocumentBuilder");
        }

        // Load XML document from WWW
        try {
            xmlDoc = builder.parse(XML_URI);
        } catch (IOException ex) {
            throw new Exception("Can't get XML by URL " + XML_URI);
        } catch (SAXException ex) {
            throw new Exception("Can't read downloaded XML.");
        }

    }

    public void loadExpression(String expression) throws Exception {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        try {
            expr = xpath.compile(EXPRESSION);
        } catch (XPathExpressionException ex) {
              throw new Exception("Can't parse xPath expression " + EXPRESSION);
        }
    }

    public String nodeSetProccesing() throws Exception{

        NodeList nodeList;
        try {
            nodeList = (NodeList) expr.evaluate(xmlDoc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            throw new Exception("Can't evaluate exression");
        }

        StringBuilder builder = new StringBuilder();
        System.out.println(nodeList.getLength());
        for( int i = 0 ; i < nodeList.getLength() ; i++ ){


            switch (nodeList.item(i).getNodeType()){
                case Node.ATTRIBUTE_NODE:
                    builder.append("attribute node "+ nodeList.item(i).getTextContent());
                    break;
                case Node.TEXT_NODE:
                    builder.append("text node "+ nodeList.item(i).getTextContent());
                    break;
                case Node.ELEMENT_NODE:
                    builder.append("node element "+ nodeList.item(i).getTextContent());
                    break;
            }
//            result.append(nodeList.item(i).getTextContent());
        }
        return builder.toString();
//        return result.toString();
    }

    public void processHtml(){
        // create an instance of HtmlCleaner
        HtmlCleaner cleaner = new HtmlCleaner();


        TagNode node = cleaner.clean("https://www.re-store.ru/apple-ipad/ipad/");

// optionally find parts of the DOM or modify some nodes
        TagNode[] myNodes = node.getAllElements(true);
        for (int i = 0; i<myNodes.length ; i++){
            TagNode[] myNodes1 = myNodes[i].getAllElements(true);
            System.out.println(myNodes[i].getText());
            for (int j = 0; i<myNodes1.length ; i++){
                System.out.println(myNodes1[j].getText());
            }
        }
//
//        Object[] myNodes = node.evaluateXPath(xPathExpression);
//// and/or
//        aNode.removeFromTree();
//// and/or
//        aNode.addAttribute(attName, attValue);
//// and/or
//        aNode.removeAttribute(attName, attValue);
//// and/or
//        cleaner.setInnerHtml(aNode, htmlContent);
    }

}