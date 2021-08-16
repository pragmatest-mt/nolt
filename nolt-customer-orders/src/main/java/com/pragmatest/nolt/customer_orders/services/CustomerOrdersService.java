package com.pragmatest.nolt.customer_orders.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerOrdersService
{
    public String submitOrder() {
        return UUID.randomUUID().toString();
    }
}
