package com.pragmatest.nolt.customer_orders.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaTopicConfiguration {

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${order.submitted.topic}")
    private String orderSubmittedTopicName;

    @Value(value = "${order.accepted.topic}")
    private String orderAcceptedTopicName;


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic orderSubmittedTopic() {
        return new NewTopic(orderSubmittedTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderAcceptedTopic() {
        return new NewTopic(orderAcceptedTopicName, 1, (short) 1);
    }
}