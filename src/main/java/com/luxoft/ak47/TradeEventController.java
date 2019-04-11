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
        String tradeLocationXml;
        Random Randomizer = new Random();
        if (id.contains("OBS_")) {
            List<String> tradeLocations = Arrays.asList("HKG","SGP","POL","USA","AUS");
            tradeLocation = tradeLocations.get(Randomizer.nextInt(tradeLocations.size()));
            tradeLocationXml = "<tradeLocation>" + tradeLocation + "</tradeLocation>";
        }
        else {
            tradeLocationXml = "";
        }
        List<String> currList = Arrays.asList("EUR","SGD","USD","AUD","PLN");
        String curr = currList.get(Randomizer.nextInt(currList.size()));
        xmlReturned="<tradeEvent>" +
                "<id>" + id + "</id>" +
                "<version>0</version>" +
                 tradeLocationXml +
                "<currency>" + curr + "</currency>" +
                "</tradeEvent>";
        return(xmlReturned);
    }
}
