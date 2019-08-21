package com.luxoft.ak47;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


@RestController
public class TradeEventController {
    @RequestMapping(value = "/tradeEvent/{trade_id}", produces = MediaType.TEXT_XML_VALUE)
    String tradeEvent(@PathVariable String trade_id) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        String xmlReturnedAsString="";
        File inboundXMLfile = new File("XML/" + trade_id + ".xml");
        DocumentBuilderFactory docBFactory = DocumentBuilderFactory.newInstance();
        // konfiguracja factory - ignoruj whitespaces i komentarze
        docBFactory.setIgnoringElementContentWhitespace(true);
        docBFactory.setIgnoringComments(true);
        DocumentBuilder docBuilder = docBFactory.newDocumentBuilder();
        Document inboundXML = docBuilder.parse(inboundXMLfile);

        Element rootElement = inboundXML.getDocumentElement();
        NodeList childNodesOfRootElement = rootElement.getChildNodes();
        System.out.println("childNodesOfRootElement Length: " + childNodesOfRootElement.getLength() + " (incl. whitespace nodes)");
        for (int i=0; i<childNodesOfRootElement.getLength(); i++) {
            System.out.println(childNodesOfRootElement.item(i).getNodeName() + " : " + childNodesOfRootElement.item(i).getTextContent());
        }

        // transformacja dokumentu XML DOM na string
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        // transformacja: StringWriter
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(inboundXML);
        transformer.transform(source, result);
        xmlReturnedAsString = sw.toString();
        return(xmlReturnedAsString);
    }
}
