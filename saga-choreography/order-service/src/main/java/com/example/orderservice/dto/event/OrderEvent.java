package com.example.orderservice.dto.event;

import com.example.orderservice.dto.PurchaseOrderDto;
import com.example.orderservice.enums.OrderStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class OrderEvent implements Event, Serializable {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private PurchaseOrderDto purchaseOrder;
    private OrderStatus orderStatus;

    public OrderEvent(PurchaseOrderDto purchaseOrder, OrderStatus orderStatus) {
        this.purchaseOrder = purchaseOrder;
        this.orderStatus = orderStatus;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
