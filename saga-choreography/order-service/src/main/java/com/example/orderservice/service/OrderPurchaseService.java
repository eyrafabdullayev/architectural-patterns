package com.example.orderservice.service;

import com.example.orderservice.dto.request.CreateOrderRequestDto;
import com.example.orderservice.entity.PurchaseOrder;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.helper.RequestMapper;
import com.example.orderservice.repository.OrderPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderPurchaseService {

    private final Map<Long, BigDecimal> productPriceMap;
    private final OrderStatusPublisher orderStatusPublisher;
    private final OrderPurchaseRepository orderPurchaseRepository;

    public PurchaseOrder createOrder(CreateOrderRequestDto orderRequest) {
        PurchaseOrder purchaseOrder = orderPurchaseRepository.save(
                RequestMapper.dtoToEntity(orderRequest, productPriceMap));
        orderStatusPublisher.raiseOrderEvent(purchaseOrder, OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }
}
