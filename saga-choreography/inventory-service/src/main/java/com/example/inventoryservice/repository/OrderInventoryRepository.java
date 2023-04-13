package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Long> {

    Optional<OrderInventory> findByProductIdAndAvailableInventoryGreaterThanEqual(Long productId, int greaterThan);
    Optional<OrderInventory> findByProductId(Long productId);
}
