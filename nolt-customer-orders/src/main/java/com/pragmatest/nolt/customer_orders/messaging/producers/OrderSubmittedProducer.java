package com.pragmatest.nolt.customer_orders.messaging.producers;

import com.pragmatest.nolt.customer_orders.messaging.events.OrderSubmittedEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class OrderSubmittedProducer {

    @Value(value = "${order.submitted.topic}")
    String orderSubmittedTopicName;

    @Autowired
    KafkaTemplate<String, OrderSubmittedEvent> orderSubmittedKafkaTemplate;

    public void send(OrderSubmittedEvent event) {
        orderSubmittedKafkaTemplate
            .send(orderSubmittedTopicName, event)
            .addCallback(new ListenableFutureCallback<SendResult<String, OrderSubmittedEvent>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Failed to produced message: " + throwable);
                }

                @Override
                public void onSuccess(SendResult<String, OrderSubmittedEvent> eventSendResult) {
                    ProducerRecord<String, OrderSubmittedEvent> producerRecord = eventSendResult.getProducerRecord();
                    System.out.println("Successfully produced message: " + producerRecord);
                }
            });
    }
}
