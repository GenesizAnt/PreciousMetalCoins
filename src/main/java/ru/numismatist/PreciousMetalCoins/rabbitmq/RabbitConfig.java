package ru.numismatist.PreciousMetalCoins.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue coinQueue1() {
        return new Queue("coinQueue1");
    }

    @Bean
    public Queue coinQueue2() {
        return new Queue("coinQueue2");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("coinExchange");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(coinQueue1()).to(directExchange()).with("collectorValue");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(coinQueue2()).to(directExchange()).with("coin");
    }
}
