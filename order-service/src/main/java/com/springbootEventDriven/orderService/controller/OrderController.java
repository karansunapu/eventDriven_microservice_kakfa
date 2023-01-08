package com.springbootEventDriven.orderService.controller;

import com.springbootEventDriven.basedomains.dto.Order;
import com.springbootEventDriven.orderService.kafka.OrderEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

        private OrderEventProducer orderEventProducer;

        public OrderController(OrderEventProducer orderEventProducer) {
                this.orderEventProducer = orderEventProducer;
        }

        @PostMapping("/placeOrder")
        public ResponseEntity<String> placeOrder(@RequestBody Order order){
                orderEventProducer.publishMessage(order);
                return ResponseEntity.ok("order placed successfully");
        }
}
