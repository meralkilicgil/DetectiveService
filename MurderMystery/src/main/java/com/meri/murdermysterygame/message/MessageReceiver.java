package com.meri.murdermysterygame.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageReceiver {

    @RabbitListener(queues = "fraudQueue")
    public void receiveFraudNotifications(String message){
        log.info("Fraud Notification: " + message);
    }
}
