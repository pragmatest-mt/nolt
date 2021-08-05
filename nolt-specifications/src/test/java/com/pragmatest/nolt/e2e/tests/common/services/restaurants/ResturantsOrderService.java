package com.pragmatest.nolt.e2e.tests.common.services.restaurants;

import com.pragmatest.nolt.contracts.customers.AcceptOrderRequest;
import com.pragmatest.nolt.contracts.customers.AcceptOrderResponse;
import org.springframework.http.ResponseEntity;

public interface ResturantsOrderService
{
    ResponseEntity<AcceptOrderResponse> acceptOrder(String restaurantId, String orderId, AcceptOrderRequest acceptOrderRequest);
}
