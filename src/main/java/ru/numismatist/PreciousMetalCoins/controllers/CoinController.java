package ru.numismatist.PreciousMetalCoins.controllers;

import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;
import ru.numismatist.PreciousMetalCoins.dto.mapper.CoinMapper;
import ru.numismatist.PreciousMetalCoins.models.Coin;
import ru.numismatist.PreciousMetalCoins.services.CoinService;

import java.util.List;

import static ru.numismatist.PreciousMetalCoins.util.Util.getXMLResponse;
import static ru.numismatist.PreciousMetalCoins.util.XmlSerializer.convertXMLToCoin;

@Controller
@RequestMapping("/coins")
public class CoinController {

    private final CoinService coinService;
    private final CoinMapper coinMapper;

    @Autowired
    public CoinController(CoinService coinService, CoinMapper coinMapper) {
        this.coinService = coinService;
        this.coinMapper = coinMapper;
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

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public void addCoin(@RequestBody CoinXml coinXml) {
        Coin newCoin = coinMapper.toCoin(coinXml);
        coinService.addNewCoin(newCoin);
    }

    @PostMapping("/add_from_file")
    @ResponseBody
    public void addCoinFromFile() {
        Coin newCoin = convertXMLToCoin();
        coinService.addNewCoin(newCoin);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteCoin(@PathVariable("id") int id) {
        coinService.deleteCoinById(id);
    }

    @PatchMapping(value = "/patch/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public void pathCoin(@RequestBody CoinXml coinXml, @PathVariable("id") int id) {
        Coin patchCoin = coinMapper.toCoin(coinXml);
        coinService.updateCoin(id, patchCoin);
    }

    @GetMapping(value = "/section_denomination_metal/{number}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public StringBuilder getCoinsByCatalogNumber(@PathVariable("number") String number) throws JAXBException {
        List<CoinXml> coinXmlList = coinMapper.toDtoListXML(coinService.numberSectionDenominationMetal(number));
        StringBuilder stringBuilder = new StringBuilder();
        for (CoinXml coinXml : coinXmlList) {
            stringBuilder.append(getXMLResponse(coinXml));
        }
        return stringBuilder;
    }
}















