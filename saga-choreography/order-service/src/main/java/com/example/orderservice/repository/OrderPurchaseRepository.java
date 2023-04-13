package com.example.orderservice.repository;

import com.example.orderservice.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPurchaseRepository extends JpaRepository<PurchaseOrder, Long> {
}
