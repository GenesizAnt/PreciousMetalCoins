package ru.numismatist.PreciousMetalCoins.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {

    Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "coinQueue1")
    public void processCoinQueue1(String message) {
        logger.info("Received first coinQueue1: {}", message);
    }

    @RabbitListener(queues = "coinQueue2")
    public void processCoinQueue2(String message) {
        logger.info("Received second coinQueue2: {}", message);
    }


}
