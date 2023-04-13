package com.example.inventoryservice.kafka;

import com.example.inventoryservice.constants.Constant;
import com.example.inventoryservice.dto.event.OrderEvent;
import com.example.inventoryservice.enums.OrderStatus;
import com.example.inventoryservice.service.InventoryService;
import com.example.inventoryservice.service.InventoryStatusPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EvenHandlerConfig {

    private final InventoryService inventoryService;
    private final InventoryStatusPublisher inventoryStatusPublisher;

    @KafkaListener(topics = {Constant.KafkaTopic.INVENTORY_SERVICE_TOPIC},
            containerFactory = Constant.CONTAINER_FACTORY_BEAN_NAME)
    public void inventoryListener(@Payload OrderEvent orderEvent) {
        processInventory(orderEvent);
    }

    private void processInventory(OrderEvent orderEvent) {
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus()))
            inventoryStatusPublisher.raiseOrderEvent(
                    inventoryService.orderInventory(orderEvent));
        else inventoryService.cancelOrderInventory(orderEvent);
    }
}
