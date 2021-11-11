package com.pragmatest.nolt.specifications.common.services.restaurants;

import com.pragmatest.nolt.specifications.common.models.restaurants.RestaurantAcceptOrderRequest;
import com.pragmatest.nolt.specifications.common.models.restaurants.RestaurantAcceptOrderResponse;
import org.springframework.http.ResponseEntity;

public interface ResturantsOrderService
{
    ResponseEntity<RestaurantAcceptOrderResponse> AcceptRestaurantOrder(String orderId, String restaurantId, RestaurantAcceptOrderRequest request);
}
