package com.example.orderservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationProps {

    private KafkaConn kafkaConn;
    private KafkaTopics kafkaTopics;

    @Configuration
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KafkaConn {

        @Value("${spring.kafka.bootstrap-servers}")
        private String bootstrapBrokers;
    }

    @Configuration
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KafkaTopics {

        @Value("${app.kafka.inventory.groupIdConfig}")
        private String inventoryGroupIdConfig;
        @Value("${app.kafka.payment.groupIdConfig}")
        private String paymentGroupIdConfig;
    }
}
