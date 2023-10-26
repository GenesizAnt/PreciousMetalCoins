package ru.numismatist.PreciousMetalCoins.rabbitmq;

import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.numismatist.PreciousMetalCoins.dto.CoinToXml;
import ru.numismatist.PreciousMetalCoins.dto.mapper.CoinMapper;
import ru.numismatist.PreciousMetalCoins.models.Coin;
import ru.numismatist.PreciousMetalCoins.services.CoinService;

import java.util.List;
import java.util.Objects;

import static ru.numismatist.PreciousMetalCoins.util.Util.getXMLResponse;
import static ru.numismatist.PreciousMetalCoins.util.XmlSerializer.convertXMLToCoinFromStringXml;

@EnableRabbit
@Controller
public class RabbitMqListener {

    Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

    private final CoinService coinService;
    private final CoinMapper coinMapper;
    private final RabbitTemplate template;
    private ResponseEntity<CoinToXml> result;
    private ResponseEntity<List<CoinToXml>> resultAll;
    private List<CoinToXml> coinToXmlList;

    @Autowired
    public RabbitMqListener(CoinService coinService, CoinMapper coinMapper, RabbitTemplate template) {
        this.coinService = coinService;
        this.coinMapper = coinMapper;
        this.template = template;
    }

    @GetMapping(value = "/resultGetCoin", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getResultGetCoin() throws JAXBException {
//        System.out.println();
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

//        return getXMLResponse(result.getBody());
    }

//    @RabbitListener(queues = "coinQueue1")
//    public void processCoinQueue1(String message) {
//        logger.info("Received first coinQueue1: {}", message);
//    }

    @RabbitListener(queues = "coinQueue1") //get
    public void processCoinQueue1(String message) {
        logger.info("ok - {}", message);

        CoinToXml coinToXml = coinMapper.toDtoXML(coinService.getCoinByIndex(Integer.parseInt(message)));
        result = ResponseEntity.ok(coinToXml);
//        template.convertAndSend("directExchange", "my-response-routing-key", result);
//
//        return ResponseEntity.ok(getXMLResponse(coinToXml));


    }

    @RabbitListener(queues = "coinQueue2") //getAll
    public void processCoinQueue2(String message) {
        coinToXmlList = coinMapper.toDtoListXML(coinService.findAllCoin());
//        ResponseEntity<CoinToXml> result;
        resultAll = ResponseEntity.ok(coinToXmlList);
//        for (CoinToXml coinToXml : coinToXmlList) {
//            result = ResponseEntity.ok(coinToXml);
//        }
    }

//    @RabbitListener(queues = "coinQueue2")
//    public void processCoinQueue2(String message) {
//        logger.info("Received second coinQueue2: {}", message);
//    }



}
