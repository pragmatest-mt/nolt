package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.data.entities.CustomerOrderEntity;
import com.pragmatest.nolt.customer_orders.data.repositories.CustomerOrdersRepository;
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

    public String submitOrder(OrderSubmission orderSubmission) {

        CustomerOrderEntity customerOrderEntity = mapper.map(orderSubmission, CustomerOrderEntity.class);

        String orderId = UUID.randomUUID().toString();
        customerOrderEntity.setOrderId(orderId);

        CustomerOrderEntity savedEntity = repository.save(customerOrderEntity);

        return savedEntity.getOrderId();
    }

    public Order getOrder(String orderId) {

        Optional<CustomerOrderEntity> orderEntities = repository.findById(orderId);

        Order order = mapper.map(orderEntities.get(), Order.class);
        return order;
    }

}
