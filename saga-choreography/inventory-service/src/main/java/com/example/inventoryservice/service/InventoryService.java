package com.example.inventoryservice.service;

import com.example.inventoryservice.constants.Constant;
import com.example.inventoryservice.dto.InventoryDto;
import com.example.inventoryservice.dto.event.InventoryEvent;
import com.example.inventoryservice.dto.event.OrderEvent;
import com.example.inventoryservice.entity.OrderInventoryConsumption;
import com.example.inventoryservice.enums.InventoryStatus;
import com.example.inventoryservice.repository.OrderInventoryConsumptionRepository;
import com.example.inventoryservice.repository.OrderInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final OrderInventoryRepository orderInventoryRepository;
    private final OrderInventoryConsumptionRepository orderInventoryConsumptionRepository;

    @Transactional
    public InventoryEvent orderInventory(OrderEvent orderEvent) {
        InventoryDto inventoryDto = InventoryDto.of(orderEvent.getPurchaseOrder().getId(),
                orderEvent.getPurchaseOrder().getProductId());
        return orderInventoryRepository.findByProductIdAndAvailableInventoryGreaterThanEqual(orderEvent.getPurchaseOrder().getProductId(), Constant.ZERO)
                .map(i -> {
                    int quantity = orderEvent.getPurchaseOrder().getQuantity();
                    i.setAvailableInventory(i.getAvailableInventory() - quantity);
                    orderInventoryConsumptionRepository.save(OrderInventoryConsumption.of(orderEvent.getPurchaseOrder().getId(),
                            orderEvent.getPurchaseOrder().getProductId(), quantity));
                    return InventoryEvent.of(inventoryDto, InventoryStatus.RESERVED); })
                .orElse(InventoryEvent.of(inventoryDto, InventoryStatus.REJECTED));

    }

    @Transactional
    public void cancelOrderInventory(OrderEvent orderEvent) {
        orderInventoryConsumptionRepository.findByOrderId(orderEvent.getPurchaseOrder().getId())
                .ifPresent(ci -> {
                    orderInventoryRepository.findByProductId(orderEvent.getPurchaseOrder().getProductId())
                            .ifPresent(i -> i.setAvailableInventory(i.getAvailableInventory() + orderEvent.getPurchaseOrder().getQuantity()));
                    orderInventoryConsumptionRepository.delete(ci);
                });
    }
}
