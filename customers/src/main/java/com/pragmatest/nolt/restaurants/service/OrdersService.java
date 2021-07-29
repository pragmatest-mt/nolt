package com.pragmatest.nolt.restaurants.service;

import com.pragmatest.nolt.restaurants.service.models.Order;

import java.util.List;

public interface OrdersService {
    List<Order> getOrders();

    Order submitOrder(Order order);

    Order acceptOrder(Order order);
}
