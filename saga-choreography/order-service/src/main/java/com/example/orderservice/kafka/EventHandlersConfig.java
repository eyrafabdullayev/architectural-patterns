package com.example.orderservice.kafka;

import com.example.orderservice.constants.Constant;
import com.example.orderservice.dto.event.InventoryEvent;
import com.example.orderservice.dto.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventHandlersConfig {

    private final OrderStatusUpdateEventHandler orderStatusUpdateEventHandler;


    @KafkaListener(topics = {Constant.KafkaTopic.INVENTORY_SERVICE_TOPIC},
            containerFactory = Constant.INVENTORY_CONTAINER_FACTORY_BEAN_NAME)
    public void inventoryEventConsumer(@Payload InventoryEvent inventoryEvent) {
        orderStatusUpdateEventHandler.updateOrder(inventoryEvent.getInventoryDto().getOrderId(),
                (purchaseOrder) -> purchaseOrder.setInventoryStatus(inventoryEvent.getInventoryStatus()));
    }

    @KafkaListener(topics = {Constant.KafkaTopic.PAYMENT_SERVICE_TOPIC},
            containerFactory = Constant.PAYMENT_CONTAINER_FACTORY_BEAN_NAME)
    public void paymentEventConsumer(@Payload PaymentEvent paymentEvent) {
        orderStatusUpdateEventHandler.updateOrder(paymentEvent.getPayment().getOrderId(),
                (purchaseOrder -> purchaseOrder.setPaymentStatus(paymentEvent.getPaymentStatus())));
    }
}
