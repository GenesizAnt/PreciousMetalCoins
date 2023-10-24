package ru.numismatist.PreciousMetalCoins.controllers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.numismatist.PreciousMetalCoins.models.Metall;

import java.io.StringReader;

@Controller
@RequestMapping("/collector_value")
public class CollectorValueController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getCollectorValueCoinById(@PathVariable("id") String id,
                                            @RequestParam(value = "day", required = false) String day,
                                            @RequestParam(value = "month", required = false) String month,
                                            @RequestParam(value = "year", required = false) String year) throws JAXBException {
        String url = dataFormat(day, month, year);
//        String url = "http://www.cbr.ru/scripts/xml_metall.asp?date_req1=24/10/2023&date_req2=24/10/2023";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        System.out.printf("");

        String body = response.getBody();
        JAXBContext jaxbContext = JAXBContext.newInstance(Metall.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Metall metall = (Metall) unmarshaller.unmarshal(new StringReader(body));


        return "getXMLResponse(coinXml)";
    }

    private String dataFormat(String day, String month, String year) {
        StringBuilder date = new StringBuilder();
        date.append(day).append("/").append(month).append("/").append(year);

        StringBuilder url = new StringBuilder();
        url.append("http://www.cbr.ru/scripts/xml_metall.asp?date_req1=")
                .append(date).append("&date_req2=").append(date);
        return String.valueOf(url);
    }
}
