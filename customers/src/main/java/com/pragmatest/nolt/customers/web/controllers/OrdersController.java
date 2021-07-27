package com.pragmatest.nolt.customers.web.controllers;

import com.pragmatest.nolt.customers.service.OrdersService;
import com.pragmatest.nolt.customers.service.models.Order;
import com.pragmatest.nolt.customers.web.requests.OrderRequest;
import com.pragmatest.nolt.customers.web.responses.OrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    OrderResponse submit(OrderRequest orderRequest) {
       Order order = modelMapper.map(orderRequest, Order.class);
       order = ordersService.submitOrder(order);

       OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);

       return orderResponse;
    }
}
