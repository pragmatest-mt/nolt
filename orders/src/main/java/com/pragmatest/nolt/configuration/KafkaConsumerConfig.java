package com.pragmatest.nolt.configuration;

import com.pragmatest.nolt.messaging.commands.AddToOrderCommand;
import com.pragmatest.nolt.messaging.events.OrderCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    @Autowired
    private KafkaTemplate<String, OrderCreatedEvent> orderCreatedEventKafkaTemplate;

    public ConsumerFactory<String, AddToOrderCommand> addToOrderCommandConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new ErrorHandlingDeserializer(new JsonDeserializer<>(AddToOrderCommand.class)));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AddToOrderCommand> addToOrderCommandKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AddToOrderCommand> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(addToOrderCommandConsumerFactory());
        factory.setErrorHandler(new SeekToCurrentErrorHandler());
        //factory.setReplyTemplate(orderCreatedEventKafkaTemplate);
        return factory;
    }
}