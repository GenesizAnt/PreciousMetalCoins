package ru.numismatist.PreciousMetalCoins.controllers;

import jakarta.xml.bind.JAXBException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;
import ru.numismatist.PreciousMetalCoins.models.Coin;
import ru.numismatist.PreciousMetalCoins.services.CoinService;
import ru.numismatist.PreciousMetalCoins.util.XmlSerializer;

@Controller
@RequestMapping("/coins")
public class GetCoinController {

    private final CoinService coinService;

    public GetCoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getCoin(@PathVariable int id) {
        System.out.println(coinService.getCoinByIndex(id).getCost());
    }

    @GetMapping(value = "/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getCoinXml(@PathVariable int id) {
        Coin coinByIndex = coinService.getCoinByIndex(id);
        CoinXml coinXml = new CoinXml(coinByIndex.getName(),coinByIndex.getDenomination(),
                coinByIndex.getMetal(), coinByIndex.getWeight(), coinByIndex.getCatalogNumber(),
                coinByIndex.getCost());
        try {
            String s = XmlSerializer.serializeObjectToXml(coinXml);
            return s;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
