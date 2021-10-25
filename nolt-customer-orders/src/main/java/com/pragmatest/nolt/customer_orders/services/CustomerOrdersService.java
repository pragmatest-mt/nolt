package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.data.entities.CustomerOrderEntity;
import com.pragmatest.nolt.customer_orders.data.repositories.CustomerOrdersRepository;
import com.pragmatest.nolt.customer_orders.enums.OrderState;
import com.pragmatest.nolt.customer_orders.messaging.events.OrderSubmittedEvent;
import com.pragmatest.nolt.customer_orders.messaging.producers.OrderSubmittedProducer;
import com.pragmatest.nolt.customer_orders.services.models.Order;
import com.pragmatest.nolt.customer_orders.services.models.OrderSubmission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerOrdersService {
    @Autowired
    CustomerOrdersRepository repository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    private OrderSubmittedProducer orderSubmittedProducer;

    public String submitOrder(OrderSubmission orderSubmission) {

        CustomerOrderEntity customerOrderEntity = mapper.map(orderSubmission, CustomerOrderEntity.class);

        String orderId = UUID.randomUUID().toString();
        customerOrderEntity.setOrderId(orderId);
        customerOrderEntity.setOrderState(OrderState.SUBMITTED);

        CustomerOrderEntity savedEntity = repository.save(customerOrderEntity);

        OrderSubmittedEvent orderSubmittedEvent = mapper.map(savedEntity, OrderSubmittedEvent.class);
        orderSubmittedProducer.send(orderSubmittedEvent);

        return savedEntity.getOrderId();
    }

    public Order getOrder(String orderId) {

        Optional<CustomerOrderEntity> orderEntities = repository.findById(orderId);

        Order order = mapper.map(orderEntities.get(), Order.class);
        return order;
    }

}
