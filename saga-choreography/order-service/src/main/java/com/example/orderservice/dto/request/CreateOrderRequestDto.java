package com.example.orderservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderRequestDto {

    private Long userId;
    private Long productId;
    private int quantity = 1;
    private UUID orderId;
}
