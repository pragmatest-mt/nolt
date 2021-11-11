package com.pragmatest.nolt.specifications.common.services.restaurants;

import com.pragmatest.nolt.specifications.common.models.restaurants.RestaurantAcceptOrderRequest;
import com.pragmatest.nolt.specifications.common.models.restaurants.RestaurantAcceptOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class RestaurantsOrderServiceImpl implements ResturantsOrderService{

    @Value(value = "${restaurant-service.accept-restaurant-order.template}")
    String acceptRestaurantOrderUrl;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<RestaurantAcceptOrderResponse> AcceptRestaurantOrder(String orderId, String restaurantId, RestaurantAcceptOrderRequest request) {
        HttpEntity<RestaurantAcceptOrderRequest> requestEntity = new HttpEntity<>(request, getDefaultHeaders(restaurantId));

        ResponseEntity<RestaurantAcceptOrderResponse> customerSubmitOrderResponseResponseEntity = restTemplate.exchange(
                acceptRestaurantOrderUrl, HttpMethod.POST,
                requestEntity, RestaurantAcceptOrderResponse.class, orderId);

        return customerSubmitOrderResponseResponseEntity;
    }

    private HttpHeaders getDefaultHeaders(String restaurantId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-Restaurant-Id", restaurantId);
        httpHeaders.set("X-Forwarded-For", "88.203.10.242");
        return httpHeaders;
    }

}
