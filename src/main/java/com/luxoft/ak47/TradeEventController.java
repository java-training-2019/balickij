package com.luxoft.ak47;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TradeEventController {
    @RequestMapping(value = "/tradeEvent/{id}", produces = MediaType.TEXT_XML_VALUE)
    String tradeEvent(@PathVariable String id) {
        String xmlReturned;
        String tradeLocation = null;
        if (id.contains("OBS")) {
            tradeLocation = "LOC";
        }
        else {
            tradeLocation = null;
        }
        xmlReturned="<tradeEvent><id>" + id + "</id><version>0</version><tradeLocation>" + tradeLocation + "</tradeLocation></tradeEvent>";
        return(xmlReturned);
    }
}
