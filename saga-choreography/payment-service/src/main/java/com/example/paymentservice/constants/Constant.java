package com.example.paymentservice.constants;

public final class Constant {

    public final static class KafkaTopic {

        public final static String INVENTORY_SERVICE_TOPIC = "payment-service";

        private KafkaTopic() {
            throw new RuntimeException();
        }
    }

    public final static String CONTAINER_FACTORY_BEAN_NAME = "kafkaListenerContainerFactory";

    public final static String DESERIALIZER_VALUE_DEFAULT_TYPE = "com.example.paymentservice.dto.event.OrderEvent";

    private Constant() {
        throw new RuntimeException();
    }
}
