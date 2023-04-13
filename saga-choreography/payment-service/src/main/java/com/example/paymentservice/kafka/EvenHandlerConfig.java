package com.example.paymentservice.kafka;

import com.example.paymentservice.constants.Constant;
import com.example.paymentservice.dto.event.OrderEvent;
import com.example.paymentservice.enums.OrderStatus;
import com.example.paymentservice.service.PaymentService;
import com.example.paymentservice.service.PaymentStatusPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EvenHandlerConfig {

    private final PaymentService paymentService;
    private final PaymentStatusPublisher paymentStatusPublisher;

    @KafkaListener(topics = {Constant.KafkaTopic.INVENTORY_SERVICE_TOPIC},
            containerFactory = Constant.CONTAINER_FACTORY_BEAN_NAME)
    public void inventoryListener(@Payload OrderEvent orderEvent) {
        processInventory(orderEvent);
    }

    private void processInventory(OrderEvent orderEvent) {
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus()))
            paymentStatusPublisher.raiseOrderEvent(
                    paymentService.orderPayment(orderEvent));
        else paymentService.cancelOrderEvent(orderEvent);
    }
}
