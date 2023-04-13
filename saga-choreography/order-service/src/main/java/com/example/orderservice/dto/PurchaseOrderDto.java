package com.example.orderservice.dto;

import com.example.orderservice.enums.InventoryStatus;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PurchaseOrderDto {

    private Long id;
    private Long userId;
    private Long productId;
    private BigDecimal price;
    private int quantity;
    private OrderStatus orderStatus;
    private InventoryStatus inventoryStatus;
    private PaymentStatus paymentStatus;
}
