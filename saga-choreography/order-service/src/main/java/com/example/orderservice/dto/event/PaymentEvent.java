package com.example.orderservice.dto.event;

import com.example.orderservice.dto.PaymentDto;
import com.example.orderservice.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent implements Event, Serializable {

    private UUID eventId;
    private Date date;
    private PaymentDto payment;
    private PaymentStatus paymentStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
