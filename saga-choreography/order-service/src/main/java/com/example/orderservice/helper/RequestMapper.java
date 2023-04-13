package com.example.orderservice.helper;

import com.example.orderservice.dto.request.CreateOrderRequestDto;
import com.example.orderservice.entity.PurchaseOrder;
import com.example.orderservice.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Map;

public class RequestMapper {

    public static PurchaseOrder dtoToEntity(CreateOrderRequestDto orderRequest,
                                            final Map<Long, BigDecimal> productPriceMap) {
        if (!productPriceMap.containsKey(orderRequest.getProductId())) {
            throw new IllegalArgumentException();
        }
        return PurchaseOrder.builder()
                .productId(orderRequest.getProductId())
                .userId(orderRequest.getUserId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .quantity(orderRequest.getQuantity())
                .price(productPriceMap.get(orderRequest.getProductId()))
                .build();
    }
}
