package ru.numismatist.PreciousMetalCoins.controllers;

import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;
import ru.numismatist.PreciousMetalCoins.dto.mapper.CoinMapper;
import ru.numismatist.PreciousMetalCoins.services.CoinService;

import java.util.List;

import static ru.numismatist.PreciousMetalCoins.util.Util.getXMLResponse;

@Controller
@RequestMapping("/coins")
public class GetCoinController {

    private final CoinService coinService;
    private final CoinMapper coinMapper;

    @Autowired
    public GetCoinController(CoinService coinService, CoinMapper coinMapper) {
        this.coinService = coinService;
        this.coinMapper = coinMapper;
    }

    @GetMapping("/test/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getCoin(@PathVariable int id) {
        System.out.println(coinService.getCoinByIndex(id).getCost());
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getCoinXml(@PathVariable int id) throws JAXBException {
        CoinXml coinXml = coinMapper.toDtoXML(coinService.getCoinByIndex(id));
        return getXMLResponse(coinXml);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public StringBuilder getCoinsAllXml() throws JAXBException {
        List<CoinXml> coinXmlList = coinMapper.toDtoListXML(coinService.findAllCoin());
        StringBuilder stringBuilder = new StringBuilder();
        for (CoinXml coinXml : coinXmlList) {
            stringBuilder.append(getXMLResponse(coinXml));
        }
        return stringBuilder;
    }

    @PostMapping("/add")
    public void addCoin() {

    }
}















