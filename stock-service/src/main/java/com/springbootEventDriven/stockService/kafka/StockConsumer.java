package com.springbootEventDriven.stockService.kafka;

import com.springbootEventDriven.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockConsumer.class);

    @KafkaListener(topics = "${spring.kafka.producer.topic-name}$"
    , groupId = "${spring.kafka.consumer.group-id}$")
    public void consumeOrderEvent(OrderEvent orderEvent){

        LOGGER.info("Stock : event consumed " + orderEvent.toString());

        // send to database
        // make an entity class
        // connect to database (properties file)
        // make repository class extending Jpa Repo for CRUD operation
    }
}
