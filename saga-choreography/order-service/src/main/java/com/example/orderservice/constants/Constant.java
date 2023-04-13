package com.example.orderservice.constants;

public final class Constant {

    public final static class KafkaTopic {

        public final static String ORDER_SERVICE_TOPIC = "order-service";
        public final static String INVENTORY_SERVICE_TOPIC = "inventory-service";
        public final static String PAYMENT_SERVICE_TOPIC = "payment-service";

        private KafkaTopic() {
            throw new RuntimeException();
        }
    }

    public final static String INVENTORY_CONTAINER_FACTORY_BEAN_NAME = "inventoryKafkaListenerContainerFactory";
    public final static String PAYMENT_CONTAINER_FACTORY_BEAN_NAME = "paymentKafkaListenerContainerFactory";

    public final static String DESERIALIZER_VALUE_DEFAULT_TYPE = "com.example.orderservice.dto.*";

    private Constant() {
        throw new RuntimeException();
    }
}
