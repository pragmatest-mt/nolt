package com.pragmatest.nolt.customer_orders.messaging.producers;

import com.pragmatest.nolt.customer_orders.messaging.events.OrderSubmittedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class OrderSubmittedProducer {

    @Value(value = "${order.submitted.topic}")
    private String orderSubmittedTopicName;

    @Autowired
    KafkaTemplate<String, OrderSubmittedEvent> orderSubmittedKafkaTemplate;

    public void send(OrderSubmittedEvent event) {
        orderSubmittedKafkaTemplate
            .send(orderSubmittedTopicName, event)
            .addCallback(new ListenableFutureCallback<SendResult<String, OrderSubmittedEvent>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    // Handle failures here.
                }

                @Override
                public void onSuccess(SendResult<String, OrderSubmittedEvent> eventSendResult) {
                    // Handle successes here.
                }
            });
    }
}
