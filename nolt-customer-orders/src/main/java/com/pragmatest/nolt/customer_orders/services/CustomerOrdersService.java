package com.pragmatest.nolt.customer_orders.services;

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


    public Order submitOrder(OrderSubmission orderSubmission) {
        Order order = mapper.map(orderSubmission, Order.class);

        String orderId = UUID.randomUUID().toString();

        order.setOrderId(orderId);
        order.setOrderState(OrderState.SUBMITTED);

        return order;
    }
}
