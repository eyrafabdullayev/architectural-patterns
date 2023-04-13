package com.example.orderservice.service;

import com.example.orderservice.constants.Constant;
import com.example.orderservice.dto.event.OrderEvent;
import com.example.orderservice.dto.PurchaseOrderDto;
import com.example.orderservice.entity.PurchaseOrder;
import com.example.orderservice.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void raiseOrderEvent(PurchaseOrder purchaseOrder, OrderStatus orderStatus) {
        kafkaTemplate.send(Constant.KafkaTopic.ORDER_SERVICE_TOPIC,
                new OrderEvent(PurchaseOrderDto.builder()
                        .id(purchaseOrder.getId())
                        .orderStatus(orderStatus)
                        .price(purchaseOrder.getPrice())
                        .userId(purchaseOrder.getUserId())
                        .productId(purchaseOrder.getProductId())
                        .quantity(purchaseOrder.getQuantity())
                        .build(), orderStatus));
    }
}
