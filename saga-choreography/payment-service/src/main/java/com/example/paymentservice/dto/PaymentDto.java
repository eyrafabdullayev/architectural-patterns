package com.example.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    private Long orderId;
    private Long userId;
    private BigDecimal price;

    public static PaymentDto of(PurchaseOrderDto purchaseOrder) {
        return PaymentDto.builder()
                .orderId(purchaseOrder.getId())
                .userId(purchaseOrder.getUserId())
                .price(purchaseOrder.getPrice())
                .build();
    }
}
