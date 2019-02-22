package task2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.htmlcleaner.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ParserHtml {

    private static final String EXPRESSION = "/bookstore/book[year>2004][title[@lang='en']]/@category";


    private static String downloadPage(URI uri) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet getReq = new HttpGet(uri);
        HttpResponse response = httpClient.execute(getReq);
        ResponseHandler<String> handler = new BasicResponseHandler();
        return handler.handleResponse(response);
    }

    public static void parseAvitoPages(String avitoPage) {
        HtmlCleaner cleaner = new HtmlCleaner();

        String page = "";
        try {
            URIBuilder uriBuilder = new URIBuilder(avitoPage);
            page = downloadPage(uriBuilder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        TagNode node = cleaner.clean(page);
        try {
            Document doc = new DomSerializer(
                    new CleanerProperties()).createDOM(node);

            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xpath.evaluate("//div[@class='item item_table clearfix js-catalog-item-enum  item-with-contact js-item-extended']",
                    doc, XPathConstants.NODESET);

            System.out.println(nodeList.getLength());
            for( int i = 0 ; i < nodeList.getLength() ; i++ ){
                System.out.println("name :" + ((TagNode) node.evaluateXPath("//span[@itemprop='name']")[i]).getText());
                System.out.println("price : " + ((TagNode) node.evaluateXPath("//span[@itemprop='price']")[i]).getText());
                System.out.println("link : https://avito.ru" + (node.evaluateXPath("//a[@class='item-description-title-link']/@href")[i]));
                System.out.println("address : " + ((TagNode) node.evaluateXPath("//p[@class='address']")[i]).getText() + "\n\n\n");

            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        String nextPage = null;
        try {
            nextPage = (String) node.evaluateXPath("//a[@class='pagination-page js-pagination-next']/@href")[0];
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        if (nextPage!=null){
           parseAvitoPages("https://avito.ru" + nextPage);
        }
    }


    public static void main(String[] args) {
        ParserHtml.parseAvitoPages("https://www.avito.ru/kazan/kommercheskaya_nedvizhimost?s_trg=11");
    }

}
