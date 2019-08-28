package com.luxoft.ak47;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


@RestController
public class TradeEventController {
    @RequestMapping(value = "/tradeEvent/{trade_id}", produces = MediaType.TEXT_XML_VALUE)
    String tradeEvent(@PathVariable String trade_id) {
        String linia="";
        StringBuilder xmlReturnedAsString = new StringBuilder();
        try (BufferedReader inboundBuffRead = new BufferedReader(new FileReader("XML/" + trade_id + ".xml"))) {
            while ((linia = inboundBuffRead.readLine()) != null) {
                xmlReturnedAsString.append(linia);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return(xmlReturnedAsString.toString());
    }
}
