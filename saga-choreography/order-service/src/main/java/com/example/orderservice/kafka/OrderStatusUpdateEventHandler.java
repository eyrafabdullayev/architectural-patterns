package com.example.orderservice.kafka;

import com.example.orderservice.entity.PurchaseOrder;
import com.example.orderservice.enums.InventoryStatus;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.enums.PaymentStatus;
import com.example.orderservice.repository.OrderPurchaseRepository;
import com.example.orderservice.service.OrderStatusPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class OrderStatusUpdateEventHandler {

    private final OrderPurchaseRepository orderPurchaseRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public void updateOrder(final Long orderId, Consumer<PurchaseOrder> consumer) {
        orderPurchaseRepository.findById(orderId)
                .ifPresent(consumer.andThen(this::updateOrder));
    }

    public void updateOrder(PurchaseOrder purchaseOrder) {
        if (Objects.isNull(purchaseOrder.getInventoryStatus()) ||
            Objects.isNull(purchaseOrder.getPaymentStatus()))
            return;
        boolean isCompleted = PaymentStatus.RESERVED.equals(purchaseOrder.getPaymentStatus()) &&
                InventoryStatus.RESERVED.equals(purchaseOrder.getInventoryStatus());
        OrderStatus orderStatus = isCompleted ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isCompleted)
            orderStatusPublisher.raiseOrderEvent(purchaseOrder, orderStatus);
    }
}
