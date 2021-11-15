package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.data.entities.CustomerOrderEntity;
import com.pragmatest.nolt.customer_orders.data.repositories.CustomerOrdersRepository;
import com.pragmatest.nolt.customer_orders.enums.OrderState;
import com.pragmatest.nolt.customer_orders.services.models.Order;
import com.pragmatest.nolt.customer_orders.services.models.OrderSubmission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerOrdersService {
   @Autowired
   ModelMapper mapper;

    @Autowired
    CustomerOrdersRepository repository;

    public Order submitOrder(OrderSubmission orderSubmission) {
        CustomerOrderEntity customerOrderEntity = mapper.map(orderSubmission, CustomerOrderEntity.class);

        String orderId = UUID.randomUUID().toString();
        customerOrderEntity.setOrderId(orderId);
        customerOrderEntity.setOrderState(OrderState.SUBMITTED);

        CustomerOrderEntity savedEntity = repository.save(customerOrderEntity);

        Order order = mapper.map(savedEntity, Order.class);

        return order;
    }
}
