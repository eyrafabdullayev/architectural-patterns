package com.example.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Map;

@Configuration
public class ProductPriceConfig {

    @Bean
    public Map<Long, BigDecimal> productPrice() {
        return Map.of(
                1L, BigDecimal.valueOf(10.0),
                2L, BigDecimal.valueOf(20.0),
                3L, BigDecimal.valueOf(29.99)
        );
    }
}
