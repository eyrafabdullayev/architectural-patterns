package com.example.paymentservice.dto.event;

import com.example.paymentservice.dto.PurchaseOrderDto;
import com.example.paymentservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
