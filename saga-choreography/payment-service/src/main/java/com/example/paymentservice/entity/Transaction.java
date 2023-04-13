package com.example.paymentservice.entity;

import com.example.paymentservice.dto.PurchaseOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "product_id")
    private Long productId;
    private BigDecimal amount;

    public static Transaction of(BigDecimal amount, PurchaseOrderDto purchaseOrder) {
        return Transaction.builder()
                .orderId(purchaseOrder.getId())
                .userId(purchaseOrder.getUserId())
                .productId(purchaseOrder.getProductId())
                .amount(amount)
                .build();
    }
}
