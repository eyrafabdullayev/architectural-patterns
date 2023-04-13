package com.example.inventoryservice.service;

import com.example.inventoryservice.constants.Constant;
import com.example.inventoryservice.dto.event.InventoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryStatusPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void raiseOrderEvent(InventoryEvent inventoryEvent) {
        kafkaTemplate.send(Constant.KafkaTopic.INVENTORY_SERVICE_TOPIC, inventoryEvent);
    }
}
