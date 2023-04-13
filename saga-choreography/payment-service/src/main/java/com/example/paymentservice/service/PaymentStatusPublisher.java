package com.example.paymentservice.service;

import com.example.paymentservice.constants.Constant;
import com.example.paymentservice.dto.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentStatusPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void raiseOrderEvent(PaymentEvent paymentEvent) {
        kafkaTemplate.send(Constant.KafkaTopic.INVENTORY_SERVICE_TOPIC, paymentEvent);
    }
}
