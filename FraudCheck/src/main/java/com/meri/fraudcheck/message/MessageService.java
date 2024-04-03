package com.meri.fraudcheck.message;

import org.springframework.stereotype.Service;

@Service

public class MessageService {

    public final MessageSender messageSender;

    private final String exchangeName = "fraudExchange";
    private final String routingKey = "topic.fraud";

    public MessageService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendFraudCheckNotification(String message){
        messageSender.sendMessage(exchangeName, routingKey, message);
    }
}
