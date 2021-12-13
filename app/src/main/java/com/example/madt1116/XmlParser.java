package com.example.madt1116;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlParser {
    public static String getRateFromECB(InputStream stream, String currencyCode) throws IOException {
        String result = "";
        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(stream);

            NodeList rateNodes = doc.getElementsByTagName(Constants.ROOT_NODE);
            for (int i = 0; i < rateNodes.getLength(); ++i) {
                Element currencyNode = (Element) rateNodes.item(i);
                String currencyName = currencyNode.getElementsByTagName(Constants.CURRENCY_NODE).item(0).getTextContent();
                String currencyRate = currencyNode.getElementsByTagName(Constants.FOREX_NODE).item(0).getTextContent();
                result = result + String.format("%s %s\n", currencyName, currencyRate);

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return result;
    }
}
