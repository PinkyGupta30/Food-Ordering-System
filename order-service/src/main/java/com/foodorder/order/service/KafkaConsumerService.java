package com.foodorder.order.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void consumeOrderNotification(String message) {
        System.out.println("Order notification received from Kafka: " + message);
    }
}