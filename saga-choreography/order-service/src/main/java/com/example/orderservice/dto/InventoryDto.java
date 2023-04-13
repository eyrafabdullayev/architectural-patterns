package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {

    private Long orderId;
    private Long productId;

    public static InventoryDto of(Long orderId, Long productId) {
        return new InventoryDto(orderId, productId);
    }
}
