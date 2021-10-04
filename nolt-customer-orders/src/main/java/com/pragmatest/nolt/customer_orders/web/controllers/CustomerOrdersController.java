package com.pragmatest.nolt.customer_orders.web.controllers;

import com.pragmatest.nolt.customer_orders.services.CustomerOrdersService;
import com.pragmatest.nolt.customer_orders.services.models.OrderSubmission;
import com.pragmatest.nolt.customer_orders.web.requests.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.responses.SubmitOrderResponse;

import java.util.UUID;
import jdk.jfr.ContentType;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerOrdersController {

    @Autowired
    CustomerOrdersService customerOrdersService;

    @Autowired
    ModelMapper mapper;

    @PostMapping(value = "orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SubmitOrderResponse submit(@RequestHeader(name = "X-Customer-Id") String customerId, @RequestBody SubmitOrderRequest request) {

        OrderSubmission orderSubmission = mapper.map(request, OrderSubmission.class);
        orderSubmission.setCustomerId(customerId);

        String orderId = customerOrdersService.submitOrder(orderSubmission);
        return new SubmitOrderResponse(orderId);
    }
}