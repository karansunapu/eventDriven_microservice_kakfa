package com.springbootEventDriven.orderService.kafka;

import com.springbootEventDriven.basedomains.dto.Order;
import com.springbootEventDriven.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class OrderEventProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventProducer.class);

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private NewTopic topic;

    public OrderEventProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate, NewTopic topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publishMessage(Order order){
        // set the id for the order
        order.setOrderID(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("order is in pending state");
        orderEvent.setStatus("ORDER_PENDING");
        orderEvent.setOrder(order);

        LOGGER.info(String.format("Order event => %s", orderEvent.toString()));

        // to send as Json we cant directly use .send(topicName, message)
        // make a Message and add payload, header-topic
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC , topic.name() )
                .build();

        kafkaTemplate.send(message);
    }
}
