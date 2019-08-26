package com.luxoft.ak47;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@RestController
public class TradeEventController {
    @RequestMapping(value = "/tradeEvent/{trade_id}", produces = MediaType.TEXT_XML_VALUE)
    String tradeEvent(@PathVariable String trade_id) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        String xmlReturnedAsString="";
        File inboundXMLfile = new File("XML/" + trade_id + ".xml");
        FileReader inboundFileReader = new FileReader(inboundXMLfile);

        return(xmlReturnedAsString);
    }
}
