package ru.numismatist.PreciousMetalCoins.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.numismatist.PreciousMetalCoins.dto.CoinToXml;
import ru.numismatist.PreciousMetalCoins.dto.mapper.CoinMapper;
import ru.numismatist.PreciousMetalCoins.services.CoinService;

import java.util.List;

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

    public ResponseEntity<CoinToXml> getResult() {
        return result;
    }

    public ResponseEntity<List<CoinToXml>> getResultAll() {
        return resultAll;
    }
}
