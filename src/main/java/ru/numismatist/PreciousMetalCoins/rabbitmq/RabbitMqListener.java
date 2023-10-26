package ru.numismatist.PreciousMetalCoins.rabbitmq;

import jakarta.xml.bind.JAXBException;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.numismatist.PreciousMetalCoins.dto.CoinToXml;
import ru.numismatist.PreciousMetalCoins.dto.mapper.CoinMapper;
import ru.numismatist.PreciousMetalCoins.services.CoinService;

import java.util.List;
import java.util.Objects;

import static ru.numismatist.PreciousMetalCoins.util.Util.getXMLResponse;

@EnableRabbit
@Controller
public class RabbitMqListener {

    private final CoinService coinService;
    private final CoinMapper coinMapper;
    private ResponseEntity<CoinToXml> result;
    private ResponseEntity<List<CoinToXml>> resultAll;

    @Autowired
    public RabbitMqListener(CoinService coinService, CoinMapper coinMapper) {
        this.coinService = coinService;
        this.coinMapper = coinMapper;
    }

    @GetMapping(value = "/resultGetCoin", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getResultGetCoin() throws JAXBException {
        return getXMLResponse(result.getBody());
    }

    @GetMapping(value = "/resultGetAllCoin", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public StringBuilder getResultAllGetCoin() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();
        for (CoinToXml coinToXml : Objects.requireNonNull(resultAll.getBody())) {
            stringBuilder.append(getXMLResponse(coinToXml));
        }
        return stringBuilder;
    }

    @RabbitListener(queues = "coinQueue1") //get
    public void processCoinQueue1(String message) {
        CoinToXml coinToXml = coinMapper.toDtoXML(coinService.getCoinByIndex(Integer.parseInt(message)));
        result = ResponseEntity.ok(coinToXml);
    }

    @RabbitListener(queues = "coinQueue2") //getAll
    public void processCoinQueue2(String message) {
        List<CoinToXml> coinToXmlList = coinMapper.toDtoListXML(coinService.findAllCoin());
        resultAll = ResponseEntity.ok(coinToXmlList);
    }
}
