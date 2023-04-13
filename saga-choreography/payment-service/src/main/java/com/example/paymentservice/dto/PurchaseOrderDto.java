package com.example.paymentservice.dto;

import com.example.paymentservice.enums.InventoryStatus;
import com.example.paymentservice.enums.OrderStatus;
import com.example.paymentservice.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
