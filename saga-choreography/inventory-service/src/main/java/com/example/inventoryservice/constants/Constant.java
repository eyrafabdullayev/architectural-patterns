package com.example.inventoryservice.constants;

public final class Constant {

    public final static int ZERO = 0;

    public final static String CONTAINER_FACTORY_BEAN_NAME = "kafkaListenerContainerFactory";

    public final static String DESERIALIZER_VALUE_DEFAULT_TYPE = "com.example.inventoryservice.dto.event.OrderEvent";

    public final static class KafkaTopic {

        public final static String INVENTORY_SERVICE_TOPIC = "inventory-service";

        private KafkaTopic() {
            throw new RuntimeException();
        }
    }

    private Constant() {
    }
}
