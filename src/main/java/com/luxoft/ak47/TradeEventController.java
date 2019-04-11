package com.luxoft.ak47;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@RestController
public class TradeEventController {
    @RequestMapping(value = "/tradeEvent/{id}", produces = MediaType.TEXT_XML_VALUE)
    String tradeEvent(@PathVariable String id) {
        String xmlReturned;
        String tradeLocation;
        if (id.contains("OBS")) {
            tradeLocation = "LOC";
        }
        else {
            tradeLocation = null;
        }
        List<String> currList = Arrays.asList("EUR","SGD","USD","AUD","PLN");
        Random currRandomizer = new Random();
        String curr = currList.get(currRandomizer.nextInt(currList.size()));
        xmlReturned="<tradeEvent>" +
                "<id>" + id + "</id>" +
                "<version>0</version>" +
                "<tradeLocation>" + tradeLocation + "</tradeLocation>" +
                "<currency>" + "qqqq" + "</currency>" +
                "</tradeEvent>";
        return(xmlReturned);
    }
}
