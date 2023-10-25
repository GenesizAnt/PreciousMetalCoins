package ru.numismatist.PreciousMetalCoins.controllers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.numismatist.PreciousMetalCoins.dto.CoinCollectorValue;
import ru.numismatist.PreciousMetalCoins.dto.mapper.CoinMapper;
import ru.numismatist.PreciousMetalCoins.models.Metall;
import ru.numismatist.PreciousMetalCoins.services.CoinService;

import java.io.StringReader;
import java.util.Objects;

import static ru.numismatist.PreciousMetalCoins.util.Util.getXMLResponse;

@Controller
@RequestMapping("/collector_value")
public class CollectorValueController {

    private final RestTemplate restTemplate;
    private final CoinService coinService;
    private final CoinMapper coinMapper;

    @Autowired
    public CollectorValueController(CoinService coinService, CoinMapper coinMapper) {
        this.coinMapper = coinMapper;
        this.coinService = coinService;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getCollectorValueCoinById(@PathVariable("id") int id,
                                            @RequestParam(value = "day", required = false) String day,
                                            @RequestParam(value = "month", required = false) String month,
                                            @RequestParam(value = "year", required = false) String year) throws JAXBException {

        Metall metalPerDate = extractedPreciousMetalsPriceFromCB(day, month, year);
        CoinCollectorValue coinCollectorValue = coinMapper.toDtoCoinCollectorValue(coinService.getCoinByIndex(id));

        switch (coinCollectorValue.getPreciousMetalFromCatalogNumber()) {
            case GOLD -> coinCollectorValue.calculateCollectorValueRub(metalPerDate.getRecords().get(0).getSell());
            case SILVER -> coinCollectorValue.calculateCollectorValueRub(metalPerDate.getRecords().get(1).getSell());
            case PLATINUM -> coinCollectorValue.calculateCollectorValueRub(metalPerDate.getRecords().get(2).getSell());
            case PALLADIUM -> coinCollectorValue.calculateCollectorValueRub(metalPerDate.getRecords().get(3).getSell());
        }

        return getXMLResponse(coinCollectorValue);
    }

    private Metall extractedPreciousMetalsPriceFromCB(String day, String month, String year) throws JAXBException {
        String url = getPreciousMetalsPriceFromCB(day, month, year);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String responseBody = response.getBody();
        JAXBContext jaxbContext = JAXBContext.newInstance(Metall.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Metall) unmarshaller.unmarshal(new StringReader(Objects.requireNonNull(responseBody)));
    }

    private String getPreciousMetalsPriceFromCB(String day, String month, String year) {
        StringBuilder dateForCBFormat = new StringBuilder();
        dateForCBFormat.append(day).append("/").append(month).append("/").append(year);

        return "http://www.cbr.ru/scripts/xml_metall.asp?date_req1=" +
                dateForCBFormat + "&date_req2=" + dateForCBFormat;
    }
}
