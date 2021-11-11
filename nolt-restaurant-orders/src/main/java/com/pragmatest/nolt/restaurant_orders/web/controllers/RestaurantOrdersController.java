package com.pragmatest.nolt.restaurant_orders.web.controllers;

import com.pragmatest.nolt.restaurant_orders.services.RestaurantOrdersService;
import com.pragmatest.nolt.restaurant_orders.services.models.Order;
import com.pragmatest.nolt.restaurant_orders.web.responses.AcceptOrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RestaurantOrdersController {

    @Autowired
    RestaurantOrdersService restaurantsOrderService;

    @Autowired
    ModelMapper mapper;

    @PostMapping(value = "orders/{orderId}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AcceptOrderResponse accept(@PathVariable(value="orderId") String orderId) {

        Order returnedOrder = restaurantsOrderService.acceptOrder(orderId);

        if (returnedOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        AcceptOrderResponse acceptOrderResponse
                = mapper.map(returnedOrder, AcceptOrderResponse.class);

        return acceptOrderResponse;
    }
}