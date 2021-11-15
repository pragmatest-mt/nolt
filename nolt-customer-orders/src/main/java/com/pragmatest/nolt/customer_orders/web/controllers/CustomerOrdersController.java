package com.pragmatest.nolt.customer_orders.web.controllers;

import com.pragmatest.nolt.customer_orders.web.requests.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.responses.SubmitOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CustomerOrdersController {

    @PostMapping(value = "orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SubmitOrderResponse submit(@RequestHeader(name = "X-Customer-Id") String customerId, @RequestBody SubmitOrderRequest request) {
        String orderId = UUID.randomUUID().toString();
        return new SubmitOrderResponse(orderId);
    }
}