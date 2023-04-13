package com.example.orderservice.kafka;

import com.example.orderservice.config.ApplicationProps;
import com.example.orderservice.constants.Constant;
import com.example.orderservice.dto.event.InventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryKafkaConsumerConfig {

    private final ApplicationProps applicationProps;
    
    @Bean(name = Constant.INVENTORY_CONTAINER_FACTORY_BEAN_NAME)
    public ConcurrentKafkaListenerContainerFactory<String, InventoryEvent> kafkaListenerContainerFactory(
            ConsumerFactory<String, InventoryEvent> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, InventoryEvent> concurrentKafkaListenerContainerFactory = 
                new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        return concurrentKafkaListenerContainerFactory;
    }


    @Bean
    public ConsumerFactory<String, InventoryEvent> consumerFactory() {
        final Map<String, Object> configProps = new HashMap<>();
        
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, applicationProps.getKafkaConn().getBootstrapBrokers());
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, applicationProps.getKafkaTopics().getInventoryGroupIdConfig());
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Constant.DESERIALIZER_VALUE_DEFAULT_TYPE);
        
        return new DefaultKafkaConsumerFactory<>(configProps,
                new StringDeserializer(),
                new JsonDeserializer<>(InventoryEvent.class));
    }
}
