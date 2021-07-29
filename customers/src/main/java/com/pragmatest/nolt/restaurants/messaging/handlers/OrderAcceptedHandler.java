package com.pragmatest.nolt.restaurants.messaging.handlers;

import com.pragmatest.nolt.restaurants.messaging.events.OrderAcceptedEvent;
import com.pragmatest.nolt.restaurants.service.OrdersService;
import com.pragmatest.nolt.restaurants.service.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(value = "kafka.enabled", matchIfMissing = true)
@Component
public class OrderAcceptedHandler {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ModelMapper modelMapper;

    @KafkaListener(
            topics = "${order.accepted.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "orderAcceptedEventKafkaListenerContainerFactory")
    public void handleOrderAccepted(OrderAcceptedEvent orderAcceptedEvent) throws Exception {
        Order order = modelMapper.map(orderAcceptedEvent, Order.class);
        ordersService.acceptOrder(order);
    }
}


