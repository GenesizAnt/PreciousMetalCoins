package ru.numismatist.PreciousMetalCoins.controllers;

import jakarta.xml.bind.JAXBException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.numismatist.PreciousMetalCoins.dto.CoinToXml;
import ru.numismatist.PreciousMetalCoins.dto.mapper.CoinMapper;
import ru.numismatist.PreciousMetalCoins.models.Coin;
import ru.numismatist.PreciousMetalCoins.rabbitmq.RabbitMqListener;
import ru.numismatist.PreciousMetalCoins.services.CoinService;

import java.util.List;
import java.util.Objects;

import static ru.numismatist.PreciousMetalCoins.util.Util.getXMLResponse;
import static ru.numismatist.PreciousMetalCoins.util.XmlSerializer.convertXMLToCoinFromFile;

@Controller
@RequestMapping("/coins")
public class CoinController {

    private final CoinService coinService;
    private final CoinMapper coinMapper;
    private final RabbitTemplate template;
    private final RabbitMqListener rabbitMqListener;

    @Autowired
    public CoinController(CoinService coinService, CoinMapper coinMapper, RabbitTemplate template, RabbitMqListener rabbitMqListener) {
        this.coinService = coinService;
        this.coinMapper = coinMapper;
        this.template = template;
        this.rabbitMqListener = rabbitMqListener;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getCoinXml(@PathVariable int id) throws JAXBException {
        CoinToXml coinToXml = coinMapper.toDtoXML(coinService.getCoinByIndex(id));
        return getXMLResponse(coinToXml);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public StringBuilder getCoinsAllXml() throws JAXBException {
        List<CoinToXml> coinToXmlList = coinMapper.toDtoListXML(coinService.findAllCoin());
        StringBuilder stringBuilder = new StringBuilder();
        for (CoinToXml coinToXml : coinToXmlList) {
            stringBuilder.append(getXMLResponse(coinToXml));
        }
        return stringBuilder;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public void addCoin(@RequestBody CoinToXml coinToXml) {
        Coin newCoin = coinMapper.toCoin(coinToXml);
        coinService.addNewCoin(newCoin);
    }

    @PostMapping("/add_from_file")
    @ResponseBody
    public void addCoinFromFile() {
        Coin newCoin = convertXMLToCoinFromFile();
        coinService.addNewCoin(newCoin);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteCoin(@PathVariable("id") int id) {
        coinService.deleteCoinById(id);
    }

    @PatchMapping(value = "/patch/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public void pathCoin(@RequestBody CoinToXml coinToXml, @PathVariable("id") int id) {
        Coin patchCoin = coinMapper.toCoin(coinToXml);
        coinService.updateCoin(id, patchCoin);
    }

    @GetMapping(value = "/section_denomination_metal/{number}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public StringBuilder getCoinsByCatalogNumber(@PathVariable("number") String number) throws JAXBException {
        List<CoinToXml> coinToXmlList = coinMapper.toDtoListXML(coinService.numberSectionDenominationMetal(number));
        StringBuilder stringBuilder = new StringBuilder();
        for (CoinToXml coinToXml : coinToXmlList) {
            stringBuilder.append(getXMLResponse(coinToXml));
        }
        return stringBuilder;
    }

    @GetMapping("/rabbit")
    public ResponseEntity<String> testRabbit(@RequestParam(value = "message") String message,
                                             @RequestParam(value = "key") String key) {
        template.setExchange("directExchange");
        template.convertAndSend(key, message);
        switch (key) {
            case "get" -> {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, "/coins/resultGetCoin")
                        .build();
            }
            case "getAll" -> {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, "/coins/resultGetAllCoin")
                        .build();
            }
            default -> {
                return ResponseEntity.badRequest().body("Invalid key. Only 'get' or 'getAll'");
            }
        }
    }

    @GetMapping(value = "/resultGetCoin", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getResultGetCoin() throws JAXBException, InterruptedException {
        Thread.sleep(500);
        return getXMLResponse(rabbitMqListener.getResult().get());
    }

    @GetMapping(value = "/resultGetAllCoin", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public StringBuilder getResultAllGetCoin() throws JAXBException, InterruptedException {
        Thread.sleep(500);
        StringBuilder stringBuilder = new StringBuilder();
        for (CoinToXml coinToXml : Objects.requireNonNull(rabbitMqListener.getResultAll().get())) {
            stringBuilder.append(getXMLResponse(coinToXml));
        }
        return stringBuilder;
    }
}















