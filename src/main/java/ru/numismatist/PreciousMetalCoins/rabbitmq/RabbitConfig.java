package ru.numismatist.PreciousMetalCoins.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

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
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindingToDirectExchange1() {
        return BindingBuilder.bind(coinQueue1()).to(directExchange()).with("get");
    }

    @Bean
    public Binding bindingToDirectExchange2() {
        return BindingBuilder.bind(coinQueue2()).to(directExchange()).with("getAll");
    }
}
