package com.meri.murdermysterygame.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String MYSTERY_QUEUE = "mysteryQueue";
    public static final String MYSTERY_EXCHANGE = "mysteryExchange";
    public static final String FRAUD_QUEUE = "fraudQueue";
    public static final String FRAUD_EXCHANGE = "fraudExchange";

    @Bean
    public Queue mysteryQueue(){
        return new Queue(MYSTERY_QUEUE);
    }

    @Bean
    public Exchange mysteryExchange(){
        return new TopicExchange(MYSTERY_EXCHANGE);
    }

    @Bean
    public Binding mysteryBinding(){
        return BindingBuilder.bind(mysteryQueue()).to(mysteryExchange()).with("topic.mystery").noargs();
    }

    @Bean
    public Queue fraudQueue(){
        return new Queue(FRAUD_QUEUE);
    }

    @Bean
    public Exchange fraudExchange(){
        return new TopicExchange(FRAUD_EXCHANGE);
    }

    @Bean
    public Binding fraudBinding(){
        return BindingBuilder.bind(fraudQueue()).to(fraudExchange()).with("topic.fraud").noargs();
    }
}
