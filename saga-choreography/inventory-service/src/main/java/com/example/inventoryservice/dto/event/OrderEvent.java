package com.example.inventoryservice.dto.event;

import com.example.inventoryservice.dto.PurchaseOrderDto;
import com.example.inventoryservice.enums.OrderStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class OrderEvent implements Event, Serializable {

    private UUID eventId;
    private Date date;
    private PurchaseOrderDto purchaseOrder;
    private OrderStatus orderStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
