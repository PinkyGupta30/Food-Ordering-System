package com.foodorder.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "order-topic";

    public void sendOrderNotification(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Order notification sent to Kafka: " + message);
    }
}