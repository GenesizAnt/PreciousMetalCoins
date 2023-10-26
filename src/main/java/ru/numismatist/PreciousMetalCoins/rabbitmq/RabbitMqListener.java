package ru.numismatist.PreciousMetalCoins.rabbitmq;

import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.numismatist.PreciousMetalCoins.dto.CoinToXml;
import ru.numismatist.PreciousMetalCoins.dto.mapper.CoinMapper;
import ru.numismatist.PreciousMetalCoins.services.CoinService;

import static ru.numismatist.PreciousMetalCoins.util.Util.getXMLResponse;

@EnableRabbit
@Component
@Controller
public class RabbitMqListener {

    Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

    private final CoinService coinService;
    private final CoinMapper coinMapper;
    private ResponseEntity<CoinToXml> result;

    @Autowired
    public RabbitMqListener(CoinService coinService, CoinMapper coinMapper) {
        this.coinService = coinService;
        this.coinMapper = coinMapper;
    }

    @GetMapping(value = "/result", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getResult() throws JAXBException {
        System.out.println();
        return getXMLResponse(result.getBody());
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
//
//        return ResponseEntity.ok(getXMLResponse(coinToXml));


    }

//    @RabbitListener(queues = "coinQueue2") //addCoin
//    public ResponseEntity<String> processCoinQueue2(String message) {
//        CoinToXml coinToXml = convertXMLToCoinFromStringXml(message);
//        Coin newCoin = coinMapper.toCoin(coinToXml);
//        coinService.addNewCoin(newCoin);
//        return ResponseEntity.ok("Монета добавлена в базу");
//    }

//    @RabbitListener(queues = "coinQueue2")
//    public void processCoinQueue2(String message) {
//        logger.info("Received second coinQueue2: {}", message);
//    }



}
