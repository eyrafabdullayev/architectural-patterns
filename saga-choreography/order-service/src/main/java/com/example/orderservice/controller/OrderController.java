package com.example.orderservice.controller;

import com.example.orderservice.dto.request.CreateOrderRequestDto;
import com.example.orderservice.entity.PurchaseOrder;
import com.example.orderservice.service.OrderPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderPurchaseService orderPurchaseService;

    @PostMapping
    public PurchaseOrder createOrder(@RequestBody CreateOrderRequestDto createOrderRequest) {
        return orderPurchaseService.createOrder(createOrderRequest);
    }
}
