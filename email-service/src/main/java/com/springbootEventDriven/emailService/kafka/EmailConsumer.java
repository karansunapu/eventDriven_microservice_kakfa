package com.springbootEventDriven.emailService.kafka;

import com.springbootEventDriven.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

    @KafkaListener(topics = "${spring.kafka.producer.topic-name}$"
    , groupId = "${spring.kafka.consumer.group-id}$")
    public void consumeOrderEvent(OrderEvent orderEvent){

        LOGGER.info("Email : event consumed " + orderEvent.toString());

        // send the confirmation email
    }
}
