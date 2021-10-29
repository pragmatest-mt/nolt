package com.pragmatest.nolt.restaurant_orders.messaging.consumers;

import com.pragmatest.nolt.restaurant_orders.messaging.events.OrderSubmittedEvent;
import com.pragmatest.nolt.restaurant_orders.services.RestaurantOrdersService;
import com.pragmatest.nolt.restaurant_orders.services.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderSubmittedConsumer {

    @Autowired
    private RestaurantOrdersService ordersService;

    @Autowired
    private ModelMapper modelMapper;

    @KafkaListener(
            topics = "${order.submitted.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "orderSubmittedEventKafkaListenerContainerFactory")
    public void handleOrderSubmitted(OrderSubmittedEvent orderSubmittedEvent) throws Exception {
        System.out.println("Consumed message: " + orderSubmittedEvent);

        Order order = modelMapper.map(orderSubmittedEvent, Order.class);
        ordersService.submitOrder(order);
    }
}
