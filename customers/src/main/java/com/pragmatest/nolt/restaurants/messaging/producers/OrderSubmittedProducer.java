package com.pragmatest.nolt.restaurants.messaging.producers;

import com.pragmatest.nolt.restaurants.messaging.events.OrderSubmittedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@ConditionalOnProperty(value = "kafka.enabled", matchIfMissing = true)
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
                        System.out.println("Oops failed to produce");
                    }

                    @Override
                    public void onSuccess(SendResult<String, OrderSubmittedEvent> stringOrderSubmittedEventSendResult) {
                        System.out.println("Yeyyy msg out.");
                    }
                });
    }

}
