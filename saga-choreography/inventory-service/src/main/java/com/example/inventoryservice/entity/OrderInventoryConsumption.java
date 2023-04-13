package com.example.inventoryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderInventoryConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "quantity_consumed")
    private int quantityConsumed;

    public static OrderInventoryConsumption of(Long orderId, Long productId, int quantityConsumed) {
        return OrderInventoryConsumption.builder()
                .orderId(orderId)
                .productId(productId)
                .quantityConsumed(quantityConsumed)
                .build();
    }

}
