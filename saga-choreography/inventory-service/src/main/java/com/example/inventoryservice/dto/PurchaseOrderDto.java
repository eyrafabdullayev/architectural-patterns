package com.example.inventoryservice.dto;

import com.example.inventoryservice.enums.InventoryStatus;
import com.example.inventoryservice.enums.OrderStatus;
import com.example.inventoryservice.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
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
